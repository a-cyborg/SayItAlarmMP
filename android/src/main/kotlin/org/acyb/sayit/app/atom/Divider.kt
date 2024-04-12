/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.acyb.sayit.app.token.Color
import org.acyb.sayit.app.token.Spacing

@Composable
fun DividerStandard() {
    Divider(
        color = Color.text.subtle.copy(alpha = 0.3f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = Spacing.s),
    )
}
