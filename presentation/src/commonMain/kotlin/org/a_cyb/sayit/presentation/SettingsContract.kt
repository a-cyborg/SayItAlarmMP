/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import kotlinx.coroutines.flow.StateFlow
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.presentation.SettingsCommandContract.Save
import org.a_cyb.sayit.presentation.SettingsCommandContract.SetSnooze
import org.a_cyb.sayit.presentation.SettingsCommandContract.SetTheme
import org.a_cyb.sayit.presentation.SettingsCommandContract.SetTimeOut

interface SettingsContract {
    interface SettingsViewModel : SetTimeOut, SetSnooze, SetTheme, Save, CommandContract.CommandExecutor {
        val state: StateFlow<SettingsState>
    }

    interface SettingsState
    data object Initial : SettingsState
    data class Error(val message: String) : SettingsState

    interface TimeInput {
        val input: Int
    }

    data class ValidTimeInput(override val input: Int) : TimeInput
    data class InvalidTimeInput(override val input: Int) : TimeInput

    data class SettingsStateWithContent(
        val timeOut: TimeInput,
        val snooze: TimeInput,
        val theme: Theme,
    ) : SettingsState
}
