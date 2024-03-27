/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import kotlinx.coroutines.flow.StateFlow
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.presentation.SettingsCommandContract.*
import org.a_cyb.sayit.presentation.SettingsCommandContract.CommandReceiver.*

interface SettingsContract {

    interface SettingsViewModel : SetTimeOut, SetSnooze, Save, CommandExecutor {
        val settingsState: StateFlow<SettingsState>
    }

    interface UserTimeInput {
        val value: String
    }

    data class ValidUserTimeInput(override val value: String) : UserTimeInput
    data class InvalidUserTimeInput(
        override val value: String,
        val explanation: String,
    ) : UserTimeInput

    interface SettingsState
    data object Initial : SettingsState
    data object Error : SettingsState
    sealed interface SettingsStateWithContent : SettingsState {
        val timeOut: UserTimeInput
        val snooze: UserTimeInput
        val theme: Theme
    }
}