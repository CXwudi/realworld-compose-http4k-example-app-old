plugins {
  `kotlin-dsl`
  alias(libs.plugins.buildConfig)
}

buildConfig {
  generateAtSync = false
  useKotlinOutput { internalVisibility = false }
  forClass(packageName = "my.util", className = "Versions") {
    buildConfigField(Int::class.java, "Java", libs.versions.java.map { it.toInt() })
  }
  forClass(packageName = "my.util", className = "Libs", ) {
    buildConfigField(String::class.java, "SerializationJson", libs.dev.serializationJson.map { "$it" })
    buildConfigField(String::class.java, "Decompose", libs.dev.decompose.map { "$it" })
    buildConfigField(String::class.java, "Koin", libs.dev.koin.map { "$it" })
  }
}