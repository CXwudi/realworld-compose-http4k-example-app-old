dependencyResolutionManagement {
  repositories {
    gradlePluginPortal()
    google()
  }
  versionCatalogs {
    create("libs") {
      from(files("../libs.versions.toml"))
    }
  }
}

fileTree(".").matching {
  include("*/build.gradle.kts")
  include("*/build.gradle")
}.files.map { it.parentFile.name }.forEach { include(it) }
