plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.compose)
  alias(libs.plugins.kotlinCompose)
}

kotlin {
  js(IR) {
    browser()
    binaries.executable()
  }

  sourceSets {
    jsMain.dependencies {
      implementation(project(":frontend-decompose-logic"))
      implementation(project(":frontend-compose-ui"))
      implementation(compose.ui)
      implementation(compose.foundation)
    }
  }
}