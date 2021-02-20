buildscript {
    val kotlin_version by extra("1.4.30")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("com.android.tools.build:gradle:4.0.1")
    }
}

group = "io.pomplan"
version = "0.1"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}