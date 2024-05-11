/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

interface ListCommandContract {
    fun interface DeleteAlarm : CommandContract.CommandReceiver {
        fun deleteAlarm(id: Long)
    }
}

data class DeleteAlarmCommand(val id: Long) : CommandContract.Command<ListCommandContract.DeleteAlarm> {
    override fun execute(receiver: ListCommandContract.DeleteAlarm) {
        receiver.deleteAlarm(id)
    }
}
