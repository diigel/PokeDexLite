// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
}

// build.gradle.kts (Project Level)
allprojects {
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion("2.1.10")
            }
            // Tambahkan ini juga untuk KSP
            if (requested.group == "com.google.devtools.ksp") {
                useVersion("2.1.10-1.0.30")
            }
        }
    }
}
