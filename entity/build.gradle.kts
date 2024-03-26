import tech.antibytes.gradle.configuration.sourcesets.iosx

plugins {
    alias(antibytesCatalog.plugins.gradle.antibytes.kmpConfiguration)
    alias(antibytesCatalog.plugins.gradle.antibytes.androidLibraryConfiguration)
    alias(antibytesCatalog.plugins.gradle.antibytes.coverage)
}

val projectPackage = "org.acyb.sayit.entity"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

android {
    namespace = projectPackage
}

kotlin {
    androidTarget()

    jvm()

    iosx()

    sourceSets {
        val commonMain by getting
    }
}