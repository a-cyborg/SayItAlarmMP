/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import kotlinx.coroutines.flow.StateFlow
import org.a_cyb.sayit.entity.CombinedMinutes
import org.a_cyb.sayit.entity.WeeklyRepeat
import org.a_cyb.sayit.presentation.ListCommandContract.DeleteAlarm

interface ListContract {
    interface ListViewModel : DeleteAlarm, CommandContract.CommandExecutor {
        val state: StateFlow<ListState>
    }

    interface ListState
    data object Initial : ListState
    data class Loaded(val alarms: List<AlarmInfo>) : ListState
    data class Error(val error: ListError, val message: String) : ListState

    enum class ListError {
        LOAD_ALARMS_FAILED,
        DELETE_ALARM_FAILED,
    }

    data class AlarmInfo(
        val id: Long,
        val combinedMinutes: CombinedMinutes,
        val weeklyRepeat: WeeklyRepeat,
        val label: String,
        val enabled: Boolean,
    )
}
