/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package tech.antibytes.gradle.project.config.quality

import org.gradle.api.Project
import tech.antibytes.gradle.quality.api.QualityGateConfiguration

class SonarConfiguration(project: Project) {
    val configuration = QualityGateConfiguration(
        project = project,
        projectKey = "a-cyborg_SayItAlarmMP",
        organization = "a-cyborg",
        exclude = excludes,
    )
}
