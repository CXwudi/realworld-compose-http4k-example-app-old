package my.util

import gradle.kotlin.dsl.accessors._a8ec36be4619c16a910e731748c33891.sourceSets
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.applyCommonFrontendDependencies() {
  sourceSets {
    commonMain.dependencies {
      implementation(Libs.Decompose)
      implementation(Libs.Koin)
    }
  }
}