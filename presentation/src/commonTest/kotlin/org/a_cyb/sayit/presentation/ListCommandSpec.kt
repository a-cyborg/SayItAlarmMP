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
    fun `LoadAlarm fulfills Command`() {
        LoadAlarmsCommand fulfils CommandContract.Command::class
    }

    @Test
    fun `Given LoadAlarmCommand execute is called it runs loadAlarm`() {
        // Given
        val command = LoadAlarmsCommand
        val receiver = object : ListCommandContract.LoadAlarms {
            var hasBeenCalled: Boolean = false

            override fun loadAlarms() {
                hasBeenCalled = true
            }
        }

        // When
        command.execute(receiver)

        // Then
        receiver.hasBeenCalled mustBe true
    }

    @Test
    fun `FetchAlarmCommand fulfills Command`() {
        FetchAlarmCommand(1) fulfils CommandContract.Command::class
    }

    @Test
    fun `Given FetchAlarmCommand execute is called it runs fetchAlarm`() {
        // Given
        val command = FetchAlarmCommand(1)
        val receiver = object : ListCommandContract.FetchAlarm {
            var hasBeenCalled: Boolean = false

            override fun fetchAlarm(id: Long) {
                hasBeenCalled = true
            }
        }

        // When
        command.execute(receiver)

        // Then
        receiver.hasBeenCalled mustBe true
    }

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

            override fun deleteAlarm(id: Long): Int {
                hasBeenCalled = true
                return 1
            }
        }

        // When
        command.execute(receiver)

        // Then
        receiver.hasBeenCalled mustBe true
    }
}
