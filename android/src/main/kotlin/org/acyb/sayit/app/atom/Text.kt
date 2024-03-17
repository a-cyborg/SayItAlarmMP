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
fun TextSubtle(text: String) {
    Text(
        text = text,
        color = Color.text.subtle,
        style = Font.title.l,
    )
}

@Composable
fun TextAccent(text: String) {
    Text(
        text = text,
        color = Color.text.accent,
        style = Font.label.l,
    )
}