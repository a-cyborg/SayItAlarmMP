/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.acyb.sayit.app.molecule.TopAppBarGlobal
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
    fun `It renders a DialogStandardFillMax`() {
        composeTestRule.setContent {
            DialogStandardFillMax(
                topAppBar = {
                    TopAppBarGlobal(
                        title = "Dialog",
                        firstIcon = { IconButtonClose {} },
                        secondIcon = {},
                    )
                },
                onDismiss = { },
            ) {}
        }

        composeTestRule.onNodeWithText("Dialog")
            .onParent()
            .onParent()
            .captureRoboImage()
    }

    @Test
    fun `When pressBack is called given dialogStandardFillMax it propagates the given onDismiss action`() {
        var hasBeenCalled = false

        composeTestRule.setContent {
            DialogStandardFillMax(
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

    @Test
    fun `It renders a DialogStandardFitContent`() {
        composeTestRule.setContent {
            DialogStandardFitContent(
                onDismiss = { },
            ) {
                TextTitleStandardLarge(text = "Dialog")
            }
        }

        composeTestRule.onNodeWithText("Dialog")
            .onParent()
            .captureRoboImage()
        // composeTestRule
        //     .onNode(isDialog())
        //     .captureRoboImage()
    }

    @Test
    fun `When pressBack is called given dialogStandardFitContent it propagates the given onDismiss action`() {
        var hasBeenCalled = false

        composeTestRule.setContent {
            DialogStandardFitContent(
                onDismiss = {
                    hasBeenCalled = true
                },
            ) {
                TextTitleStandardLarge(text = "Dialog")
            }
        }

        Espresso.pressBack()

        hasBeenCalled mustBe true
    }
}
