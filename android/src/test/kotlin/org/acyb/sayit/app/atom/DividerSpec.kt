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
class DividerSpec : RoborazziTest() {
    @Test
    fun `It renders DividerStandard`() {
        subjectUnderTest.setContent {
            DividerStandard()
        }
    }
}
