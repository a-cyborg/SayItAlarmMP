/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import tech.antibytes.gradle.configuration.sourcesets.iosx

plugins {
    alias(antibytesCatalog.plugins.gradle.antibytes.kmpConfiguration)
    alias(antibytesCatalog.plugins.gradle.antibytes.androidLibraryConfiguration)
    alias(antibytesCatalog.plugins.gradle.antibytes.coverage)
}

val projectPackage = "org.acyb.sayit.presentation"

android {
    namespace = projectPackage
}

kotlin {
    androidTarget()
    iosx()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(antibytesCatalog.common.kotlin.stdlib)
                implementation(antibytesCatalog.common.kotlinx.coroutines.core)
                implementation(antibytesCatalog.common.koin.core)

                implementation(projects.entity)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(antibytesCatalog.common.test.kotlin.core)

                implementation(antibytesCatalog.kmock)
                implementation(antibytesCatalog.kfixture)
                implementation(antibytesCatalog.testUtils.core)
                implementation(antibytesCatalog.testUtils.annotations)
                implementation(antibytesCatalog.testUtils.coroutine)
                implementation(antibytesCatalog.common.test.turbine)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(antibytesCatalog.android.test.junit.core)
                implementation(antibytesCatalog.jvm.test.kotlin.junit4)
                implementation(antibytesCatalog.android.test.ktx)
                implementation(antibytesCatalog.android.test.robolectric)
            }
        }
    }
}
