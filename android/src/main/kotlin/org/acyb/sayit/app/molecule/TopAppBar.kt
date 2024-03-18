/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.acyb.sayit.app.atom.TextSubtle
import org.acyb.sayit.app.token.Color

@Composable
fun TopAppBarGlobal(
    title: String,
    firstItem: @Composable () -> Unit,
    secondItem: @Composable () -> Unit,
    thirdItem: @Composable () -> Unit,
) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextSubtle(text = title)
            }
        },
        navigationIcon = firstItem,
        actions = {
            Row {
                secondItem()
                thirdItem()
            }
        },
        backgroundColor = Color.Surface.light,
        contentColor = Color.Text.accent,
    )
}