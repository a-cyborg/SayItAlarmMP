/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.atom.IconButtonAccent
import org.acyb.sayit.app.token.Icon

@Preview
@Composable
fun TopAppBarGlobalPreview() {
    TopAppBarGlobal(
        title = "SayIt",
        firstIcon = {
            IconButtonAccent(label = "Edit") {}
        },
        secondIcon = {
            IconButtonAccent(
                icon = Icon.add,
                contentDescription = "Generic content description",
            ) {}
        },
        thirdIcon = {
            IconButtonAccent(
                icon = Icon.Settings,
                contentDescription = "Generic content description"
            ) {}
        },
    )
}