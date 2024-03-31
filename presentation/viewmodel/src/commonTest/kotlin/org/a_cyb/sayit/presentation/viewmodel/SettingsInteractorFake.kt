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
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.interactor.InteractorContract.SettingsInteractorContract

class SettingsInteractorFake() : SettingsInteractorContract {

    private val _settingsData: MutableSharedFlow<Result<Settings>> = MutableSharedFlow()
    override val settingsData: SharedFlow<Result<Settings>> = _settingsData

    private var _invoked: InvocationType = InvocationType.NONE
    val invoked: InvocationType
        get() = _invoked

    private var results: MutableList<Result<Settings>> = mutableListOf()

    fun reset(result: Result<Settings>) {
        this.results = mutableListOf(result)
        _invoked = InvocationType.NONE
    }

    override fun setTimeOut(timeOut: TimeOut, scope: CoroutineScope) {
        _invoked = InvocationType.SET_TIME_OUT

        val settings = results.removeFirst()
            .getOrThrow()
            .copy(timeOut = timeOut)

        val result: Result<Settings> = if (timeOut.timeOut in 60..300) {
            Result.success(settings)
        } else {
            Result.failure(
                SettingsInteractorContract.SettingsException(
                    SettingsInteractorContract.ErrorType.INVALID_TIMEOUT_INPUT,
                    settings
                )
            )
        }

        scope.launch {
            _settingsData.emit(result)
        }
    }

    override fun setSnooze(snooze: Snooze, scope: CoroutineScope) {
        _invoked = InvocationType.SET_SNOOZE
    }

    override fun save(scope: CoroutineScope) {
        _invoked = InvocationType.SAVE
    }
}

enum class InvocationType {
    SET_TIME_OUT,
    SET_SNOOZE,
    SAVE,
    NONE,
}