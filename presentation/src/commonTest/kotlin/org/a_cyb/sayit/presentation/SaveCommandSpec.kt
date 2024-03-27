/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe
import kotlin.test.Test

class SaveCommandSpec {
    @Test
    fun `It fulfils Command`() {
        SaveCommand fulfils SettingsCommandContract.Command::class
    }

    @Test
    fun `Given execute is called it runs setTimeOut`() {
        // Given
        val command = SaveCommand

        // When
        command.execute(SaveCommandReceiverFake)

        // Then
        SaveCommandReceiverFake.hasBeenCalled mustBe true
    }
}

object SaveCommandReceiverFake : SettingsCommandContract.CommandReceiver.Save {
    var hasBeenCalled: Boolean = false
    override fun save() {
        hasBeenCalled = true
    }
}
