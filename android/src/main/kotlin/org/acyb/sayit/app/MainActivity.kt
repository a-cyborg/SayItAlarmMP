/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.rememberCoroutineScope
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
import org.acyb.sayit.app.screen.SettingsScreen
import org.acyb.sayit.app.token.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            if (isSystemInDarkTheme()) {
                Color.useDarkTheme()
            } else {
                Color.useLightTheme()
            }
            val scope = rememberCoroutineScope()
            Surface(color = Color.surface.standard) {
                val settingsStateWithContent = SettingsContract.SettingsStateWithContent(
                    timeOut = SettingsContract.ValidTimeInput(180),
                    snooze = SettingsContract.ValidTimeInput(15),
                    theme = Theme.LIGHT,
                )
                Column {
                    SettingsScreen(
                        viewModel = SettingsViewModelFake(
                            scope,
                            settingsStateWithContent,
                        ),
                    )
                }
            }
        }
    }
}

internal class SettingsViewModelFake(
    private val viewModelScope: CoroutineScope,
    private val initState: SettingsContract.SettingsState,
) : SettingsContract.SettingsViewModel {

    private val _state: MutableStateFlow<SettingsContract.SettingsState> = MutableStateFlow(initState)
    override val state: StateFlow<SettingsContract.SettingsState> = _state

    var testTimeOutIsCalled = false

    override fun setTimeOut(timeOut: TimeOut) {
        Log.d("[***]", "setTimeOut: Fake setTimeOut called")
        testTimeOutIsCalled = true

        viewModelScope.launch {
            _state.update {
                (_state.value as SettingsContract.SettingsStateWithContent)
                    .copy(timeOut = SettingsContract.ValidTimeInput(timeOut.timeOut))
            }
        }
    }

    override fun setSnooze(snooze: Snooze) {}

    override fun setTheme(theme: Theme) {}

    override fun save() {}

    override fun <T : CommandContract.CommandReceiver> runCommand(command: CommandContract.Command<T>) {}
}
