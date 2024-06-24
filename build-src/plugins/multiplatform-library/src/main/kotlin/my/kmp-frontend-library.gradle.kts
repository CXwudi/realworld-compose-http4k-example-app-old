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
      implementation(Libs.DecomposeCompose)
      implementation(Libs.MviKotlin)
      implementation(Libs.MviKotlinMain)
      implementation(Libs.MviKotlinCoroutines)
      implementation(project.dependencies.platform(Libs.KoinBom))
      implementation("io.insert-koin:koin-core")
    }

    commonTest.dependencies {
      implementation("io.insert-koin:koin-test")
    }

    androidMain.dependencies {
      implementation(Libs.AndroidXCoreKtx)
      implementation(Libs.AndroidXAppCompat)
      implementation(Libs.AndroidXActivityCompose)
    }
  }
}