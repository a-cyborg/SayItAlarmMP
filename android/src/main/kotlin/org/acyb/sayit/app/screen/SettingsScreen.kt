/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.a_cyb.sayit.entity.Snooze
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.entity.TimeOut
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.SettingsStateWithContent
import org.a_cyb.sayit.presentation.SettingsContract.SettingsViewModel
import org.acyb.sayit.R
import org.acyb.sayit.app.atom.IconButtonEdit
import org.acyb.sayit.app.atom.IconButtonNavigateBack
import org.acyb.sayit.app.atom.PanelRowStandard
import org.acyb.sayit.app.atom.PanelStandard
import org.acyb.sayit.app.atom.SpacerLarge
import org.acyb.sayit.app.atom.SpacerMedium
import org.acyb.sayit.app.atom.SpacerXLarge
import org.acyb.sayit.app.atom.TextBodyStandardSmall
import org.acyb.sayit.app.atom.TextTitleStandardLarge
import org.acyb.sayit.app.molecule.PopUpPickerStandardWheel
import org.acyb.sayit.app.molecule.TextRowTimeDuration
import org.acyb.sayit.app.molecule.TextRowWarning
import org.acyb.sayit.app.molecule.TopAppBarGlobal
import org.acyb.sayit.app.token.Color

@Composable
private fun SettingsTopAppBar(onNavigateBack: () -> Unit) {
    TopAppBarGlobal(
        title = stringResource(id = R.string.settings),
        firstIcon = { IconButtonNavigateBack { onNavigateBack() } },
    )
}

@Suppress("MagicNumber")
@Composable
fun PanelItemTimeOut(value: Int, onValueEdit: (Int) -> Unit) {
    val timeOuts = (30..300).toList()
    var showEditActionContent by remember { mutableStateOf(false) }

    PanelRowStandard(
        valueLabel = stringResource(id = R.string.timeout),
        value = stringResource(id = R.string.minute_short, value),
    ) {
        IconButtonEdit { showEditActionContent = true }
    }

    if (showEditActionContent) {
        PopUpPickerStandardWheel(
            title = stringResource(id = R.string.timeout),
            info = stringResource(id = R.string.info_timeout),
            pickerValues = (30..300).toList(),
            pickerInitIdx = timeOuts.indexOf(value),
            pickerItemRow = { TextRowTimeDuration(minutes = it) },
            onDismiss = { showEditActionContent = false },
            onConfirm = { onValueEdit(it) },
        )
    }
}

@Suppress("MagicNumber")
@Composable
fun PanelItemSnooze(value: Int, onValueEdit: (Int) -> Unit) {
    val snoozes = (5..60).toList()
    var showEditActionContent by remember { mutableStateOf(false) }

    PanelRowStandard(
        valueLabel = stringResource(id = R.string.snooze),
        value = stringResource(id = R.string.minute_short, value),
    ) {
        IconButtonEdit { showEditActionContent = true }
    }

    if (showEditActionContent) {
        PopUpPickerStandardWheel(
            title = stringResource(id = R.string.snooze),
            info = stringResource(id = R.string.info_snooze),
            pickerValues = snoozes,
            pickerInitIdx = snoozes.indexOf(value),
            pickerItemRow = { TextRowTimeDuration(minutes = it) },
            onDismiss = { showEditActionContent = false },
            onConfirm = { onValueEdit(it) },
        )
    }
}

@Composable
fun PanelItemTheme(value: Theme, onValueEdit: (Theme) -> Unit) {
    val themes = Theme.entries.map { it.name.toCamelCase() }
    var showEditActionContent by remember { mutableStateOf(false) }

    PanelRowStandard(
        valueLabel = stringResource(id = R.string.theme),
        value = value.name.toCamelCase(),
    ) {
        IconButtonEdit { showEditActionContent = true }
    }

    if (showEditActionContent) {
        PopUpPickerStandardWheel(
            title = stringResource(id = R.string.theme),
            pickerValues = themes,
            pickerInitIdx = themes.indexOf(value.name.toCamelCase()),
            pickerItemRow = { TextTitleStandardLarge(it) },
            onDismiss = { showEditActionContent = false },
            onConfirm = { onValueEdit(Theme.valueOf(it.uppercase())) },
        )
    }
}

private fun String.toCamelCase() = this.lowercase().replaceFirstChar(Char::titlecase)

@Composable
fun SettingsPanel(
    timeOut: Int,
    snooze: Int,
    theme: Theme,
    onTImeOutChange: (Int) -> Unit,
    onSnoozeChange: (Int) -> Unit,
    onThemeChange: (Theme) -> Unit,
) {
    PanelStandard(
        panelItems = listOf(
            { PanelItemTimeOut(value = timeOut, onValueEdit = onTImeOutChange) },
            { PanelItemSnooze(value = snooze, onValueEdit = onSnoozeChange) },
            { PanelItemTheme(value = theme, onValueEdit = onThemeChange) },
        ),
    )
}

@Composable
fun PanelItemAbout() {
    var showText by remember { mutableStateOf(false) }

    PanelRowStandard(valueLabel = stringResource(id = R.string.about)) {
        IconButtonEdit { showText = true }
    }
}

@Composable
fun PanelItemVersion() {
    PanelRowStandard(valueLabel = stringResource(id = R.string.version), value = "")
}

@Composable
fun InfoPanel() {
    PanelStandard(
        panelItems = listOf(
            { PanelItemAbout() },
            { PanelItemVersion() },
        ),
    )
}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
) {
    val state = viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState())
            .background(Color.surface.standard),
    ) {
        SettingsTopAppBar(onNavigateBack = {})
        SpacerLarge()

        when (state.value) {
            is SettingsStateWithContent -> {
                val settings = (state.value as SettingsStateWithContent)
                SettingsPanel(
                    timeOut = settings.timeOut.input,
                    snooze = settings.snooze.input,
                    theme = settings.theme,
                    onTImeOutChange = { viewModel.setTimeOut(TimeOut(it)) },
                    onSnoozeChange = { viewModel.setSnooze(Snooze(it)) },
                    onThemeChange = { viewModel.setTheme(it) },
                )
            }

            is SettingsContract.Error -> {
                val errorMessage = (state.value as SettingsContract.Error).detail
                TextRowWarning(text = errorMessage)
            }
        }
        SpacerXLarge()
        InfoPanel()
        Spacer(modifier = Modifier.weight(1f))
        TextBodyStandardSmall(text = stringResource(R.string.app_name))
        SpacerMedium()
    }
}
