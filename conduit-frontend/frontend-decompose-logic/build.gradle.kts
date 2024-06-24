plugins {
  id("my.kmp-frontend-library")
}

android {
  namespace = "mikufan.cx.conduit.frontend.logic"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      // add dependencies that are specific for this decompose logic module
      // dependencies used in both this module and compose ui module are extracted into the precompiled script plugin
    }
  }
}