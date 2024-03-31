/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.a_cyb.sayit.entity.Settings
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.SetTimeOutCommand
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.ErrorWithDetail
import org.a_cyb.sayit.presentation.SettingsContract.Initial
import org.a_cyb.sayit.presentation.SettingsContract.InvalidTimeInput
import org.a_cyb.sayit.presentation.SettingsContract.Loaded
import org.a_cyb.sayit.presentation.SettingsContract.UISettingsData
import org.a_cyb.sayit.presentation.SettingsContract.ValidTimeInput
import tech.antibytes.kfixture.fixture
import tech.antibytes.kfixture.kotlinFixture
import tech.antibytes.util.test.fulfils
import tech.antibytes.util.test.mustBe
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SettingsViewModelSpec {
    private val interactor = SettingsInteractorFake()
    private val fixture = kotlinFixture()

    private val settings = Settings(
        timeOut = TimeOut(30),
        snooze = Snooze(15),
        theme = Theme.LIGHT,
    )

    private val validUISettingsData: UISettingsData = UISettingsData(
        timeOut = ValidTimeInput(30),
        snooze = ValidTimeInput(15),
        theme = Theme.LIGHT,
    )

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        interactor.reset(Result.failure(IllegalStateException()))
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
        SettingsViewModel(interactor).settingsState.value mustBe Initial
    }

    @Test
    fun `Given setTimeOut called with valid input it propagates values`() = runTest {
        // Given
        val timeOut: Int = fixture.fixture(
            range = 60..300
        ) { it != settings.timeOut.timeOut }

        interactor.reset(Result.success(settings))

        // When
        val viewModel = SettingsViewModel(interactor)

        viewModel.settingsState.test {
            viewModel.setTimeOut(TimeOut(timeOut))

            advanceUntilIdle()

            // Then
            skipItems(1) // Initial
            awaitItem() mustBe Loaded(validUISettingsData.copy(timeOut = ValidTimeInput(timeOut)))

            interactor.invoked mustBe InvocationType.SET_TIME_OUT
        }
    }

    @Test
    fun `Given setTimeOut called with invalid input it propagates error`() = runTest {
        // Given
        val timeOut: Int = fixture.fixture(
            range = Int.MIN_VALUE..59
        )

        interactor.reset(Result.success(settings))

        // When
        val viewModel = SettingsViewModel(interactor)

        viewModel.settingsState.test {
            viewModel.setTimeOut(TimeOut(timeOut))

            advanceUntilIdle()

            // Then
            skipItems(1) // Initial
            awaitItem() mustBe ErrorWithDetail(
                validUISettingsData.copy(timeOut = InvalidTimeInput(timeOut, ""))
            )

            interactor.invoked mustBe InvocationType.SET_TIME_OUT
        }
    }

    @Test
    fun `Given runCommand is called it executes the given Command`() {
        // Given
        val command = SetTimeOutCommand(timeOut = TimeOut(30))

        // When
        SettingsViewModel(interactor).runCommand(command)

        // Then
        interactor.invoked mustBe InvocationType.SET_TIME_OUT
    }
}