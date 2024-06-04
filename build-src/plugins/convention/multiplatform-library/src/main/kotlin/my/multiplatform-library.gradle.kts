package my

import my.util.Versions

plugins {
  kotlin("multiplatform")
  id("com.android.library")
  id("org.jetbrains.compose")
  kotlin("plugin.compose")
}

kotlin {
  androidTarget()
  jvm()
  js(IR) {
    browser()
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
      isStatic = true
    }
  }

  applyDefaultHierarchyTemplate()

  sourceSets {
    val commonJvmMain by creating {
      dependsOn(commonMain.get())
    }

    jvmMain.get().dependsOn(commonJvmMain)
    androidMain.get().dependsOn(commonJvmMain)
  }
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(Versions.JavaVersion)
  }
}

android {
  compileSdk = 34
  defaultConfig {
    minSdk = 26
  }
  // jvm version is covered by java toolchain above
}
