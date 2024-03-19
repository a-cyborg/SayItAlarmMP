/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AlarmListItemOnEditModePreview() {
    AlarmListItem(
        isEditMode = true,
        time = "8:30",
        label = "Wakeup Call",
        weeklyRepeat = "Everyday",
        isEnabled = true,
        onEnabledStateChange = {},
        onDeleteClick = {},
        onEditClick = {},
    )
}

@Preview
@Composable
fun AlarmListItemPreview() {
    AlarmListItem(
        isEditMode = false,
        time = "8:30",
        label = "Day about",
        weeklyRepeat = "Mon, Wed, Fri",
        isEnabled = true,
        onEnabledStateChange = {},
        onDeleteClick = {},
        onEditClick = {},
    )
}
