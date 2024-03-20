/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.acyb.sayit.R
import org.acyb.sayit.app.token.Color
import org.acyb.sayit.app.token.Icon

@Composable
fun IconButtonAdd(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icon.add,
            tint = Color.text.accent,
            contentDescription = stringResource(id = R.string.action_add_alarm),
        )
    }
}

@Composable
fun IconButtonDelete(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icon.delete,
            tint = Color.text.danger,
            contentDescription = stringResource(id = R.string.action_delete_alarm),
        )
    }
}

@Composable
fun IconButtonEdit(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icon.editArrowRight,
            tint = Color.text.accent,
            contentDescription = stringResource(id = R.string.action_edit_alarm),
        )
    }
}

@Composable
fun IconButtonEditText(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        TextLabelAccent(text = stringResource(id = R.string.edit))
    }
}

@Composable
fun IconButtonSettings(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icon.Settings,
            tint = Color.text.accent,
            contentDescription = stringResource(id = R.string.action_open_settings),
        )
    }
}