plugins {
  id("my.multiplatform-library")
  alias(libs.plugins.serialization)
}

android {
  namespace = "mikufan.cx.conduit.common"
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(libs.dev.serializationJson)
      }
    }
  }
}