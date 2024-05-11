/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.a_cyb.sayit.entity.Alarm
import org.a_cyb.sayit.presentation.CommandContract
import org.a_cyb.sayit.presentation.ListContract
import org.a_cyb.sayit.presentation.ListContract.AlarmInfo
import org.a_cyb.sayit.presentation.ListContract.Error
import org.a_cyb.sayit.presentation.ListContract.Initial
import org.a_cyb.sayit.presentation.ListContract.ListError
import org.a_cyb.sayit.presentation.ListContract.ListError.DELETE_ALARM_FAILED
import org.a_cyb.sayit.presentation.ListContract.ListError.LOAD_ALARMS_FAILED
import org.a_cyb.sayit.presentation.ListContract.ListState
import org.a_cyb.sayit.presentation.ListContract.Loaded
import org.a_cyb.sayit.presentation.interactor.ListTaskerContract

internal class ListViewModel(
    private val tasker: ListTaskerContract,
) : ListContract.ListViewModel, ViewModel() {

    private val _state: MutableStateFlow<ListState> = MutableStateFlow(Initial)
    override val state: StateFlow<ListState> = _state

    init {
        tasker.alarms
            .onEach(::updateState)
            .launchIn(scope)
    }

    private fun updateState(result: Result<List<Alarm>>) {
        result
            .onSuccess { alarms -> alarms.updateToLoaded() }
            .onFailure { throwable -> throwable.updateToError(LOAD_ALARMS_FAILED) }
    }

    private fun List<Alarm>.updateToLoaded() {
        _state.update {
            Loaded(this.map { it.toAlarmInfo() })
        }
    }

    private fun Alarm.toAlarmInfo() =
        AlarmInfo(id, combinedMinutes, weeklyRepeat, label.label, enabled)

    private fun Throwable.updateToError(listError: ListError) {
        _state.update {
            Error(listError, message = this.message ?: "")
        }
    }

    override fun deleteAlarm(id: Long) {
        if (_state.value !is Loaded) {
            IllegalStateException().updateToError(DELETE_ALARM_FAILED)
        } else {
            tasker.deleteAlarm(id, scope)
        }
    }

    override fun <T : CommandContract.CommandReceiver> runCommand(command: CommandContract.Command<T>) {
        @Suppress("UNCHECKED_CAST")
        command.execute(this as T)
    }
}
