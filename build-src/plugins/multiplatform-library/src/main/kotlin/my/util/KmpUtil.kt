package my.util

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


fun KotlinMultiplatformExtension.setIosBaseName(baseName: String) {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            this.baseName = baseName
        }
    }

}