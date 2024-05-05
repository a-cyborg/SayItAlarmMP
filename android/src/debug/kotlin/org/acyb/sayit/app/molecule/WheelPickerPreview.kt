/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.atom.TextHeadlineStandardLarge

@Preview
@Composable
fun WheelPickerPreview() {
    val colors: List<String> = listOf(
        "Red", "Blue", "Green", "Yellow", "Purple",
        "Orange", "Pink", "Brown", "Black", "White",
        "Gray", "Turquoise", "Maroon",
    )

    WheelPicker(
        values = colors,
        initIdx = 4,
        itemRow = { TextHeadlineStandardLarge(text = it) },
        onCancel = {},
        onConfirm = { _ -> },
    )
}
