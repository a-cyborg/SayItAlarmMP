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
fun TextStandardDisplay(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.display.s,
    )
}

@Composable
fun TextStandardTitle(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.title.l,
    )
}

@Composable
fun TextSubtleTitle(text: String) {
    Text(
        text = text,
        color = Color.text.subtle,
        style = Font.title.l,
    )
}

@Composable
fun TextStandardLabel(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.label.m,
    )
}

@Composable
fun TextAccentLabel(text: String) {
    Text(
        text = text,
        color = Color.text.accent,
        style = Font.label.l,
    )
}

@Composable
fun TextStandardBody(text: String) {
    Text(
        text = text,
        color = Color.text.standard,
        style = Font.body.m
    )
}
