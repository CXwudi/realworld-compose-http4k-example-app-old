plugins {
  `kotlin-dsl`
  alias(libs.plugins.buildConfig)
}

dependencies {
  implementation(libs.pluginDep.kotlin)
  implementation(libs.pluginDep.android)
  implementation(libs.pluginDep.compose)
  implementation(libs.pluginDep.kotlinCompose)
}

buildConfig {
  generateAtSync = false
  forClass(packageName = "my.util", className = "Versions") {
    buildConfigField(Int::class.java, "JavaVersion", libs.versions.java.map { it.toInt() })
  }
}