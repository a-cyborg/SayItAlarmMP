/*
 * Copyright (c) 2024 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

import tech.antibytes.gradle.dependency.helper.addCustomRepositories
import tech.antibytes.gradle.dependency.helper.ensureKotlinVersion
import tech.antibytes.gradle.project.config.repositories.Repositories.projectRepositories
import tech.antibytes.gradle.quality.api.CodeAnalysisConfiguration

plugins {
    id("tech.antibytes.gradle.setup")
    id("org.sonarqube") version "4.4.1.3373"

    alias(antibytesCatalog.plugins.gradle.antibytes.dependencyHelper)
    alias(antibytesCatalog.plugins.gradle.antibytes.quality)
}

antibytesQuality {
    codeAnalysis.set(CodeAnalysisConfiguration(project = project))
    // qualityGate.set(SonarConfiguration(project).configuration)
    // stableApi.set(StableApi.api)
}

allprojects {
    group = "com.sonarqube"

    repositories {
        mavenCentral()
        google()
        jcenter()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
        addCustomRepositories(projectRepositories)
    }

    ensureKotlinVersion()
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = antibytesCatalog.versions.gradle.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}

subprojects {
    apply(plugin = "org.sonarqube")

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    sonar {
        properties {
            property("sonar.sources", "src/main/kotlin")
            property("sonar.tests", "src/commonTest/kotlin")
            property("sonar.coverage.jacoco.xmlReportPaths", "/reports/jacoco/**/*.xml")
        }
    }
}

project(":android") {
    sonar {
        properties {
            property("sonar.tests", "src/test/kotlin")
        }
    }
}


project(":entity") {
    sonar {
        isSkipProject = true
    }
}

sonar {
    properties {
        property("sonar.projectKey", "a-cyborg_SayItAlarmMP")
        property("sonar.organization", "a-cyborg")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}