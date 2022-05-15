plugins {
    kotlin("jvm") version "1.6.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.1.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")
}

