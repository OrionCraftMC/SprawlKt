package io.github.orioncraftmc.stretchkt.gentest

import io.github.orioncraftmc.stretchkt.gentest.model.StretchElement
import org.openqa.selenium.By
import org.openqa.selenium.WindowType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverLogLevel
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

data class TestFixture(val name: String, val path: File, var handle: String? = null) {
    var rawDescription: String = ""
        set(value) {
            field = value
            description = StretchElement.fromJson(value)
        }

    lateinit var description: StretchElement

    fun <R> ChromeDriver.act(action: () -> R): R {
        switchTo().window(handle)
        val result = action()

        return result
    }
}

fun main() {

    val testFixturesDir = File("test_fixtures")

    if (!testFixturesDir.exists()) {
        throw IllegalStateException("Test fixtures directory does not exist")
    }

    val fixtures =
        (testFixturesDir.listFiles() ?: throw IllegalStateException("Test fixtures is not a directory")).asSequence()
            .filter { it.isFile }
            .filter { it.extension == "html" }
            .map { TestFixture(it.nameWithoutExtension, it) }
            .sortedBy { it.name }
            .take(10)
            .toList()


    val options = ChromeOptions().apply {
        addArguments("--disable-gpu")
        logLevel = ChromeDriverLogLevel.SEVERE
    }
    val driver = ChromeDriver(options)
    val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    fixtures.forEachIndexed { i, fixture ->
        if (i != 0) driver.switchTo().newWindow(WindowType.TAB)

        driver.navigate().to(fixture.path.toURI().toURL())

        fixture.handle = driver.windowHandle
    }


    val handlesCount = driver.windowHandles.size
    driver.windowHandles.forEachIndexed { i, handle ->
        driver.switchTo().window(handle)
        val fixture = fixtures.first { it.handle == handle }

        testRootElement(driver, wait, fixture)

        if (i != handlesCount - 1) {
            driver.close()
        }
    }

    println(fixtures.maxByOrNull { it.rawDescription.length }!!.rawDescription)

    System.`in`.read()

    driver.quit()

}

fun testRootElement(client: ChromeDriver, wait: WebDriverWait, fixture: TestFixture): String {
    val testRoot = wait.until {
        client.findElement(By.id("test-root"))
    } ?: throw IllegalStateException("Test fixture has no root element")

    val description = testRoot.getAttribute("__stretch_description__")
        ?: throw IllegalStateException("Test fixture has no Stretch Description")

    println("Got description for ${fixture.name}")

    fixture.rawDescription = description

    return description

}
