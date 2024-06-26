/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.acyb.sayit.app.atom.SpacerMedium
import org.acyb.sayit.app.atom.TextBodyStandardLarge
import org.acyb.sayit.app.atom.TextHeadlineStandardLarge
import org.acyb.sayit.app.token.Color

@Composable
fun AlarmListItem(
    time: String,
    weeklyRepeat: String,
    beforeContent: @Composable () -> Unit = {},
    afterContent: @Composable () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.surface.standard),
    ) {
        beforeContent()
        SpacerMedium()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            TextHeadlineStandardLarge(text = time)
            TextBodyStandardLarge(text = weeklyRepeat)
            SpacerMedium()
        }
        afterContent()
        SpacerMedium()
    }
}
