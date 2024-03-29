/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import org.a_cyb.sayit.entity.Snooze
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe
import kotlin.test.Test

class SetSnoozeCommandSpec {
    @Test
    fun `It fulfils Command`() {
        SetSnoozeCommand(Snooze(5)) fulfils CommandContract.Command::class
    }

    @Test
    fun `Given execute is called it runs setSnooze`() {
        // Given
        val command = SetSnoozeCommand(Snooze(5))

        // When
        command.execute(SetSnoozeReceiverFake)

        // Then
        SetSnoozeReceiverFake.hasBeenCalled mustBe true
    }
}

object SetSnoozeReceiverFake : SettingsCommandContract.SetSnooze {
    var hasBeenCalled: Boolean = false
    override fun setSnooze(snooze: Snooze) {
        hasBeenCalled = true
    }
}