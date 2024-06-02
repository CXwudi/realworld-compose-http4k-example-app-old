dependencyResolutionManagement {
  repositories {
    gradlePluginPortal()
  }
  versionCatalogs {
    create("settings") {
      from(files("../libs.versions.toml"))
    }
  }
}

include("root-settings-plugin")