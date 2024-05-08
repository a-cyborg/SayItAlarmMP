/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut

interface SettingsCommandContract {
    fun interface SetTimeOut : CommandContract.CommandReceiver {
        fun setTimeOut(timeOut: TimeOut)
    }

    fun interface SetSnooze : CommandContract.CommandReceiver {
        fun setSnooze(snooze: Snooze)
    }

    fun interface SetTheme : CommandContract.CommandReceiver {
        fun setTheme(theme: Theme)
    }
}

data class SetTimeOutCommand(val timeOut: Int) : CommandContract.Command<SettingsCommandContract.SetTimeOut> {
    override fun execute(receiver: SettingsCommandContract.SetTimeOut) {
        receiver.setTimeOut(TimeOut(timeOut))
    }
}

data class SetSnoozeCommand(val snooze: Int) : CommandContract.Command<SettingsCommandContract.SetSnooze> {
    override fun execute(receiver: SettingsCommandContract.SetSnooze) {
        receiver.setSnooze(Snooze(snooze))
    }
}

data class SetThemeCommand(val theme: Theme) : CommandContract.Command<SettingsCommandContract.SetTheme> {
    override fun execute(receiver: SettingsCommandContract.SetTheme) {
        receiver.setTheme(theme)
    }
}
