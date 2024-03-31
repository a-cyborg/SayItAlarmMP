/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.CommandContract
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.interactor.InteractorContract.SettingsInteractorContract

internal class SettingsViewModel(
    private val interactor: SettingsInteractorContract,
) : SettingsContract.SettingsViewModel, Vm() {

    private var _settingsState: MutableStateFlow<SettingsContract.SettingsState> =
        MutableStateFlow(SettingsContract.Initial)
    override val settingsState: StateFlow<SettingsContract.SettingsState> = _settingsState

    init {
        interactor.settingsData
            .onEach(::updateState)
            .launchIn(scope)
    }

    private fun updateState(settingsResult: Result<Settings>) {
        _settingsState.update { settingsResult.toUIState() }
    }

    private fun Result<Settings>.toUIState(): SettingsContract.SettingsState {
        return if (isFailure) {
            toError()
        } else {
            toLoaded()
        }
    }

    private fun Result<Settings>.toLoaded(): SettingsContract.SettingsState {
        return SettingsContract.Loaded(getOrThrow().toUISettingsData())
    }

    private fun Settings.toUISettingsData(): SettingsContract.UISettingsData {
        return SettingsContract.UISettingsData(
            timeOut = SettingsContract.ValidTimeInput(timeOut.timeOut),
            snooze = SettingsContract.ValidTimeInput(snooze.snooze),
            theme = theme,
        )
    }

    private fun Result<Settings>.toError(): SettingsContract.SettingsState {
        val exception = exceptionOrNull()

        return if (exception == null || !exception.isKnownError()) {
            SettingsContract.Error
        } else {
            SettingsContract.ErrorWithDetail(
                (exception as SettingsInteractorContract.SettingsException)
                    .toUISettingsData()
            )
        }
    }

    private fun Throwable.isKnownError(): Boolean {
        return this is SettingsInteractorContract.InteractorError &&
            errorType != SettingsInteractorContract.ErrorType.UNIDENTIFIED
    }

    private fun SettingsInteractorContract.SettingsException.toUISettingsData():
        SettingsContract.UISettingsData {
        val data = value.toUISettingsData()

        return when (errorType) {
            SettingsInteractorContract.ErrorType.INVALID_TIMEOUT_INPUT -> {
                val timeOut = data.timeOut.value
                data.copy(timeOut = SettingsContract.InvalidTimeInput(timeOut, ""))
            }

            SettingsInteractorContract.ErrorType.INVALID_SNOOZE_INPUT -> {
                val snooze = data.snooze.value
                data.copy(snooze = SettingsContract.InvalidTimeInput(snooze, ""))
            }

            else -> data
        }
    }

    override fun setTimeOut(timeOut: TimeOut) {
        scope.launch {
            interactor.setTimeOut(timeOut, this)
        }
    }

    override fun setSnooze(snooze: Snooze) {}

    override fun save() {}

    override fun <T : CommandContract.CommandReceiver> runCommand(command: CommandContract.Command<T>) {
        @Suppress("UNCHECKED_CAST")
        command.execute(this as T)
    }
}