plugins {
    id("com.android.application")
    kotlin("android")
}

group = "io.pomplan"
version = "0.2"

repositories {
    google()
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
}

dependencies {
    implementation(project(":common"))
    implementation(project(":android:ui_kit"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.compose.ui:ui:1.0.0-beta07")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta07")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta07")
    implementation("androidx.compose.material:material:1.0.0-beta07")
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta07")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta07")
    implementation("androidx.activity:activity-compose:1.3.0-alpha08")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha05")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta07")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-beta07")


    implementation("androidx.navigation:navigation-compose:1.0.0-alpha10")
    implementation("androidx.navigation:navigation-runtime-ktx:2.3.5")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.useIR = true
}