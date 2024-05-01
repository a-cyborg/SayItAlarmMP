/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.acyb.sayit.R
import org.acyb.sayit.app.RoborazziTest
import org.acyb.sayit.app.atom.TextHeadlineStandardLarge
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import tech.antibytes.util.test.mustBe

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class WheelPickerSpec : RoborazziTest() {

    private val colors: List<String> = listOf(
        "Red", "Blue", "Green", "Yellow", "Purple",
        "Orange", "Pink", "Brown", "Black", "White",
        "Gray", "Turquoise", "Maroon"
    )

    @Test
    fun `It renders a WheelPicker`() {

        subjectUnderTest.setContent {
            WheelPicker(
                values = colors,
                itemRow = { TextHeadlineStandardLarge(text = it) },
                onConfirm = { _ -> }
            )
        }
    }

    @Test
    fun `When initIdx is not provided it displays a first value in the center`() {

        subjectUnderTest.setContent {
            WheelPicker(
                values = colors,
                itemRow = { TextHeadlineStandardLarge(text = it) },
                onConfirm = { _ -> }
            )
        }

        with(subjectUnderTest) {
            onNodeWithText(colors[0]).assertExists()
            onNodeWithText(colors[1]).assertExists()
            onNodeWithText(colors[2]).assertExists()
        }
    }

    @Test
    fun `When initIdx is provided it displays a value at initValueIndex in the center`() {
        val initIdx = 6

        subjectUnderTest.setContent {
            WheelPicker(
                values = colors,
                initIdx = initIdx,
                itemRow = { TextHeadlineStandardLarge(text = it) },
                onConfirm = { _ -> }
            )
        }

        with(subjectUnderTest) {
            onNodeWithText(colors[initIdx - 2]).assertExists()
            onNodeWithText(colors[initIdx - 1]).assertExists()
            onNodeWithText(colors[initIdx]).assertExists()
            onNodeWithText(colors[initIdx + 1]).assertExists()
            onNodeWithText(colors[initIdx + 2]).assertExists()
        }
    }

    @Test
    fun `Given onConfirm called it propagates the given action with selected value`() {
        val selectedItemIdx = 3

        var hasBeenCalled = false
        var selectedItem = ""

        subjectUnderTest.setContent {
            WheelPicker(
                values = colors,
                initIdx = selectedItemIdx,
                itemRow = { TextHeadlineStandardLarge(text = it) },
            ) {
                hasBeenCalled = true
                selectedItem = it
            }
        }

        subjectUnderTest
            .onNodeWithText(subjectUnderTest.activity.getString(R.string.save))
            .performClick()

        hasBeenCalled mustBe true
        selectedItem mustBe colors[selectedItemIdx]
    }
}
