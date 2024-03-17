/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.acyb.sayit.app.token.Color

@Composable
fun TopAppBarIcon(
    label: String,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        TextAccent(text = label)
    }
}

@Composable
fun TopAppBarIcon(
    icon: ImageVector,
    contentDescription: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (enabled) Color.Text.accent else Color.Text.subtle
        )
    }
}
