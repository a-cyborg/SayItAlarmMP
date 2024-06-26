/*
 * Copyright (c) 2024 Matthias Geisler (bitPogo)
 * Copyright (c) 2024 Mima Kang
 *
 * All rights reserved.
 * Use of this source code is governed by Apache v2.0
 */

plugins {
    id(antibytesCatalog.plugins.kotlin.android.get().pluginId)

    alias(antibytesCatalog.plugins.gradle.antibytes.androidApplicationConfiguration)
    alias(antibytesCatalog.plugins.gradle.antibytes.coverage)
    alias(antibytesCatalog.plugins.test.roborazzi.gradle)

    alias(antibytesCatalog.plugins.kmock)
}

val projectPackage = "org.acyb.sayit"

kmock {
    rootPackage = projectPackage
}

android {
    namespace = projectPackage

    defaultConfig {
        applicationId = "${projectPackage}.app"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = antibytesCatalog.versions.android.compose.compiler.get()
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            matchingFallbacks.add("release")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

roborazzi {
    outputDir.set(project.layout.projectDirectory.dir("src/test/snapshots/roborazzi/images"))
}

dependencies {
    implementation(antibytesCatalog.jvm.kotlin.stdlib.jdk8)

    implementation(antibytesCatalog.android.ktx.core)
    implementation(antibytesCatalog.android.ktx.viewmodel.core)
    implementation(antibytesCatalog.android.ktx.viewmodel.compose)

    implementation(antibytesCatalog.android.appCompact.core)

    implementation(antibytesCatalog.android.ktx.activity.compose)
    implementation(antibytesCatalog.android.compose.ui.core)
    implementation(antibytesCatalog.android.material.compose.core)

    implementation(antibytesCatalog.android.compose.foundation.core)

    implementation(antibytesCatalog.android.material.core)
    implementation(antibytesCatalog.android.material.compose.icons)
    implementation(antibytesCatalog.android.material.compose.extendedIcons)
    implementation(antibytesCatalog.android.material3.core)

    implementation(projects.entity)
    implementation(projects.presentation)

    testImplementation(antibytesCatalog.testUtils.core)
    testImplementation(antibytesCatalog.testUtils.coroutine)
    testImplementation(antibytesCatalog.kfixture)
    testImplementation(antibytesCatalog.kmock)
    testImplementation(antibytesCatalog.jvm.test.junit.runtime)
    testImplementation(antibytesCatalog.jvm.test.junit.core)
    testImplementation(platform(antibytesCatalog.jvm.test.junit.bom))
    testImplementation(antibytesCatalog.jvm.test.junit.legacyEngineJunit4)
    testImplementation(antibytesCatalog.android.test.compose.core)
    testImplementation(antibytesCatalog.android.test.compose.junit4Rule)

    testImplementation(antibytesCatalog.android.test.roborazzi.compose)
    testImplementation(antibytesCatalog.android.test.roborazzi.junit)
    testImplementation(antibytesCatalog.android.test.roborazzi.core)
    testImplementation(antibytesCatalog.android.test.robolectric)
    testImplementation(antibytesCatalog.android.test.espresso.core)

    // Debug
    debugImplementation(antibytesCatalog.android.compose.ui.tooling.core)
    debugImplementation(antibytesCatalog.android.test.compose.manifest)

    androidTestImplementation(antibytesCatalog.android.test.junit.core)
    androidTestImplementation(antibytesCatalog.jvm.test.junit.junit4)
    androidTestImplementation(antibytesCatalog.android.test.compose.junit4)
    androidTestImplementation(antibytesCatalog.android.test.espresso.core)
    androidTestImplementation(antibytesCatalog.android.test.uiAutomator)

    androidTestImplementation(antibytesCatalog.testUtils.core)
    androidTestImplementation(antibytesCatalog.kfixture)
    androidTestImplementation(antibytesCatalog.kmock)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
