/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.acyb.sayit.R
import org.acyb.sayit.app.atom.TextDisplayStandardSmall
import org.acyb.sayit.app.roborazziOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import tech.antibytes.util.test.mustBe

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class PopUpPickerSpec {

    private val colors: List<String> = listOf(
        "Red", "Blue", "Green", "Yellow", "Purple",
        "Orange", "Pink", "Brown", "Black", "White",
        "Gray", "Turquoise", "Maroon",
    )

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val roborazziRule = roborazziOf(composeTestRule, RoborazziRule.CaptureType.None)

    private fun getString(id: Int) = composeTestRule.activity.getString(id)

    @Test
    fun `It renders PopUpPickerStandardWheel`() {
        composeTestRule.setContent {
            PopUpPickerStandardWheel(
                title = "Colors",
                info = "My favorite color is",
                pickerValues = colors,
                pickerInitIdx = 6,
                pickerItemRow = { TextDisplayStandardSmall(it) },
                onDismiss = {},
                onConfirm = { _ -> },
            )
        }

        composeTestRule
            .onNode(isDialog())
            .captureRoboImage()
    }

    @Test
    fun `When pressBack is called given PopUpPickerStandardWheel it propagates the given onDismiss action`() {
        var hasBeenCalled = false

        composeTestRule.setContent {
            PopUpPickerStandardWheel(
                title = "Colors",
                info = "My favorite color is",
                pickerValues = colors,
                pickerItemRow = { TextDisplayStandardSmall(it) },
                onDismiss = {
                    hasBeenCalled = true
                },
                onConfirm = { _ -> },
            )
        }

        Espresso.pressBack()

        hasBeenCalled mustBe true
    }

    @Test
    fun `Given PopUpPickerStandardWheel displays wheel picker`() {
        composeTestRule.setContent {
            PopUpPickerStandardWheel(
                title = "Colors",
                info = "My favorite color is",
                pickerValues = colors,
                pickerItemRow = { TextDisplayStandardSmall(it) },
                onDismiss = {},
                onConfirm = { _ -> },
            )
        }

        composeTestRule
            .onNodeWithContentDescription(getString(R.string.component_wheel_picker))
            .assertExists()
    }
}
