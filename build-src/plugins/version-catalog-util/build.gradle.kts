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
    buildConfigField(String::class.java, "CoroutinesBom", libs.dev.coroutinesBom.map { "$it" })
    buildConfigField(String::class.java, "Decompose", libs.dev.decompose.map { "$it" })
    buildConfigField(String::class.java, "DecomposeCompose", libs.dev.decomposeCompose.map { "$it" })
    buildConfigField(String::class.java, "MviKotlin", libs.dev.mvikotlin.map { "$it" })
    buildConfigField(String::class.java, "MviKotlinMain", libs.dev.mvikotlinMain.map { "$it" })
    buildConfigField(String::class.java, "MviKotlinCoroutines", libs.dev.mvikotlinCoroutines.map { "$it" })
    buildConfigField(String::class.java, "MviKotlinLogging", libs.dev.mvikotlinLogging.map { "$it" })
    buildConfigField(String::class.java, "KoinBom", libs.dev.koinBom.map { "$it" })
    buildConfigField(String::class.java, "KotlinWrapper", libs.dev.kotlinWrapper.map { "$it" })
    buildConfigField(String::class.java, "AndroidXCoreKtx", libs.dev.androidx.coreKtx.map { "$it" })
    buildConfigField(String::class.java, "AndroidXLifecycleKtx", libs.dev.androidx.lifecycleKtx.map { "$it" })
    buildConfigField(String::class.java, "AndroidXAppCompat", libs.dev.androidx.appcompat.map { "$it" })
    buildConfigField(String::class.java, "AndroidXActivityCompose", libs.dev.androidx.activityCompose.map { "$it" })
  }
}