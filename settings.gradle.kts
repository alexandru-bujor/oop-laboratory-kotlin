plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "untitled"
include("test-module")
include("src:test_version2_0")
findProject(":src:test_version2_0")?.name = "test_version2_0"
