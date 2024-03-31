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
import org.a_cyb.sayit.presentation.SettingsCommandContract.SetTimeOut

interface SettingsContract {
    interface SettingsViewModel : SetTimeOut, SetSnooze, Save, CommandContract.CommandExecutor {
        val settingsState: StateFlow<SettingsState>
    }

    interface SettingsState
    data object Initial : SettingsState
    data object Error : SettingsState

    sealed interface SettingsStateWithContent : SettingsState {
        val data: UISettingsData
    }

    data class Loaded(override val data: UISettingsData) : SettingsStateWithContent
    data class ErrorWithDetail(override val data: UISettingsData) : SettingsStateWithContent

    data class UISettingsData(
        val timeOut: TimeInput,
        val snooze: TimeInput,
        val theme: Theme,
    )

    interface TimeInput {
        val value: Int
    }

    data class ValidTimeInput(override val value: Int) : TimeInput
    data class InvalidTimeInput(override val value: Int, val explanation: String) : TimeInput
}