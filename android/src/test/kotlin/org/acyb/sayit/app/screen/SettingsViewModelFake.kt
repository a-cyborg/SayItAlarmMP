/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.screen

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.CommandContract
import org.a_cyb.sayit.presentation.SettingsContract

internal class SettingsViewModelFake(
    private val viewModelScope: CoroutineScope,
    private val initState: SettingsContract.SettingsState,
) : SettingsContract.SettingsViewModel {

    private val _state: MutableStateFlow<SettingsContract.SettingsState> = MutableStateFlow(initState)
    override val state: StateFlow<SettingsContract.SettingsState> = _state

    override fun setTimeOut(timeOut: TimeOut) {
        viewModelScope.launch {
            _state.update {
                (_state as SettingsContract.SettingsStateWithContent)
                    .copy(timeOut = SettingsContract.ValidTimeInput(timeOut.timeOut))
            }
        }
    }

    override fun setSnooze(snooze: Snooze) {}

    override fun setTheme(theme: Theme) {}

    override fun save() {}

    override fun <T : CommandContract.CommandReceiver> runCommand(command: CommandContract.Command<T>) {}
}
