/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.ui.res.stringResource
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
class PanelSpec : RoborazziTest() {

    @Test
    fun `It renders PanelStandard`() {
        subjectUnderTest.setContent {
            PanelStandard(
                panelItems = listOf(
                    {
                        PanelRowStandard(
                            valueLabel = stringResource(id = R.string.timeout),
                            value = stringResource(id = R.string.minute_short, 180),
                            afterContent = { IconButtonEdit {} },
                        )
                    },
                    {
                        PanelRowStandard(
                            valueLabel = stringResource(id = R.string.snooze),
                            value = stringResource(id = R.string.minute_short, 15),
                            afterContent = { IconButtonEdit {} },
                        )
                    },
                    {
                        PanelRowStandard(
                            valueLabel = stringResource(id = R.string.theme),
                            value = "Light",
                            afterContent = { IconButtonEdit {} },
                        )
                    },
                ),
            )
        }
    }
}
