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
    id("org.owasp.dependencycheck") version "9.1.0" apply false

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
    apply(plugin = "org.owasp.dependencycheck")

    afterEvaluate {
        extensions.configure(SonarExtension::class.java) {
            properties {
                property("sonar.organization", "a-cyborg")
                property("sonar.exclude", setOf("**/*Contract.kt"))
            }
        }
    }
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = antibytesCatalog.versions.gradle.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}

configure<org.owasp.dependencycheck.gradle.extension.DependencyCheckExtension> {
    format = org.owasp.dependencycheck.reporting.ReportGenerator.Format.ALL.toString()
    outputDirectory = "$buildDir/security-report"
}
