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
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.Initial
import org.a_cyb.sayit.presentation.SettingsContract.SettingsStateWithContent
import org.a_cyb.sayit.presentation.SettingsContract.ValidTimeInput
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe

class SettingsViewModelSpec {
    private val settings = Settings(
        timeOut = TimeOut(30),
        snooze = Snooze(15),
        theme = Theme.LIGHT,
    )

    private val interactor = SettingsInteractorFake(settings)

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
        SettingsViewModel(interactor) fulfils SettingsContract.SettingsViewModel::class
    }

    @Test
    fun `It is in the initial state`() {
        SettingsViewModel(interactor).state.value mustBe Initial
    }

    @Test
    fun `It initializes with loaded settings`() = runTest {
        val viewModel = SettingsViewModel(interactor)

        viewModel.state.test {
            skipItems(1)

            awaitItem() mustBe SettingsStateWithContent(
                timeOut = ValidTimeInput(settings.timeOut.timeOut),
                snooze = ValidTimeInput(settings.snooze.snooze),
                theme = settings.theme,
            )
        }
    }

    /*
    @Test
    fun `Given setTimeOut called with valid input it sets state timeOut to ValidTimeInput`() = runTest {
        // Given
        val timeOuts: IntArray = fixture.fixture(
            range = 30..300,
        )
        val viewModel = SettingsViewModel(interactor)

        interactor.emitResult(Result.success(settingsDummy), this)

        viewModel.state.test {
            skipItems(2)

            timeOuts.forEach { timeOut ->
                // When
                viewModel.runCommand(SetTimeOutCommand(timeOut))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(timeOut),
                    snooze = ValidTimeInput(settingsDummy.snooze.snooze),
                    theme = settingsDummy.theme,
                )
            }
        }
    }

    @Test
    fun `Given setTimeOut called with invalid input it sets state timeOut to InvalidTimeInput`() = runTest {
        // Given
        val timeOuts: IntArray = fixture.fixture(
            (Int.MIN_VALUE..29),
            (301..Int.MAX_VALUE),
        )
        val viewModel = SettingsViewModel(interactor)

        interactor.emitResult(Result.success(settingsDummy), this)

        viewModel.state.test {
            skipItems(2)

            timeOuts.forEach { timeOut ->
                // When
                viewModel.runCommand(SetTimeOutCommand(timeOut))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = InvalidTimeInput(timeOut),
                    snooze = ValidTimeInput(settingsDummy.snooze.snooze),
                    theme = settingsDummy.theme,
                )
            }
        }
    }

    @Test
    fun `Given setSnooze called with valid input it sets state snooze to ValidTimeInput`() = runTest {
        // Given
        val snoozes: IntArray = fixture.fixture(
            range = 5..60,
        )
        val viewModel = SettingsViewModel(interactor)

        interactor.emitResult(Result.success(settingsDummy), this)

        viewModel.state.test {
            skipItems(2)

            snoozes.forEach { snooze ->
                // When
                viewModel.runCommand(SetSnoozeCommand(snooze))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(settingsDummy.timeOut.timeOut),
                    snooze = ValidTimeInput(snooze),
                    theme = settingsDummy.theme,
                )
            }
        }
    }

    @Test
    fun `Given setSnooze called with invalid input it sets state snooze to InvalidTimeInput`() = runTest {
        // Given
        val snoozes: IntArray = fixture.fixture(
            Int.MIN_VALUE..4,
            61..Int.MAX_VALUE,
        )
        val viewModel = SettingsViewModel(interactor)

        interactor.emitResult(Result.success(settingsDummy), this)

        viewModel.state.test {
            skipItems(2)

            snoozes.forEach { snooze ->
                // When
                viewModel.runCommand(SetSnoozeCommand(snooze))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(settingsDummy.timeOut.timeOut),
                    snooze = InvalidTimeInput(snooze),
                    theme = settingsDummy.theme,
                )
            }
        }
    }

    @Test
    fun `Given setTheme called it sets state theme to given theme`() = runTest {
        // Given
        val themes: List<Theme> = listOf(Theme.DARK, Theme.LIGHT, Theme.DARK)
        val viewModel = SettingsViewModel(interactor)

        interactor.emitResult(Result.success(settingsDummy), this)

        viewModel.state.test {
            skipItems(2)

            themes.forEach { theme ->
                // When
                viewModel.runCommand(SetThemeCommand(theme))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(settingsDummy.timeOut.timeOut),
                    snooze = ValidTimeInput(settingsDummy.snooze.snooze),
                    theme = theme,
                )
            }
        }
    }

    @Test
    fun `Given save called it invokes interactor save with mapped Settings from settingsStateWithContent`() = runTest {
        // Given
        val numOfTest = 3
        val viewModel = SettingsViewModel(interactor)

        interactor.emitResult(Result.success(settingsDummy), this)

        viewModel.state.test {
            skipItems(2)

            repeat(numOfTest) {
                val timeOut: Int = fixture.fixture(range = 30..300)
                val snooze: Int = fixture.fixture(range = 5..60)
                val theme: Theme = fixture.fixture(listOf(Theme.DARK, Theme.LIGHT))

                viewModel.runCommand(SetTimeOutCommand(timeOut))
                viewModel.runCommand(SetSnoozeCommand(snooze))
                viewModel.runCommand(SetThemeCommand(theme))

                // When
                viewModel.runCommand(SaveCommand)

                // Then
                interactor.settings.first().getOrThrow() mustBe Settings(
                    timeOut = TimeOut(timeOut),
                    snooze = Snooze(snooze),
                    theme = theme,
                )

                cancelAndIgnoreRemainingEvents()
            }
        }
    }
     */
}
