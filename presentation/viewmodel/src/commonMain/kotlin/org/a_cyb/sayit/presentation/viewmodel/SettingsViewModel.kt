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
import org.a_cyb.sayit.presentation.SettingsContract.SettingsError
import org.a_cyb.sayit.presentation.SettingsContract.SettingsError.INITIAL_SETTINGS_UNRESOLVED
import org.a_cyb.sayit.presentation.SettingsContract.SettingsError.UNABLE_RESOLVE_SETTINGS_STATE_WITH_CONTENT
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
            .onEach(::initState)
            .launchIn(scope)
    }

    private fun initState(settingsResult: Result<Settings>) {
        settingsResult
            .onSuccess { settings -> _state.update { settings.toStateWithContent() } }
            .onFailure { throwable -> _state.update { throwable.toErrorState(INITIAL_SETTINGS_UNRESOLVED) } }
    }

    private fun Settings.toStateWithContent(): SettingsStateWithContent =
        SettingsStateWithContent(
            timeOut = timeOut.toValidatedTimeInput(),
            snooze = snooze.toValidatedTimeInput(),
            theme = theme,
        )

    private fun Throwable.toErrorState(settingsError: SettingsError): SettingsState =
        Error(detail = "$settingsError message=$message, cause=$cause")

    @Suppress("MagicNumber")
    private fun TimeOut.toValidatedTimeInput(): TimeInput =
        if (timeOut !in (30..300)) {
            InvalidTimeInput(timeOut)
        } else {
            ValidTimeInput(timeOut)
        }

    @Suppress("MagicNumber")
    private fun Snooze.toValidatedTimeInput(): TimeInput =
        if (snooze !in (5..60)) {
            InvalidTimeInput(snooze)
        } else {
            ValidTimeInput(snooze)
        }

    override fun setTimeOut(timeOut: TimeOut) {
        updateContentOrError { copy(timeOut = timeOut.toValidatedTimeInput()) }
    }

    override fun setSnooze(snooze: Snooze) {
        updateContentOrError { copy(snooze = snooze.toValidatedTimeInput()) }
    }

    override fun setTheme(theme: Theme) {
        updateContentOrError { copy(theme = theme) }
    }

    private fun updateContentOrError(updateAction: SettingsStateWithContent.() -> SettingsStateWithContent) {
        _state.update {
            (state.value as? SettingsStateWithContent)
                ?.updateAction()
                ?: IllegalStateException().toErrorState(UNABLE_RESOLVE_SETTINGS_STATE_WITH_CONTENT)
        }
    }

    override fun save() {
        if (state.value is SettingsStateWithContent) {
            interactor.save(
                settings = (state.value as SettingsStateWithContent).toSettings(),
                scope = scope,
            )
        }
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
