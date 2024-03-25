/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.acyb.sayit.app.token.Spacing

@Composable
fun SpacerExtraSmall() {
    Spacer(modifier = Modifier.size(Spacing.xxxs))
}
@Composable
fun SpacerMedium() {
    Spacer(modifier = Modifier.size(Spacing.m))
}
@Composable
fun SpacerLarge() {
    Spacer(modifier = Modifier.size(Spacing.l))
}