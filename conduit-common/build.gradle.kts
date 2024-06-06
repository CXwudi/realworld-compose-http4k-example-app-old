plugins {
  id("my.kmp-library")
  alias(libs.plugins.serialization)
}

group = "mikufan.cx.conduit"

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