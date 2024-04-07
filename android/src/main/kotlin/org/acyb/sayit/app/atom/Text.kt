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
fun TextHeadlineStandardLarge(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.headline.l
    )
}

@Composable
fun TextHeadlineStandardSmall(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.headline.s
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
        style = Font.body.l
    )
}

@Composable
fun TextBodyStandardMedium(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.body.m
    )
}