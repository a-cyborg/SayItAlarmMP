/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.TimeOut

interface SettingsCommandContract {
    sealed interface CommandReceiver {
        fun interface SetTimeOut : CommandReceiver {
            fun setTimeOut(timeOut: TimeOut)    // [Q] Should I use Int instead of TimeOut value?
        }

        fun interface SetSnooze : CommandReceiver {
            fun setSnooze(snooze: Snooze)
        }

        fun interface Save : CommandReceiver {
            fun save()
        }
    }

    interface Command<T : CommandReceiver> {
        fun execute(receiver: T)
    }

    interface CommandExecutor {
        fun <T : CommandReceiver> runCommand(command: Command<T>)
    }
}

data class SetTimeOutCommand(val timeOut: TimeOut) :
    SettingsCommandContract.Command<SettingsCommandContract.CommandReceiver.SetTimeOut> {
    override fun execute(receiver: SettingsCommandContract.CommandReceiver.SetTimeOut) {
        receiver.setTimeOut(timeOut)
    }
}

data class SetSnoozeCommand(val snooze: Snooze) :
    SettingsCommandContract.Command<SettingsCommandContract.CommandReceiver.SetSnooze> {
    override fun execute(receiver: SettingsCommandContract.CommandReceiver.SetSnooze) {
        receiver.setSnooze(snooze)
    }
}

data object SaveCommand : SettingsCommandContract.Command<SettingsCommandContract.CommandReceiver.Save> {
    override fun execute(receiver: SettingsCommandContract.CommandReceiver.Save) {
        receiver.save()
    }
}