/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.acyb.sayit.R
import org.acyb.sayit.app.atom.SectionRowClickable
import org.acyb.sayit.app.atom.TextDisplayStandardLarge

@Composable
fun TimeSection(
    time: String,
    onClick: () -> Unit,
) {
    SectionRowClickable(
        contentDescription = stringResource(id = R.string.action_set_alarm_time),
        onClick = onClick
    ) {
        TextDisplayStandardLarge(text = time)
    }
}