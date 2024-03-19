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
    TextStandardDisplay(text = "SayIt")
}
@Preview
@Composable
fun TextStandardTitlePreview() {
    TextStandardTitle(text = "SayIt")
}

@Preview
@Composable
fun TextSubtleTitlePreview() {
    TextSubtleTitle(text = "SayIt")
}

@Preview
@Composable
fun TextStandardLabelPreview() {
    TextStandardLabel(text = "SayIt")
}
@Preview
@Composable
fun TextAccentLabelPreview() {
    TextAccentLabel(text = "SayIt")
}
@Preview
@Composable
fun TextStandardBodyPreview() {
    TextStandardBody(text = "SayIt")
}
