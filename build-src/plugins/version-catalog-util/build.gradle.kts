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
    buildConfigField(String::class.java, "KoinBom", libs.dev.koinBom.map { "$it" })
    buildConfigField(String::class.java, "AndroidXAppCompat", libs.dev.androidx.appcompat.map { "$it" })
    buildConfigField(String::class.java, "AndroidXCoreKtx", libs.dev.androidx.coreKtx.map { "$it" })
    buildConfigField(String::class.java, "AndroidXActivityCompose", libs.dev.androidx.activityCompose.map { "$it" })
  }
}