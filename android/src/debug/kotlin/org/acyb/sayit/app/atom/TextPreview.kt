/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextStandardDisplayPreview() {
    TextDisplayStandard(text = "SayIt")
}
@Preview
@Composable
fun TextStandardTitlePreview() {
    TextTitleStandard(text = "SayIt")
}

@Preview
@Composable
fun TextSubtleTitlePreview() {
    TextTitleSubtle(text = "SayIt")
}

@Preview
@Composable
fun TextStandardLabelPreview() {
    TextLabelStandard(text = "SayIt")
}
@Preview
@Composable
fun TextAccentLabelPreview() {
    TextLabelAccent(text = "SayIt")
}
@Preview
@Composable
fun TextStandardBodyPreview() {
    TextBodyStandard(text = "SayIt")
}
