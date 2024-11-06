plugins {
    kotlin("jvm") version "1.9.10"  // Use a stable Kotlin version like 1.9.10
    kotlin("plugin.serialization") version "1.9.10"  // Add Kotlin serialization plugin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")  // Add serialization dependency
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)  // Ensure this is set to a valid JDK version (if you're using JDK 21)
}
