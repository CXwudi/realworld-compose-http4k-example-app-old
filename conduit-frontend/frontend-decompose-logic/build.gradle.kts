import my.util.applyCommonFrontendDependencies

plugins {
  id("my.kmp-library")
}

android {
  namespace = "mikufan.cx.conduit.frontend.logic"
}

kotlin {
  applyCommonFrontendDependencies()
  sourceSets {
    commonMain.dependencies {
    }
  }
}