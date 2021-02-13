import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.2.0-build132"
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "io.pomplan"
version = "0.1"

repositories {
    google()
}


kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
            }
        }
        val commonTest by getting {
            dependencies {
                val kotestVersion = "4.4.1"
                implementation("io.kotest:kotest-framework-engine:$kotestVersion")
                implementation("io.kotest:kotest-assertions-core:$kotestVersion")
                implementation("io.kotest:kotest-property:$kotestVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.2.0")
                api("androidx.core:core-ktx:1.3.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}