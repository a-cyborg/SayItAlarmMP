/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import org.a_cyb.sayit.entity.Settings

interface SettingsInteractorContract {
    val settings: SharedFlow<Result<Settings>>

    fun load(scope: CoroutineScope)
    fun save(settings: Settings, scope: CoroutineScope)
}