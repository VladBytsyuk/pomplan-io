plugins {
    id("com.android.application")
    kotlin("android")
}

group = "io.pomplan"
version = "0.2"

repositories {
    google()
}

dependencies {
    implementation(project(":common"))
    implementation(project(":android:ui_kit"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.material:material:1.3.0")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "io.pomplan.android"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 2
        versionName = "0.2"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }