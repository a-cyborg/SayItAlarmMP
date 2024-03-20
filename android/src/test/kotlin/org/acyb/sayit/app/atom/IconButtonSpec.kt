/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
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
class IconButtonSpec : RoborazziTest() {

    private fun getString(id: Int) = subjectUnderTest.activity.getString(id)

    @Test
    fun `It renders a IconButtonAdd`() {
        subjectUnderTest.setContent {
            IconButtonAdd {}
        }
    }

    @Test
    fun `Given IconButtonAdd click is called it propagates the given action`() {
        var hasBeenCalled = false

        subjectUnderTest.setContent {
            IconButtonAdd {
                hasBeenCalled = true
            }
        }
        subjectUnderTest
            .onNodeWithContentDescription(getString(R.string.action_add_alarm))
            .performClick()

        hasBeenCalled mustBe true
    }

    @Test
    fun `It renders a IconButtonDelete`() {
        subjectUnderTest.setContent {
            IconButtonDelete {}
        }
    }

    @Test
    fun `Given IconButtonDelete click is called it propagates the given action`() {
        var hasBeenCalled = false

        subjectUnderTest.setContent {
            IconButtonDelete {
                hasBeenCalled = true
            }
        }
        subjectUnderTest
            .onNodeWithContentDescription(getString(R.string.action_delete_alarm))
            .performClick()

        hasBeenCalled mustBe true
    }

    @Test
    fun `It renders a IconButtonEdit`() {
        subjectUnderTest.setContent {
            IconButtonEdit {}
        }
    }

    @Test
    fun `Given IconButtonEdit click is called it propagates the given action`() {
        var hasBeenCalled = false

        subjectUnderTest.setContent {
            IconButtonEdit {
                hasBeenCalled = true
            }
        }
        subjectUnderTest
            .onNodeWithContentDescription(getString(R.string.action_edit_alarm))
            .performClick()

        hasBeenCalled mustBe true
    }

    @Test
    fun `It renders a IconButtonEditText`() {
        subjectUnderTest.setContent {
            IconButtonEditText {}
        }
    }

    @Test
    fun `Given IconButtonEditText click is called it propagates the given action`() {
        var hasBeenCalled = false

        subjectUnderTest.setContent {
            IconButtonEditText {
                hasBeenCalled = true
            }
        }
        subjectUnderTest
            .onNodeWithText(getString(R.string.edit))
            .performClick()

        hasBeenCalled mustBe true
    }

    @Test
    fun `It renders a IconButtonSettings`() {
        subjectUnderTest.setContent {
            IconButtonSettings {}
        }
    }

    @Test
    fun `Given IconButtonSettings click is called it propagates the given action`() {
        var hasBeenCalled = false

        subjectUnderTest.setContent {
            IconButtonSettings {
                hasBeenCalled = true
            }
        }

        subjectUnderTest
            .onNodeWithContentDescription(getString(R.string.action_open_settings))
            .performClick()

        hasBeenCalled mustBe true
    }
}