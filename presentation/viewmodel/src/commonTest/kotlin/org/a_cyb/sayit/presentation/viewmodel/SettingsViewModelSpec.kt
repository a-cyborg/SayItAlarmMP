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
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.SetSnoozeCommand
import org.a_cyb.sayit.presentation.SetThemeCommand
import org.a_cyb.sayit.presentation.SetTimeOutCommand
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.Error
import org.a_cyb.sayit.presentation.SettingsContract.Initial
import org.a_cyb.sayit.presentation.SettingsContract.SettingsError
import org.a_cyb.sayit.presentation.SettingsContract.SettingsStateWithContent
import org.a_cyb.sayit.presentation.SettingsContract.ValidTimeInput
import org.a_cyb.sayit.presentation.viewmodel.TaskerFake.SettingsTaskerFake
import tech.antibytes.kfixture.fixture
import tech.antibytes.kfixture.kotlinFixture
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe

class SettingsViewModelSpec {

    private val settings = Settings(
        timeOut = TimeOut(180),
        snooze = Snooze(15),
        theme = Theme.LIGHT,
    )
    private val fixture = kotlinFixture()
    private val taskerFake = SettingsTaskerFake(listOf(Result.success(settings)), TestScope())

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `It fulfils SettingsViewModel`() {
        SettingsViewModel(taskerFake) fulfils SettingsContract.SettingsViewModel::class
    }

    @Test
    fun `It is in the initial state`() {
        SettingsViewModel(taskerFake).state.value mustBe Initial
    }

    @Test
    fun `Given tasker load result success it propagates SettingsStateWithContent state`() = runTest {
        // Given
        val results = listOf(Result.success(settings))
        val taskerFake = SettingsTaskerFake(results, this)
        val viewModel = SettingsViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(1) // Initial state

            // Then
            awaitItem() mustBe SettingsStateWithContent(
                timeOut = ValidTimeInput(settings.timeOut.timeOut),
                snooze = ValidTimeInput(settings.snooze.snooze),
                theme = settings.theme,
            )
        }
    }

    @Test
    fun `Given interactor load result fail it propagates INITIAL_SETTINGS_UNRESOLVED error state`() = runTest {
        val result = listOf(Result.failure<Settings>(RuntimeException()))
        val taskerFake = SettingsTaskerFake(result, this)
        val viewModel = SettingsViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(1)

            // Then
            awaitItem() mustBe Error(SettingsError.INITIAL_SETTINGS_UNRESOLVED)
        }
    }

    @Test
    fun `Given setTimeOut called it propagates updated SettingsStateWithContent state`() = runTest {
        // Given
        val timeOut: Int = fixture.fixture(range = (30..300)) { it != settings.timeOut.timeOut }
        val results = listOf(
            Result.success(settings),
            Result.success(settings.copy(timeOut = TimeOut(timeOut))),
        )
        val taskerFake = SettingsTaskerFake(results, this)
        val viewModel = SettingsViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(2)
            viewModel.runCommand(SetTimeOutCommand(timeOut))

            // Then
            awaitItem() mustBe SettingsStateWithContent(
                timeOut = ValidTimeInput(timeOut),
                snooze = ValidTimeInput(settings.snooze.snooze),
                theme = settings.theme,
            )
        }
    }

    @Test
    fun `When state is not SettingsStateWithContent and setTimeOut called it propagates SETTINGS_STATE_WITH_CONTENT_UNRESOLVED error state`() =
        runTest {
            // Given
            val timeOut: Int = fixture.fixture(range = (30..300)) { it != settings.timeOut.timeOut }
            val result: List<Result<Settings>> = listOf(
                Result.failure(RuntimeException()),
                Result.failure(IllegalStateException()),
            )
            val taskerFake = SettingsTaskerFake(result, this)
            val viewModel = SettingsViewModel(taskerFake)

            viewModel.state.test {
                // When
                skipItems(2)
                viewModel.runCommand(SetTimeOutCommand(timeOut))

                // Then
                awaitItem() mustBe Error(SettingsError.SETTINGS_STATE_WITH_CONTENT_UNRESOLVED)
            }
        }

    @Test
    fun `Given setSnooze called it propagates updated SettingsStateWithContent state`() = runTest {
        // Given
        val snooze: Int = fixture.fixture(range = 5..60) { it != settings.snooze.snooze }
        val results = listOf(
            Result.success(settings),
            Result.success(settings.copy(snooze = Snooze(snooze))),
        )
        val taskerFake = SettingsTaskerFake(results, this)
        val viewModel = SettingsViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(2)
            viewModel.runCommand(SetSnoozeCommand(snooze))

            // Then
            awaitItem() mustBe SettingsStateWithContent(
                timeOut = ValidTimeInput(settings.timeOut.timeOut),
                snooze = ValidTimeInput(snooze),
                theme = settings.theme,
            )
        }
    }

    @Test
    fun `When state is not SettingsStateWithContent and setSnooze called it propagates SETTINGS_STATE_WITH_CONTENT_UNRESOLVED error state`() =
        runTest {
            // Given
            val snooze: Int = fixture.fixture(range = (5..60)) { it != settings.snooze.snooze }
            val result: List<Result<Settings>> = listOf(
                Result.failure(RuntimeException()),
                Result.failure(IllegalStateException()),
            )
            val taskerFake = SettingsTaskerFake(result, this)
            val viewModel = SettingsViewModel(taskerFake)

            viewModel.state.test {
                // When
                skipItems(2)
                viewModel.runCommand(SetSnoozeCommand(snooze))

                // Then
                awaitItem() mustBe Error(SettingsError.SETTINGS_STATE_WITH_CONTENT_UNRESOLVED)
            }
        }

    @Test
    fun `Given setTheme called it propagates updated SettingsStateWithContent state`() = runTest {
        // Given
        val results = listOf(
            Result.success(settings),
            Result.success(settings.copy(theme = Theme.DARK)),
        )
        val taskerFake = SettingsTaskerFake(results, this)
        val viewModel = SettingsViewModel(taskerFake)

        viewModel.state.test {
            // When
            skipItems(2)
            viewModel.runCommand(SetThemeCommand(Theme.DARK))

            // Then
            awaitItem() mustBe SettingsStateWithContent(
                timeOut = ValidTimeInput(settings.timeOut.timeOut),
                snooze = ValidTimeInput(settings.snooze.snooze),
                theme = Theme.DARK,
            )
        }
    }
}
