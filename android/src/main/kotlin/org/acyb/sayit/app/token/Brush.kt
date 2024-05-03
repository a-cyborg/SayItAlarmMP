/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.token

import androidx.compose.ui.graphics.Brush

object Brush {
    val verticalFadingBrush = Brush.verticalGradient(
        0f to Color.ColorPalette.transparent,
        0.3f to Color.ColorPalette.transparent.copy(alpha = 0.3f),
        0.5f to Color.surface.standard,
        0.7f to Color.ColorPalette.transparent.copy(alpha = 0.3f),
        1f to Color.ColorPalette.transparent,
    )
}
