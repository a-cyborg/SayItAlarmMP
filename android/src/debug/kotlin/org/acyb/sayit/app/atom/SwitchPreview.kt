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
fun SwitchStandardCheckedPreview() {
    SwitchStandard(checked = true) {}
}

@Preview
@Composable
fun SwitchStandardUncheckedPreview() {
    SwitchStandard(checked = false) {}
}
