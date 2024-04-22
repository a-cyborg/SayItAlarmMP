/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.acyb.sayit.app.atom.IconButtonClose
import org.acyb.sayit.app.atom.IconButtonSaveText
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
class DialogSpec {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val roborazziRule = roborazziOf(composeTestRule, RoborazziRule.CaptureType.None)

    @Test
    fun `It renders a DialogStandard`() {
        composeTestRule.setContent {
            DialogStandard(
                topAppBar = {
                    TopAppBarGlobal(
                        title = "Add Alarm",
                        firstIcon = { IconButtonClose {} },
                        secondIcon = { IconButtonSaveText {} },
                    )
                },
                onDismiss = { },
            ) {}
        }

        composeTestRule.onNodeWithText("Add Alarm")
            .onParent()
            .onParent()
            .captureRoboImage()
    }

    @Test
    fun `Given DialogSpec onDismiss pressBack click is called it propagates the given action`() {
        var hasBeenCalled = false

        composeTestRule.setContent {
            DialogStandard(
                topAppBar = {
                    TopAppBarGlobal(
                        title = "Add Alarm",
                        firstIcon = { IconButtonClose {} },
                        secondIcon = { IconButtonSaveText {} },
                    )
                },
                onDismiss = {
                    hasBeenCalled = true
                },
            ) {}
        }

        Espresso.pressBack()

        hasBeenCalled mustBe true
    }
}
