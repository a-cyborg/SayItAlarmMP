/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import org.a_cyb.sayit.entity.TimeOut
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe
import kotlin.test.Test

class SetTimeOutCommandSpec {
    @Test
    fun `It fulfils Command`() {
        SetTimeOutCommand(TimeOut(33)) fulfils SettingsCommandContract.Command::class
    }

    @Test
    fun `Given execute is called it runs setTimeOut`() {
        // Given
        val command = SetTimeOutCommand(TimeOut(33))

        // When
        command.execute(SetTimeOutReceiverFake)

        // Then
        SetTimeOutReceiverFake.hasBeenCalled mustBe true
    }
}

object SetTimeOutReceiverFake : SettingsCommandContract.CommandReceiver.SetTimeOut {
    var hasBeenCalled: Boolean = false
    override fun setTimeOut(timeOut: TimeOut) {
        hasBeenCalled = true
    }
}
