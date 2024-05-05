/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.atom

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.acyb.sayit.R

@Preview
@Composable
fun PanelItemRowStandardNumPreview() {
    PanelRowStandard(
        valueLabel = stringResource(id = R.string.timeout),
        value = "30",
        afterContent = { IconButtonEdit { } },
    )
}
