/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import tech.antibytes.gradle.configuration.apple.ensureAppleDeviceCompatibility
import tech.antibytes.gradle.configuration.sourcesets.iosx

plugins {
    alias(antibytesCatalog.plugins.gradle.antibytes.kmpConfiguration)
    alias(antibytesCatalog.plugins.gradle.antibytes.androidLibraryConfiguration)
}

val projectPackage = "org.acyb.sayit.entity"

android {
    namespace = projectPackage
}

kotlin {
    androidTarget()

    iosx()
    ensureAppleDeviceCompatibility()

    sourceSets {
        val commonMain by getting
    }
}