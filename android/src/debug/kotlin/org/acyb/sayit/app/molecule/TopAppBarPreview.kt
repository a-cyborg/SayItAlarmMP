/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.atom.TopAppBarIcon
import org.acyb.sayit.app.token.Icon

@Preview
@Composable
fun TopAppBarGlobalPreview() {
    TopAppBarGlobal(
        title = "SayIt",
        firstItem = {
            TopAppBarIcon(label = "Edit") {}
        },
        secondItem = {
            TopAppBarIcon(
                icon = Icon.add,
                contentDescription = "Generic content description",
            ) {}
        },
        thirdItem = {
            TopAppBarIcon(
                icon = Icon.Settings,
                contentDescription = "Generic content description"
            ) {}
        },
    )
}