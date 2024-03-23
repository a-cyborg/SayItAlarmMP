/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.token

import androidx.compose.ui.graphics.Color as ColorPalette
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.*
import org.junit.jupiter.api.TestFactory
import tech.antibytes.util.test.mustBe

class ColorSpec {
    @TestFactory
    fun `Color is initially in LightMode`(): List<DynamicTest> {
        return listOf(
            ColorModeTestSpec(
                "test.standard",
                ColorPalette(0xFF000000)
            ) { Color.text.standard },
            ColorModeTestSpec(
                "test.subtle",
                ColorPalette(0xFF7C6F64),
            ) { Color.text.subtle },
            ColorModeTestSpec(
                "test.inverse",
                ColorPalette(0xFFFFFFFF),
            ) { Color.text.inverse },
            ColorModeTestSpec(
                "test.success",
                ColorPalette(0xFF79740E),
            ) { Color.text.success },
            ColorModeTestSpec(
                "test.info",
                ColorPalette(0xFF076678),
            ) { Color.text.info },
            ColorModeTestSpec(
                "test.warning",
                ColorPalette(0xFF8F3F71),
            ) { Color.text.warning },
            ColorModeTestSpec(
                "test.danger",
                ColorPalette(0xFF9D0006),
            ) { Color.text.danger },
            ColorModeTestSpec(
                "test.accent",
                ColorPalette(0xFFB57614),
            ) { Color.text.accent },
            ColorModeTestSpec(
                "test.attention",
                ColorPalette(0xFF427658),
            ) { Color.text.attention },
        ).map { (descriptor, expected, actual) ->
            dynamicTest("Color.$descriptor is initially in LightMode") {
                actual() mustBe expected
            }
        }
    }

    @TestFactory
    fun `When useDarkMode is called it switches in DarkMode`(): List<DynamicTest> {
        Color.userLightTheme()
        Color.useDarkTheme()

        return listOf(
            ColorModeTestSpec(
                "test.standard",
                ColorPalette(0xFFFFFFFF)
            ) { Color.text.standard },
            ColorModeTestSpec(
                "test.subtle",
                ColorPalette(0xFFA89984),
            ) { Color.text.subtle },
            ColorModeTestSpec(
                "test.inverse",
                ColorPalette(0xFF000000),
            ) { Color.text.inverse },
            ColorModeTestSpec(
                "test.success",
                ColorPalette(0xFF98971A),
            ) { Color.text.success },
            ColorModeTestSpec(
                "test.info",
                ColorPalette(0xFF458588),
            ) { Color.text.info },
            ColorModeTestSpec(
                "test.warning",
                ColorPalette(0xFFB16286),
            ) { Color.text.warning },
            ColorModeTestSpec(
                "test.danger",
                ColorPalette(0xFFCC241D),
            ) { Color.text.danger },
            ColorModeTestSpec(
                "test.accent",
                ColorPalette(0xFFD79921),
            ) { Color.text.accent },
            ColorModeTestSpec(
                "test.attention",
                ColorPalette(0xFF689D6A),
            ) { Color.text.attention },
        ).map { (descriptor, expected, actual) ->
            dynamicTest("Color.$descriptor is initially in LightMode") {
                actual() mustBe expected
            }
        }
    }

    @TestFactory
    fun `When useLightMode is called it switches in LightMode`(): List<DynamicTest> {
        Color.useDarkTheme()
        Color.userLightTheme()

        return listOf(
            ColorModeTestSpec(
                "test.standard",
                ColorPalette(0xFF000000)
            ) { Color.text.standard },
            ColorModeTestSpec(
                "test.subtle",
                ColorPalette(0xFF7C6F64),
            ) { Color.text.subtle },
            ColorModeTestSpec(
                "test.inverse",
                ColorPalette(0xFFFFFFFF),
            ) { Color.text.inverse },
            ColorModeTestSpec(
                "test.success",
                ColorPalette(0xFF79740E),
            ) { Color.text.success },
            ColorModeTestSpec(
                "test.info",
                ColorPalette(0xFF076678),
            ) { Color.text.info },
            ColorModeTestSpec(
                "test.warning",
                ColorPalette(0xFF8F3F71),
            ) { Color.text.warning },
            ColorModeTestSpec(
                "test.danger",
                ColorPalette(0xFF9D0006),
            ) { Color.text.danger },
            ColorModeTestSpec(
                "test.accent",
                ColorPalette(0xFFB57614),
            ) { Color.text.accent },
            ColorModeTestSpec(
                "test.attention",
                ColorPalette(0xFF427658),
            ) { Color.text.attention },
        ).map { (descriptor, expected, actual) ->
            dynamicTest("Color.$descriptor is initially in LightMode") {
                actual() mustBe expected
            }
        }
    }
}

data class ColorModeTestSpec(
    val descriptor: String,
    val expectedColor: ColorPalette,
    val actualColor: () -> ColorPalette,
)