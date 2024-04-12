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
            tint = Color.text.attention,
            contentDescription = stringResource(id = R.string.action_add_alarm),
        )
    }
}

@Composable
fun IconButtonClose(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icon.close,
            tint = Color.text.attention,
            contentDescription = stringResource(id = R.string.action_close),
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
            tint = Color.text.attention,
            contentDescription = stringResource(id = R.string.action_edit_alarm),
        )
    }
}

@Composable
fun IconButtonSettings(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icon.settings,
            tint = Color.text.attention,
            contentDescription = stringResource(id = R.string.action_open_settings),
        )
    }
}

@Composable
fun IconButtonEditText(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        TextLabelAttentionLarge(text = stringResource(id = R.string.edit))
    }
}

@Composable
fun IconButtonSaveText(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        TextLabelAttentionLarge(text = stringResource(id = R.string.save))
    }
}
