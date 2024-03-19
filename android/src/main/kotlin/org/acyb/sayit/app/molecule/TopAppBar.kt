/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.acyb.sayit.app.atom.TitleCentered
import org.acyb.sayit.app.token.Color

@Composable
fun TopAppBarGlobal(
    title: String,
    firstIcon: @Composable () -> Unit,
    secondIcon: @Composable () -> Unit,
    thirdIcon: @Composable () -> Unit,
) {
    TopAppBar(
        title = { TitleCentered(title = title) },
        navigationIcon = firstIcon,
        actions = { secondIcon(); thirdIcon() },
        backgroundColor = Color.Surface.light,
        contentColor = Color.Text.accent,
        elevation = 0.dp,
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
    )
}