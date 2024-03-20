/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.app.atom.IconButtonDelete
import org.acyb.sayit.app.atom.IconButtonEdit

@Preview
@Composable
fun AlarmListItemPreview() {
    AlarmListItem(
        time = "8:30",
        weeklyRepeat = "Wakeup Call, Everyday",
    )
}

@Preview
@Composable
fun AlarmListItemEditModePreview() {
    AlarmListItem(
        time = "8:30",
        weeklyRepeat = "Wakeup Call, Everyday",
        beforeContent = { IconButtonDelete {} },
        afterContent = { IconButtonEdit {} }
    )
}
