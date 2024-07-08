plugins {
  id("my.kmp-frontend-library")
  alias(libs.plugins.sqldelight)
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

    // and platform specific dependencies only used in this module
    androidMain.dependencies {
      implementation(libs.dev.sqldelightDriver.android)
    }

    jvmMain.dependencies {
      implementation(libs.dev.sqldelightDriver.jvm)
    }

    jsMain.dependencies {
      implementation(libs.dev.sqldelightDriver.js)
      implementation(npm("sql.js", "1.6.2"))
      implementation(devNpm("copy-webpack-plugin", "9.1.0"))
    }

    iosMain.dependencies {
      implementation(libs.dev.sqldelightDriver.ios)
    }
  }
}

sqldelight {
  databases {
    create("Database") {
      packageName = "mikufan.cx.conduit.frontend.logic.repo.db"
      generateAsync = true
      deriveSchemaFromMigrations = true
      schemaOutputDirectory = file("src/commonMain/sqldelight/databases")
      verifyMigrations = true
    }
  }
}