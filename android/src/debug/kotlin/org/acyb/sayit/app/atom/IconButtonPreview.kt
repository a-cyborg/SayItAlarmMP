/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.token.Icon

@Preview
@Composable
fun IconButtonAccentTextPreview() {
    IconButtonAccent(label = "Button") {}
}

@Preview
@Composable
fun IconButtonAccentPreview() {
    IconButtonAccent(icon = Icon.add, contentDescription = "") {}
}

@Preview
@Composable
fun IconButtonDangerPreview() {
    IconButtonDanger(icon = Icon.delete, contentDescription = "") {}
}
