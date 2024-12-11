plugins {
    kotlin("jvm") version "2.0.20" // Ensure compatibility with JDK
    kotlin("plugin.serialization") version "2.0.20" // Serialization support
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // Use Maven Central for dependency resolution
}

dependencies {
    // Kotlin Standard Library
    implementation(kotlin("stdlib"))

    // Gson for JSON handling
    implementation("com.google.code.gson:gson:2.8.9")

    // Kotlin Serialization for JSON handling
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // JUnit 5 for unit testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // Kotlin Test for concise and Kotlin-friendly testing syntax
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform() // Ensures the use of JUnit 5 for testing
}

kotlin {
    jvmToolchain(21) // Ensure compatibility with JDK 21
}
