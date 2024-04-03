/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.TimeOut

interface SettingsCommandContract {
    fun interface SetTimeOut : CommandContract.CommandReceiver {
        fun setTimeOut(timeOut: TimeOut)
    }

    fun interface SetSnooze : CommandContract.CommandReceiver {
        fun setSnooze(snooze: Snooze)
    }

    fun interface Save : CommandContract.CommandReceiver {
        fun save()
    }
}

data class SetTimeOutCommand(val timeOut: TimeOut) : CommandContract.Command<SettingsCommandContract.SetTimeOut> {
    override fun execute(receiver: SettingsCommandContract.SetTimeOut) {
        receiver.setTimeOut(timeOut)
    }
}

data class SetSnoozeCommand(val snooze: Snooze) : CommandContract.Command<SettingsCommandContract.SetSnooze> {
    override fun execute(receiver: SettingsCommandContract.SetSnooze) {
        receiver.setSnooze(snooze)
    }
}

data object SaveCommand : CommandContract.Command<SettingsCommandContract.Save> {
    override fun execute(receiver: SettingsCommandContract.Save) {
        receiver.save()
    }
}