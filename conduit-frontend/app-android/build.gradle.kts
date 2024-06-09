plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.compose)
  alias(libs.plugins.kotlinCompose)
}

kotlin {
  androidTarget()

  sourceSets {
    androidMain.dependencies {
      implementation(project(":frontend-decompose-logic"))
      implementation(project(":frontend-compose-ui"))

      implementation(libs.dev.androidx.appcompat)
      implementation(libs.dev.androidx.coreKtx)
      implementation(libs.dev.androidx.activityCompose)
    }
  }
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
  }
}

android {
  namespace = "mikufan.cx.conduit.frontend.app.android"
  compileSdk = 34
  defaultConfig {
    applicationId = "mikufan.cx.conduit.frontend.app.android"
    minSdk = 26
    versionCode = 1
    versionName = "1.0"
  }
}