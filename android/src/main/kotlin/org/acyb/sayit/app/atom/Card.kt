/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.acyb.sayit.app.token.Color
import org.acyb.sayit.app.token.Elevation
import org.acyb.sayit.app.token.Shape
import org.acyb.sayit.app.token.Spacing

@Composable
fun CardStandard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.s),
        shape = Shape.Card.primary,
        backgroundColor = Color.surface.standard,
        elevation = Elevation.firstLevel,
    ) {
        content()
    }
}

@Composable
fun CardSubtle(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.m, vertical = Spacing.l),
        shape = Shape.Card.primary,
        backgroundColor = Color.surface.subtle,
        elevation = Elevation.secondLevel,
    ) {
        content()
    }
}
