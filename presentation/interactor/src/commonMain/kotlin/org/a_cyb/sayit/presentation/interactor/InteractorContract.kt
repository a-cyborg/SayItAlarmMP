/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.TimeOut

interface InteractorContract {
    interface SettingsInteractorContract {
        val settingsData: SharedFlow<Result<Settings>>

        fun setTimeOut(timeOut: TimeOut, scope: CoroutineScope)
        fun setSnooze(snooze: Snooze, scope: CoroutineScope)
        fun save(scope: CoroutineScope)

        enum class ErrorType {
            INVALID_TIMEOUT_INPUT,
            INVALID_SNOOZE_INPUT,
            UNIDENTIFIED,
        }

        interface InteractorError {
            val errorType: ErrorType
            val value: Settings
        }

        data class SettingsException(
            override val errorType: ErrorType,
            override val value: Settings
        ) : InteractorError, Exception()
    }
}