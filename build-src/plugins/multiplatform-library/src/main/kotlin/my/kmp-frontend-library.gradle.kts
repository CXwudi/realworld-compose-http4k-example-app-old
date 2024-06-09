package my

import my.util.Libs

/**
 * Common Kotlin Multiplatform setup for frontend modules,
 * built on top of [my.kmp-library] with frontend specific common dependencies.
 */
plugins {
  id("my.kmp-library")
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(Libs.Decompose)
      implementation(Libs.Koin)
    }

    androidMain.dependencies {
      implementation(Libs.AndroidXCoreKtx)
      implementation(Libs.AndroidXAppCompat)
      implementation(Libs.AndroidXActivityCompose)
    }
  }
}