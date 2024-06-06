plugins {
  alias(libs.plugins.kotlinJvm)
  alias(libs.plugins.serialization)
  application
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("mikufan.cx.conduit:conduit-common")
  implementation(libs.dev.serializationJson)
}

application {
  mainClass.set("mikufan.cx.conduit.backend.MainKt")
}