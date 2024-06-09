plugins {
  id("my.kmp-library")
  alias(libs.plugins.kotlinCompose)
  alias(libs.plugins.compose)
}

android {
  namespace = "mikufan.cx.conduit.frontend.ui"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(project(":frontend-decompose-logic"))
      implementation(compose.runtime)
      implementation(compose.ui)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.components.resources)
      implementation(libs.dev.decompose)
    }
    commonJvmMain.dependencies {
      implementation(compose.uiTooling)
      implementation(compose.preview)
    }
    jvmMain.dependencies {
      implementation(compose.desktop.common)
    }
    androidMain.dependencies {
    }
  }
}
