plugins {
  `kotlin-dsl`
  alias(libs.plugins.buildConfig)
}

dependencies {
  implementation(libs.pluginDep.kotlin)
  implementation(libs.pluginDep.android)
  implementation(libs.pluginDep.compose)
  implementation(libs.pluginDep.kotlinCompose)
  implementation(libs.pluginDep.serialization)
}

buildConfig {
  generateAtSync = false
  forClass(packageName = "my.util", className = "Versions") {
    buildConfigField(Int::class.java, "Java", libs.versions.java.map { it.toInt() })
  }
  forClass(packageName = "my.util", className = "Libs") {
    buildConfigField(String::class.java, "SerializationJson", libs.dev.serializationJson.map { "$it" })
  }
}