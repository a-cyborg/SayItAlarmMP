/*
 * Copyright (c) 2024 Matthias Geisler (bitPogo) / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.molecule.TopAppBarGlobal

@Preview
@Composable
fun DialogStandardFillMaxPreview() {
    DialogStandardFillMax(
        onDismiss = {},
        topAppBar = {
            TopAppBarGlobal(
                title = "Dialog",
                firstIcon = { IconButtonClose {} },
                secondIcon = { },
            )
        },
    ) {}
}

@Preview
@Composable
fun DialogStandardFitContentPreview() {
    DialogStandardFitContent(onDismiss = { }) {
        TextTitleStandardLarge(text = "Dialog")
    }
}
