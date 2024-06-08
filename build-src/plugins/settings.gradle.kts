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

include("version-catalog-util", "multiplatform-library", "kotlin-jvm-app")
