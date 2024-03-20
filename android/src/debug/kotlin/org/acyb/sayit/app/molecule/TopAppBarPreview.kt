/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.atom.IconButtonAdd
import org.acyb.sayit.app.atom.IconButtonEditText
import org.acyb.sayit.app.atom.IconButtonSettings

@Preview
@Composable
fun TopAppBarGlobalPreview() {
    TopAppBarGlobal(
        title = "SayIt",
        firstIcon = { IconButtonEditText {} },
        secondIcon = { IconButtonAdd {} },
        thirdIcon = { IconButtonSettings {} },
    )
}