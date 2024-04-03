/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.acyb.sayit.app.atom.CardStandard
import org.acyb.sayit.app.atom.TextTitleStandardLarge
import org.acyb.sayit.app.token.Spacing

@Composable
fun InfoTextCard(text: String) {
    CardStandard {
        Box(Modifier.padding(Spacing.s)) {
            TextTitleStandardLarge(text = text)
        }
    }
}