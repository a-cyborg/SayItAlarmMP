/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.acyb.sayit.R
import org.acyb.sayit.app.RoborazziTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class TextRowSpec : RoborazziTest() {

    private fun getString(id: Int) = subjectUnderTest.activity.getString(id)

    @Test
    fun `It renders RowTextTimeDuration`() {
        subjectUnderTest.setContent {
            TextRowTimeDuration(minutes = 213)
        }
    }

    @Test
    fun `TextRowTimeDuration formats given minutes to time duration style`() {
        subjectUnderTest.setContent {
            TextRowTimeDuration(minutes = 180)
        }

        subjectUnderTest.onNodeWithText("3").assertExists()
        subjectUnderTest.onNodeWithText(getString(R.string.hour_abbr)).assertExists()
        subjectUnderTest.onNodeWithText("00").assertExists()
        subjectUnderTest.onNodeWithText(getString(R.string.minute_abbr)).assertExists()
    }

    @Test
    fun `It renders TextRowTitleAndInfo`() {
        subjectUnderTest.setContent {
            TextRowTitleAndInfo(
                title = "Title",
                info = "Information"
            )
        }

        subjectUnderTest.onNodeWithText("Title").assertExists()
        subjectUnderTest.onNodeWithText("Information").assertExists()
    }
}