/*
 * Copyright (c) 2024 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import org.sonarqube.gradle.SonarExtension
import tech.antibytes.gradle.dependency.helper.addCustomRepositories
import tech.antibytes.gradle.dependency.helper.ensureKotlinVersion
import tech.antibytes.gradle.project.config.quality.Linter
import tech.antibytes.gradle.project.config.quality.SonarConfiguration
import tech.antibytes.gradle.project.config.repositories.Repositories.projectRepositories
import tech.antibytes.gradle.quality.api.CodeAnalysisConfiguration

plugins {
    id("tech.antibytes.gradle.setup")
    id("info.solidsoft.pitest") version "1.15.0"

    alias(antibytesCatalog.plugins.gradle.antibytes.dependencyHelper)
    alias(antibytesCatalog.plugins.gradle.antibytes.quality)
}

antibytesQuality {
    linter.set(Linter.spotless)
    codeAnalysis.set(
        CodeAnalysisConfiguration(
            configurationFiles = project.files("${project.projectDir}/detekt/config.yml"),
            baselineFile = project.file("${project.projectDir}/detekt/baseline.xml"),
            sourceFiles = project.files(project.projectDir),
        ),
    )
    qualityGate.set(SonarConfiguration(project).configuration)
}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()

        addCustomRepositories(projectRepositories)
    }

    ensureKotlinVersion()
}

allprojects {
    afterEvaluate {
        extensions.configure(SonarExtension::class.java) {
            properties {
                property("sonar.organization", "a-cyborg")
            }
        }
    }
}

subprojects {
    apply(plugin = "info.solidsoft.pitest")

    // pitest {
    //     threads.set(4)
    //     outputFormats.set(setOf("XML", "HTML"))
    //
    //     if (project.name == "entity") {
    //         failWhenNoMutations.set(false)
    //     }
    // }
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = antibytesCatalog.versions.gradle.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}
