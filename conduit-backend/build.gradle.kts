plugins {
  alias(libs.plugins.kotlinJvm)
  alias(libs.plugins.serialization)
  application
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21)) // once Intellij support AGP 8.4.x, we can use libs.versions.java
  }
}

kotlin {
  compilerOptions {
    javaParameters = true
    freeCompilerArgs = freeCompilerArgs.get() + listOf("-Xjsr305=strict") // enable strict null check
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