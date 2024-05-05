/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.screen

import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.presentation.SettingsContract
import org.acyb.sayit.R
import org.acyb.sayit.app.RoborazziTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import tech.antibytes.util.test.mustBe

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class SettingsScreenSpec : RoborazziTest() {

    private fun getString(id: Int) = subjectUnderTest.activity.getString(id)

    @Test
    fun `It renders SettingsScreen with given settingsStateWithContent`() = runTest {
        // Given
        val state = SettingsContract.SettingsStateWithContent(
            timeOut = SettingsContract.ValidTimeInput(180),
            snooze = SettingsContract.ValidTimeInput(15),
            theme = Theme.LIGHT,
        )
        val viewModel = SettingsViewModelFake(this, state)

        // When
        subjectUnderTest.setContent {
            SettingsScreen(viewModel = viewModel)
        }

        // Then
        with(subjectUnderTest) {
            onNodeWithText(getString(R.string.settings)).assertExists()
            onNodeWithContentDescription(getString(R.string.action_navigate_back)).assertExists()

            onNodeWithText(getString(R.string.timeout)).assertExists()
            onNodeWithText(getString(R.string.snooze)).assertExists()
            onNodeWithText(getString(R.string.theme)).assertExists()
            onNodeWithText(getString(R.string.about)).assertExists()
            onNodeWithText(getString(R.string.version)).assertExists()
            onAllNodesWithContentDescription(getString(R.string.action_edit_alarm))
                .fetchSemanticsNodes().size mustBe 4

            onNodeWithText("180 Min").assertExists()
            onNodeWithText("15 Min").assertExists()
            onNodeWithText("Light").assertExists()
        }
    }
}
