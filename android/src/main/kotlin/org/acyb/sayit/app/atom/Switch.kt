/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import org.acyb.sayit.app.token.Color

@Composable
fun SwitchStandard(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Surface.Success.light,
            checkedTrackColor = Color.Surface.Accent.light,
            uncheckedThumbColor = Color.Surface.light,
            uncheckedTrackColor = Color.Surface.moderate,
        )
    )
}