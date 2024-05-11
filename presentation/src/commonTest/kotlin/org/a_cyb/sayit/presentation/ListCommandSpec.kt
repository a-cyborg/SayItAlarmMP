/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation

import kotlin.test.Test
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe

class ListCommandSpec {
    @Test
    fun `DeleteAlarmCommand fulfills command`() {
        DeleteAlarmCommand(1) fulfils CommandContract.Command::class
    }

    @Test
    fun `Given DeleteAlarmCommand execute is called it runs deleteAlarm`() {
        // Given
        val command = DeleteAlarmCommand(1)
        val receiver = object : ListCommandContract.DeleteAlarm {
            var hasBeenCalled: Boolean = false

            override fun deleteAlarm(id: Long) {
                hasBeenCalled = true
            }
        }

        // When
        command.execute(receiver)

        // Then
        receiver.hasBeenCalled mustBe true
    }
}
