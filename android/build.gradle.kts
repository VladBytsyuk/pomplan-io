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

    implementation("androidx.compose.ui:ui:1.0.0-beta01")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta01")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.0-beta01")
    // Material Design
    implementation("androidx.compose.material:material:1.0.0-beta01")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta01")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta01")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta01")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.0-beta01")


    implementation("androidx.navigation:navigation-compose:1.0.0-alpha10")
    implementation("androidx.navigation:navigation-runtime-ktx:2.3.5")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-beta01")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "io.pomplan.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 2
        versionName = "0.2"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.4.30"
        kotlinCompilerExtensionVersion = "1.0.0-beta01"
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }