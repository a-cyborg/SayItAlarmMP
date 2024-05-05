/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.CommandContract
import org.a_cyb.sayit.presentation.SettingsContract

@Suppress("MatchingDeclarationName", "EmptyFunctionBlock")
class SettingsViewModelFake(
    initState: SettingsContract.SettingsState,
) : SettingsContract.SettingsViewModel, ViewModel() {

    private val _state: MutableStateFlow<SettingsContract.SettingsState> = MutableStateFlow(initState)
    override val state: StateFlow<SettingsContract.SettingsState> = _state

    override fun setTimeOut(timeOut: TimeOut) {}
    override fun setSnooze(snooze: Snooze) {}
    override fun setTheme(theme: Theme) {}
    override fun save() {}

    override fun <T : CommandContract.CommandReceiver> runCommand(command: CommandContract.Command<T>) {}
}

@Preview
@Composable
fun SettingsDialogPreview() {
    val settingsStateWithContent = SettingsContract.SettingsStateWithContent(
        timeOut = SettingsContract.ValidTimeInput(180),
        snooze = SettingsContract.ValidTimeInput(15),
        theme = Theme.LIGHT,
    )

    SettingsScreen(
        viewModel = SettingsViewModelFake(settingsStateWithContent),
    )
}
