/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import app.cash.turbine.test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.a_cyb.sayit.entity.Alarm
import org.a_cyb.sayit.entity.CombinedMinutes
import org.a_cyb.sayit.entity.Fri
import org.a_cyb.sayit.entity.Label
import org.a_cyb.sayit.entity.Mon
import org.a_cyb.sayit.entity.Ringtone
import org.a_cyb.sayit.entity.Wed
import org.a_cyb.sayit.entity.WeeklyRepeat
import org.a_cyb.sayit.presentation.DeleteAlarmCommand
import org.a_cyb.sayit.presentation.ListContract
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe

class ListViewModelSpec {
    private val alarmsTestData = listOf(
        Alarm(
            id = 1,
            combinedMinutes = CombinedMinutes(480),
            weeklyRepeat = WeeklyRepeat.EVERYDAY,
            label = Label("Morning"),
            enabled = true,
            ringtone = Ringtone(""),
            sayItScripts = emptyList(),
        ),
        Alarm(
            id = 2,
            combinedMinutes = CombinedMinutes(1080),
            weeklyRepeat = WeeklyRepeat(Mon, Wed, Fri),
            label = Label("Evening"),
            enabled = true,
            ringtone = Ringtone(""),
            sayItScripts = emptyList(),
        ),
        Alarm(
            id = 3,
            combinedMinutes = CombinedMinutes(1380),
            weeklyRepeat = WeeklyRepeat(),
            label = Label("Tonight"),
            enabled = false,
            ringtone = Ringtone(""),
            sayItScripts = emptyList(),
        ),
    )

    private val taskerFake = ListTaskerFake(
        listOf(Result.failure(IllegalStateException())),
        TestScope(),
    )

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `It fulfills ListViewModel`() {
        ListViewModel(taskerFake) fulfils ListContract.ListViewModel::class
    }

    @Test
    fun `It is in the initial`() {
        ListViewModel(taskerFake).state.value mustBe ListContract.Initial
    }

    @Test
    fun `Given tasker load result success it propagates Loaded`() = runTest {
        // Given
        val result = listOf(Result.success(alarmsTestData))
        val taskerFake = ListTaskerFake(result, this)
        val viewModel = ListViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(1) // Initial State

            // Then
            awaitItem() mustBe ListContract.Loaded(
                alarmsTestData.map {
                    ListContract.AlarmInfo(
                        it.id,
                        it.combinedMinutes,
                        it.weeklyRepeat,
                        it.label.label,
                        it.enabled,
                    )
                },
            )
        }
    }

    @Test
    fun `Given tasker load fail it propagates Error`() = runTest {
        // Given
        val result: List<Result<List<Alarm>>> = listOf(
            Result.failure(IllegalStateException("TestFail")),
        )
        val taskerFake = ListTaskerFake(result, this)
        val viewModel = ListViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(1)

            // Then
            awaitItem() mustBe
                ListContract.Error(ListContract.ListError.LOAD_ALARMS_FAILED, "TestFail")
        }
    }

    @Test
    fun `Given deleteAlarm called success it propagates Loaded`() = runTest {
        // Given
        val afterAlarmTestData = alarmsTestData.toMutableList()
        afterAlarmTestData.removeLast()
        val result = listOf(
            Result.success(alarmsTestData),
            Result.success(afterAlarmTestData),
        )
        val taskerFake = ListTaskerFake(result, this)
        val viewModel = ListViewModel(taskerFake)

        viewModel.state.test {
            skipItems(2)
            // When
            viewModel.runCommand(DeleteAlarmCommand(alarmsTestData.lastIndex.toLong()))

            // Then
            awaitItem() mustBe ListContract.Loaded(
                afterAlarmTestData.map {
                    ListContract.AlarmInfo(
                        it.id,
                        it.combinedMinutes,
                        it.weeklyRepeat,
                        it.label.label,
                        it.enabled,
                    )
                },
            )
        }
    }

    @Test
    fun `When state is not loaded and deleteAlarm called it propagates Error`() = runTest {
        // Given
        val result: List<Result<List<Alarm>>> = listOf(
            Result.failure(IllegalStateException()),
        )
        val taskerFake = ListTaskerFake(result, this)
        val viewModel = ListViewModel(taskerFake)

        viewModel.state.test {
            skipItems(1)
            // When
            viewModel.runCommand(DeleteAlarmCommand(1))

            // Then
            awaitItem() mustBe ListContract.Error(ListContract.ListError.DELETE_ALARM_FAILED, "")
        }
    }
}
