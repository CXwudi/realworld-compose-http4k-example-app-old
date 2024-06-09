plugins {
  id("my.kotlin-jvm")
  alias(libs.plugins.compose)
  alias(libs.plugins.kotlinCompose)
}

dependencies {
  implementation(project(":frontend-decompose-logic"))
  implementation(project(":frontend-compose-ui"))
  implementation(compose.desktop.currentOs)
}

compose.desktop {
  application {
    mainClass = "mikufan.cx.conduit.frontend.app.desktop.TestMainKt"
  }
}
