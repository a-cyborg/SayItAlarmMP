/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.acyb.sayit.R
import org.acyb.sayit.app.atom.DividerStandard
import org.acyb.sayit.app.atom.SpacerMedium
import org.acyb.sayit.app.atom.SpacerSmall
import org.acyb.sayit.app.atom.TextHeadlineStandardSmall
import org.acyb.sayit.app.token.Color

@Composable
fun AlarmListColumn(
    alarmListItems: List<@Composable () -> Unit>,
) {
    LazyColumn(
        modifier = Modifier.background(Color.surface.standard),
    ) {
        item {
            Row {
                SpacerMedium()
                TextHeadlineStandardSmall(text = stringResource(id = R.string.alarms))
            }
            SpacerSmall()
        }

        items(alarmListItems) {
            it()
            DividerStandard()
            SpacerSmall()
        }
    }
}
