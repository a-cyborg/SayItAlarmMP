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
fun TextDisplayStandardLargePreview() {
    TextDisplayStandardLarge(text = "SayIt")
}

@Preview
@Composable
fun TextHeadlineStandardPreview() {
    TextHeadlineStandardSmall(text = "SayIt")
}

@Preview
@Composable
fun TextTitleStandardLargePreview() {
    TextTitleStandardLarge(text = "SayIt")
}

@Preview
@Composable
fun TextTitleSubtleMediumPreview() {
    TextTitleSubtleMedium(text = "TitleSubtleMedium")
}

@Preview
@Composable
fun TextLabelAttentionLargePreview() {
    TextLabelAttentionLarge(text = "SayIt")
}

@Preview
@Composable
fun TextBodyStandardLargePreview() {
    TextBodyStandardLarge(text = "SayIt")
}

@Preview
@Composable
fun TextBodyStandardMediumPreview() {
    TextBodyStandardSmall(text = "SayIt")
}
