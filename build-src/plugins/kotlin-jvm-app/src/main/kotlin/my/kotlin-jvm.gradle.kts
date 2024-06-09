package my

import my.util.Libs
import my.util.Versions

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(Versions.Java)
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation(Libs.SerializationJson)
}
