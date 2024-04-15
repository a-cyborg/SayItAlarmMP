/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.CommandContract
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.Error
import org.a_cyb.sayit.presentation.SettingsContract.Initial
import org.a_cyb.sayit.presentation.SettingsContract.InvalidTimeInput
import org.a_cyb.sayit.presentation.SettingsContract.SettingsState
import org.a_cyb.sayit.presentation.SettingsContract.SettingsStateWithContent
import org.a_cyb.sayit.presentation.SettingsContract.TimeInput
import org.a_cyb.sayit.presentation.SettingsContract.ValidTimeInput
import org.a_cyb.sayit.presentation.interactor.SettingsInteractorContract

internal class SettingsViewModel(
    private val interactor: SettingsInteractorContract,
) : SettingsContract.SettingsViewModel, ViewModel() {

    private var _state: MutableStateFlow<SettingsState> = MutableStateFlow(Initial)
    override val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        interactor.settings
            .onEach(::updateState)
            .launchIn(scope)
    }

    private fun updateState(settingsResult: Result<Settings>) {
        _state.update { settingsResult.toState() }
    }

    private fun Result<Settings>.toState(): SettingsState =
        if (isFailure) {
            exceptionOrNull().toError()
        } else {
            getOrThrow().toStateWithContent()
        }

    private fun Throwable?.toError(): SettingsState =
        Error(message = this?.message ?: "")

    private fun Settings.toStateWithContent(): SettingsStateWithContent =
        SettingsStateWithContent(
            timeOut = timeOut.toValidatedTimeInput(),
            snooze = snooze.toValidatedTimeInput(),
            theme = theme,
        )

    private fun TimeOut.toValidatedTimeInput(): TimeInput =
        if (timeOut !in (30..300)) {
            InvalidTimeInput(timeOut)
        } else {
            ValidTimeInput(timeOut)
        }

    private fun Snooze.toValidatedTimeInput(): TimeInput =
        if (snooze !in (5..60)) {
            InvalidTimeInput(snooze)
        } else {
            ValidTimeInput(snooze)
        }

    private fun resolveSettingsStateWithContent(): SettingsStateWithContent =
        if (state.value !is SettingsStateWithContent) {
            throw IllegalStateException("Unable to resolve the settings state with content.")
        } else {
            state.value as SettingsStateWithContent
        }

    override fun setTimeOut(timeOut: TimeOut) {
        _state.update {
            resolveSettingsStateWithContent()
                .copy(timeOut = timeOut.toValidatedTimeInput())
        }
    }

    override fun setSnooze(snooze: Snooze) {
        _state.update {
            resolveSettingsStateWithContent()
                .copy(snooze = snooze.toValidatedTimeInput())
        }
    }

    override fun setTheme(theme: Theme) {
        _state.update {
            resolveSettingsStateWithContent()
                .copy(theme = theme)
        }
    }

    override fun save() {
        interactor.save(
            settings = resolveSettingsStateWithContent().toSettings(),
            scope = scope,
        )
    }

    private fun SettingsStateWithContent.toSettings() =
        Settings(
            timeOut = TimeOut(timeOut.input),
            snooze = Snooze(snooze.input),
            theme = theme,
        )

    override fun <T : CommandContract.CommandReceiver> runCommand(command: CommandContract.Command<T>) {
        @Suppress("UNCHECKED_CAST")
        command.execute(this as T)
    }
}
