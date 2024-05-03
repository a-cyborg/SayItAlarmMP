/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextRowTimeDurationPreview() {
    TextRowTimeDuration(minutes = 213)
}

@Preview
@Composable
fun TextRowTitleAndInfoPreview() {
    TextRowTitleAndInfo(title = "Title", info = "Additional information.")
}
