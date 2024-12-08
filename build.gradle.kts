plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation(kotlin("stdlib"))

    // Serialization library for JSON handling
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // JUnit 5 for unit testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    // Kotlin Test for more Kotlin-friendly testing syntax
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform() // Ensures the use of JUnit 5 for testing
}

kotlin {
    jvmToolchain(21) // Specifies JDK version for the JVM
}
