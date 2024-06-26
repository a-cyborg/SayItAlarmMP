/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.acyb.sayit.app.token.Color
import org.acyb.sayit.app.token.Font

@Composable
fun TextDisplayStandardLarge(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.display.l,
    )
}

@Composable
fun TextDisplayStandardSmall(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.display.s,
    )
}

@Composable
fun TextHeadlineStandardLarge(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.headline.l,
    )
}

@Composable
fun TextHeadlineStandardSmall(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.headline.s,
    )
}

@Composable
fun TextTitleStandardLarge(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.title.l,
    )
}

@Composable
fun TextTitleAttentionMedium(text: String) {
    Text(
        text = text,
        color = Color.text.attention,
        style = Font.title.m,
    )
}

@Composable
fun TextTitleWarningMedium(text: String) {
    Text(
        text = text,
        color = Color.text.warning,
        style = Font.title.m,
    )
}

@Composable
fun TextTitleSubtleMedium(text: String) {
    Text(
        text = text,
        color = Color.text.subtle,
        style = Font.title.m,
    )
}

@Composable
fun TextLabelAttentionLarge(text: String) {
    Text(
        text = text,
        color = Color.text.attention,
        style = Font.label.l,
    )
}

@Composable
fun TextBodyStandardLarge(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.body.l,
    )
}

@Composable
fun TextBodySubtleMedium(text: String) {
    Text(
        text = text,
        color = Color.text.subtle,
        style = Font.body.m,
    )
}

@Composable
fun TextBodyStandardSmall(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.body.s,
    )
}
