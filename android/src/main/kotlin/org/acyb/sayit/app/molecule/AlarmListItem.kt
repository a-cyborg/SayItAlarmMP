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
import androidx.compose.ui.res.stringResource
import org.acyb.sayit.R
import org.acyb.sayit.app.atom.IconButtonAccent
import org.acyb.sayit.app.atom.IconButtonDanger
import org.acyb.sayit.app.atom.SpacerLarge
import org.acyb.sayit.app.atom.SwitchStandard
import org.acyb.sayit.app.atom.TextBodyStandard
import org.acyb.sayit.app.atom.TextLabelStandard
import org.acyb.sayit.app.token.Color
import org.acyb.sayit.app.token.Icon

@Composable
fun AlarmListItem(
    isEditMode: Boolean,
    time: String,
    label: String,
    weeklyRepeat: String,
    isEnabled: Boolean,
    onEnabledStateChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.Surface.light),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        when (isEditMode) {
            true -> DeleteIconButton { onDeleteClick() }
            false -> SpacerLarge()
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            TextBodyStandard(text = time)
            TextLabelStandard(text = "$label, $weeklyRepeat")
        }

        when (isEditMode) {
            true -> EditIconButton { onEditClick() }
            false -> SwitchStandard(checked = isEnabled) { isEnabled ->
                onEnabledStateChange(isEnabled)
            }
        }
    }
}

@Composable
private fun DeleteIconButton(onDeleteAlarm: () -> Unit) {
    IconButtonDanger(
        icon = Icon.delete,
        contentDescription = stringResource(id = R.string.action_delete_alarm),
        onClick = onDeleteAlarm
    )
}

@Composable
private fun EditIconButton(onEditAlarm: () -> Unit) {
    IconButtonAccent(
        icon = Icon.editArrowRight,
        contentDescription = stringResource(id = R.string.action_edit_alarm),
        onClick = onEditAlarm
    )
}
