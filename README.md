# StretchKt

StretchKt is a Kotlin port of [stretch2](https://github.com/DioxusLabs/stretch), which is an implementation of Flexbox
originally written in [Rust](https://www.rust-lang.org).

The current tracked commit of the port is `42190498ca906df8a7345826078ca33e620edb63`.

## Goals

This project serves as a pure Kotlin port of the original Rust implementation. 

## Usage (not ready yet)

StretchKt is built in Kotlin and can be used by any JVM languages.

### Kotlin

1. Add the repository to your Gradle project
```kotlin
repositories {
    maven("https://raw.githubusercontent.com/OrionCraftMC/OrionMaven/main/")
}
```

2. Add the dependency to your project
````kotlin
dependencies {
    implementation("io.github.orioncraftmc:StretchKt:1.0.0-SNAPSHOT")
}
````

3. You're ready to go!
```kotlin
// main.kt
import io.github.orioncraftmc.stretchkt.geometry.Size
import io.github.orioncraftmc.stretchkt.node.Node
import io.github.orioncraftmc.stretchkt.node.Stretch
import io.github.orioncraftmc.stretchkt.style.Style
import io.github.orioncraftmc.stretchkt.style.enums.StretchDimension

fun main() {
    // You can use the `Node` class to create a new node.
    val child = Node(
        Style(
            size = Size(StretchDimension.Percent(0.5f), StretchDimension.Auto),
        ),
        emptyList()
    )

    // Or if you prefer, you can use the `Stretch` class to also create new nodes.
    val node = Stretch.newNode(
        Style(
            size = Size(StretchDimension.Points(100f), StretchDimension.Points(100f)),
        ),
        listOf(child)
    )

    val layout = Stretch.computeLayout(node, Size.undefinedNumber())

    println(layout)
}
```

### Testing (not ready yet)

Stretch2 is tested by validating that layouts written in StretchKt perform the same as in Chrome.
This is done by rendering an equivalent layout in HTML and then generating a Kotlin test case which asserts that the resulting layout is the same when run through Stretch.

You can run these tests without setting up a webdriver environment but if you are looking to add any test case you will need to install [chromedriver](http://chromedriver.chromium.org).

Once you have chromedriver installed and available in `PATH` you can re-generate all tests by running `gradle :gentest:run`.

To add a new test case add another HTML file to `/test_fixtures` following the current tests as a template for new tests.

## Relationship to Yoga

If you're in the mood for some Yoga but can't depend on native libraries, also check out our pure Java port of Yoga called [meditate-layout](https://github.com/OrionCraftMC/meditate-layout).

[Yoga](https://www.yogalayout.com) is a cross-platform implementation of Flexbox written in C.
Yoga is a fantastic project but has some fundamental issues which the upstream project hopes to resolve.
Compared to Yoga they aim to have a stronger adherence to web standards, a flexible architecture eventually supporting multiple layout algorithms, and future performance improvements including multi-threaded layout.
In addition to this they aim to use a safer language with a more modern codebase.
