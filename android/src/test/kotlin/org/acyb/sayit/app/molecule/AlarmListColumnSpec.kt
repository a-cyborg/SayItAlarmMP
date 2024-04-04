/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.acyb.sayit.app.RoborazziTest
import org.acyb.sayit.app.atom.SwitchStandard
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class AlarmListColumnSpec : RoborazziTest() {

    val alarmListItems: List<@Composable () -> Unit> = (0..6).map {
        {
            AlarmListItem(
                time = "${it * 3 / 12}:3${it}",
                weeklyRepeat = "Wakeup Call[$it], Everyday ",
                afterContent = { SwitchStandard(checked = it % 2 == 0) {} }
            )
        }
    }

    @Test
    fun `It renders AlarmListColumn`() {
        subjectUnderTest.setContent {
            AlarmListColumn(alarmListItems = alarmListItems)
        }
    }
}