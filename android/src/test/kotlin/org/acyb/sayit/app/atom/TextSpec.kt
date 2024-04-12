/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.acyb.sayit.app.RoborazziTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class TextSpec : RoborazziTest() {

    @Test
    fun `It renders TextDisplayStandard`() {
        subjectUnderTest.setContent {
            TextDisplayStandardLarge(text = "DisplayStandard")
        }
    }

    @Test
    fun `It renders TextTitleStandard`() {
        subjectUnderTest.setContent {
            TextTitleStandardLarge(text = "TitleStandard")
        }
    }

    @Test
    fun `It renders TextLabelAttentionLarge`() {
        subjectUnderTest.setContent {
            TextLabelAttentionLarge(text = "Label")
        }
    }

    @Test
    fun `It renders TextBodyStandardLarge`() {
        subjectUnderTest.setContent {
            TextBodyStandardLarge(text = "BodyStandard")
        }
    }

    @Test
    fun `It renders TextBodyStandardMedium`() {
        subjectUnderTest.setContent {
            TextBodyStandardMedium(text = "BodyStandardMedium")
        }
    }

    @Test
    fun `It renders TextHeadlineStandard`() {
        subjectUnderTest.setContent {
            TextHeadlineStandardSmall(text = "HeadlineStandard")
        }
    }
}
