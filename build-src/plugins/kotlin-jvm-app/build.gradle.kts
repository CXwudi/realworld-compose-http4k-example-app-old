plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(project(":version-catalog-util"))
  implementation(libs.pluginDep.kotlin)
  implementation(libs.pluginDep.serialization)
}