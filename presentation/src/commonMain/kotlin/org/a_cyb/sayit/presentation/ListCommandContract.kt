/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

interface ListCommandContract {
    fun interface LoadAlarms : CommandContract.CommandReceiver {
        fun loadAlarms()
    }

    fun interface FetchAlarm : CommandContract.CommandReceiver {
        fun fetchAlarm(id: Long)
    }

    fun interface DeleteAlarm : CommandContract.CommandReceiver {
        fun deleteAlarm(id: Long): Int
    }
}

object LoadAlarmsCommand : CommandContract.Command<ListCommandContract.LoadAlarms> {
    override fun execute(receiver: ListCommandContract.LoadAlarms) {
        receiver.loadAlarms()
    }
}

data class FetchAlarmCommand(val id: Long) : CommandContract.Command<ListCommandContract.FetchAlarm> {
    override fun execute(receiver: ListCommandContract.FetchAlarm) {
        receiver.fetchAlarm(id)
    }
}

data class DeleteAlarmCommand(val id: Long) : CommandContract.Command<ListCommandContract.DeleteAlarm> {
    override fun execute(receiver: ListCommandContract.DeleteAlarm) {
        receiver.deleteAlarm(id)
    }
}
