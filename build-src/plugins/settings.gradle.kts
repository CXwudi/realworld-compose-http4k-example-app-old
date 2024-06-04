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

include("convention:multiplatform-library")
