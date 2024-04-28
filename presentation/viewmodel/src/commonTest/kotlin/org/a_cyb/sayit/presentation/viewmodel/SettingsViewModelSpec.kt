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
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.SaveCommand
import org.a_cyb.sayit.presentation.SetSnoozeCommand
import org.a_cyb.sayit.presentation.SetThemeCommand
import org.a_cyb.sayit.presentation.SetTimeOutCommand
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.Error
import org.a_cyb.sayit.presentation.SettingsContract.Initial
import org.a_cyb.sayit.presentation.SettingsContract.InvalidTimeInput
import org.a_cyb.sayit.presentation.SettingsContract.SettingsError
import org.a_cyb.sayit.presentation.SettingsContract.SettingsStateWithContent
import org.a_cyb.sayit.presentation.SettingsContract.ValidTimeInput
import tech.antibytes.kfixture.fixture
import tech.antibytes.kfixture.kotlinFixture
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe

class SettingsViewModelSpec {

    private val settings = Settings(
        timeOut = TimeOut(30),
        snooze = Snooze(15),
        theme = Theme.LIGHT,
    )
    private val fixture = kotlinFixture()
    private val interactor = SettingsInteractorFake(Result.success(settings))

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
    fun `It initializes with settings set state as SettingsStateWithContent`() = runTest {
        // Given
        val viewModel = SettingsViewModel(interactor)

        viewModel.state.test {
            // When
            interactor.load(this)
            skipItems(1)

            // Then
            awaitItem() mustBe SettingsStateWithContent(
                timeOut = ValidTimeInput(settings.timeOut.timeOut),
                snooze = ValidTimeInput(settings.snooze.snooze),
                theme = settings.theme,
            )
        }
    }

    @Test
    fun `Given setTimeOut called with valid input it sets state timeOut to ValidTimeInput`() = runTest {
        // Given
        val timeOuts: IntArray = fixture.fixture(range = 30..300)
        val viewModel = SettingsViewModel(interactor)
        interactor.load(this)

        viewModel.state.test {
            skipItems(2)

            timeOuts.forEach { timeOut ->
                // When
                viewModel.runCommand(SetTimeOutCommand(timeOut))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(timeOut),
                    snooze = ValidTimeInput(settings.snooze.snooze),
                    theme = settings.theme,
                )
            }
        }
    }

    @Test
    fun `Given setTimeOut called with invalid input it sets timeOut to InvalidTimeInput`() = runTest {
        // Given
        val timeOuts: IntArray = fixture.fixture(
            (Int.MIN_VALUE..29),
            (301..Int.MAX_VALUE),
        )
        val viewModel = SettingsViewModel(interactor)
        interactor.load(this)

        viewModel.state.test {
            skipItems(2)

            timeOuts.forEach { timeOut ->
                // When
                viewModel.runCommand(SetTimeOutCommand(timeOut))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = InvalidTimeInput(timeOut),
                    snooze = ValidTimeInput(settings.snooze.snooze),
                    theme = settings.theme,
                )
            }
        }
    }

    @Test
    fun `Given setSnooze called with valid input it sets snooze to ValidTimeInput`() = runTest {
        // Given
        val snoozes: IntArray = fixture.fixture(range = 5..60)
        val viewModel = SettingsViewModel(interactor)
        interactor.load(this)

        viewModel.state.test {
            skipItems(2)

            snoozes.forEach { snooze ->
                // When
                viewModel.runCommand(SetSnoozeCommand(snooze))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(settings.timeOut.timeOut),
                    snooze = ValidTimeInput(snooze),
                    theme = settings.theme,
                )
            }
        }
    }

    @Test
    fun `Given setSnooze called with invalid input it state snooze to InvalidTimeInput`() = runTest {
        // Given
        val snoozes: IntArray = fixture.fixture(
            Int.MIN_VALUE..4,
            61..Int.MAX_VALUE,
        )
        val viewModel = SettingsViewModel(interactor)
        interactor.load(this)

        viewModel.state.test {
            skipItems(2)

            snoozes.forEach { snooze ->
                // When
                viewModel.runCommand(SetSnoozeCommand(snooze))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(settings.timeOut.timeOut),
                    snooze = InvalidTimeInput(snooze),
                    theme = settings.theme,
                )
            }
        }
    }

    @Test
    fun `Given setTheme called it sets state theme to given theme`() = runTest {
        // Given
        val themes: List<Theme> = listOf(Theme.DARK, Theme.LIGHT, Theme.DARK)
        val viewModel = SettingsViewModel(interactor)
        interactor.load(this)

        viewModel.state.test {
            skipItems(2)

            themes.forEach { theme ->
                // When
                viewModel.runCommand(SetThemeCommand(theme))

                // Then
                awaitItem() mustBe SettingsStateWithContent(
                    timeOut = ValidTimeInput(settings.timeOut.timeOut),
                    snooze = ValidTimeInput(settings.snooze.snooze),
                    theme = theme,
                )
            }
        }
    }

    @Test
    fun `Given save called it invokes interactor save with mapped Settings from settingsStateWithContent`() = runTest {
        // Given
        val viewModel = SettingsViewModel(interactor)
        interactor.load(this)

        viewModel.state.test {
            skipItems(2)

            val timeOut: Int = fixture.fixture(range = 30..300)
            val snooze: Int = fixture.fixture(range = 5..60)

            viewModel.runCommand(SetTimeOutCommand(timeOut))
            viewModel.runCommand(SetSnoozeCommand(snooze))
            viewModel.runCommand(SetThemeCommand(Theme.DARK))

            skipItems(3)

            // When
            viewModel.runCommand(SaveCommand)

            // Then
            interactor.settings.first().getOrThrow() mustBe Settings(
                timeOut = TimeOut(timeOut),
                snooze = Snooze(snooze),
                theme = Theme.DARK,
            )
        }
    }

    @Test
    fun `When interactor propagates result fail sets error state INITIAL_SETTINGS_UNRESOLVED`() = runTest {
        // Given
        val result = Result.failure<Settings>(IllegalStateException())
        val interactor = SettingsInteractorFake(result)
        val viewModel = SettingsViewModel(interactor)

        // When
        interactor.load(this)

        viewModel.state.test {
            skipItems(1)

            // Then
            val state = awaitItem()
            assertTrue(state is Error)
            state.detail.split(" ")[0] mustBe SettingsError.INITIAL_SETTINGS_UNRESOLVED.name
        }
    }

    @Test
    fun `Given SetTimeOut called when state is not StateWithContent sets Error state with UNABLE_RESOLVE_SETTINGS_STATE_WITH_CONTENT`() =
        runTest {
            // Given
            val viewModel = SettingsViewModel(interactor)

            viewModel.state.test {
                skipItems(1)

                // When
                viewModel.runCommand(SetTimeOutCommand(fixture.fixture()))

                // Then
                val state = awaitItem()
                assertTrue(state is Error)
                state.detail.split(" ")[0] mustBe
                    SettingsError.UNABLE_RESOLVE_SETTINGS_STATE_WITH_CONTENT.name
            }
        }
}
