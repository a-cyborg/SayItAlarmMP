/*
 * Copyright (c) 2024 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import tech.antibytes.gradle.dependency.helper.GradleCompositeBuilds
import tech.antibytes.gradle.dependency.helper.addCustomRepositories
import tech.antibytes.gradle.dependency.helper.ensureKotlinVersion
import tech.antibytes.gradle.project.config.quality.Linter
import tech.antibytes.gradle.project.config.quality.SonarConfiguration
import tech.antibytes.gradle.project.config.quality.StableApi
import tech.antibytes.gradle.project.config.repositories.Repositories.projectRepositories
import tech.antibytes.gradle.quality.api.CodeAnalysisConfiguration

plugins {
    id("tech.antibytes.gradle.setup")

    alias(antibytesCatalog.plugins.gradle.antibytes.dependencyHelper)
    alias(antibytesCatalog.plugins.gradle.antibytes.quality)
}

antibytesQuality {
    linter.set(Linter.spotless)
    codeAnalysis.set(CodeAnalysisConfiguration(project = project))
    stableApi.set(StableApi.api)
    qualityGate.set(SonarConfiguration(project).configuration)
}

allprojects {
    // group = "com.sonarqube"

    repositories {
        mavenCentral()
        google()
        jcenter()
        // maven {
        //     url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        // }
        addCustomRepositories(projectRepositories)
    }

    ensureKotlinVersion()
}

GradleCompositeBuilds.configure(project)
evaluationDependsOnChildren()

tasks.named<Wrapper>("wrapper") {
    gradleVersion = antibytesCatalog.versions.gradle.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}
