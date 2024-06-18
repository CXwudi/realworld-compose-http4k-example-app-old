plugins {
  alias(libs.plugins.kotlinJvm)
  alias(libs.plugins.serialization)
  application
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21)) // once Intellij support AGP 8.4.x,
  // we can set java version in libs.versions.java to 21 and use it here
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
  implementation(libs.dev.koin)

  implementation(platform(libs.dev.http4kBom))
  implementation("org.http4k:http4k-core")
  implementation("org.http4k:http4k-format-kotlinx-serialization")
  implementation("org.http4k:http4k-metrics-micrometer")

  testImplementation("org.http4k:http4k-testing-approval")
  testImplementation("org.http4k:http4k-testing-hamkrest")
  testImplementation("org.http4k:http4k-testing-kotest")
  testImplementation(platform(libs.dev.junitBom))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testImplementation("org.junit.jupiter:junit-jupiter-engine")
}

application {
  mainClass.set("mikufan.cx.conduit.backend.MainKt")
}

tasks.test {
  useJUnitPlatform()
}
