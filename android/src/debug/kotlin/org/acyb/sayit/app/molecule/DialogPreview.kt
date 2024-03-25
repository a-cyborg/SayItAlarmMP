/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.atom.IconButtonClose
import org.acyb.sayit.app.atom.IconButtonSaveText

@Preview
@Composable
fun DialogPreview() {
    DialogStandard(
        topAppBar = {
            TopAppBarGlobal(
                title = "Add Alarm",
                firstIcon = { IconButtonClose {} },
                secondIcon = { IconButtonSaveText {} },
            )
        },
        onDismiss = { }
    ) {}
}