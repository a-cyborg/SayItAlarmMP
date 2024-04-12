/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.presentation.interactor.SettingsInteractorContract

class SettingsInteractorFake(
    private val initialSettings: Settings,
) : SettingsInteractorContract {

    private val _settings: MutableSharedFlow<Result<Settings>> = MutableSharedFlow()
    override val settings: SharedFlow<Result<Settings>> = _settings

    fun emitResult(result: Result<Settings>, scope: CoroutineScope) {
        scope.launch {
            _settings.emit(result)
        }
    }

    override fun load(scope: CoroutineScope) {
        scope.launch {
            _settings.emit(Result.success(initialSettings))
        }
    }

    override fun save(settings: Settings, scope: CoroutineScope) {
        scope.launch {
            _settings.emit(Result.success(settings))
        }
    }
}
