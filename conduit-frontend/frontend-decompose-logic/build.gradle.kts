plugins {
  id("my.kmp-library")
}

android {
  namespace = "mikufan.cx.conduit.frontend.logic"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(libs.dev.decompose)
      implementation(libs.dev.koin)
    }
  }
}