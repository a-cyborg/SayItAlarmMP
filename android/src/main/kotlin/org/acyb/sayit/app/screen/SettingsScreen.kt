/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import org.a_cyb.sayit.entity.Theme
import org.a_cyb.sayit.presentation.CommandContract.Command
import org.a_cyb.sayit.presentation.CommandContract.CommandReceiver
import org.a_cyb.sayit.presentation.SetSnoozeCommand
import org.a_cyb.sayit.presentation.SetThemeCommand
import org.a_cyb.sayit.presentation.SetTimeOutCommand
import org.a_cyb.sayit.presentation.SettingsContract
import org.a_cyb.sayit.presentation.SettingsContract.SettingsStateWithContent
import org.a_cyb.sayit.presentation.SettingsContract.SettingsViewModel
import org.acyb.sayit.BuildConfig
import org.acyb.sayit.R
import org.acyb.sayit.app.atom.ColumnScreenStandard
import org.acyb.sayit.app.atom.IconButtonEdit
import org.acyb.sayit.app.atom.IconButtonNavigateBack
import org.acyb.sayit.app.atom.PanelRowStandard
import org.acyb.sayit.app.atom.PanelStandard
import org.acyb.sayit.app.atom.SpacerLarge
import org.acyb.sayit.app.atom.SpacerXLarge
import org.acyb.sayit.app.atom.TextTitleStandardLarge
import org.acyb.sayit.app.molecule.PopUpPickerStandardWheel
import org.acyb.sayit.app.molecule.TextRowTimeDuration
import org.acyb.sayit.app.molecule.TextRowWarning
import org.acyb.sayit.app.molecule.TopAppBarGlobal

@Composable
private fun SettingsTopAppBar(onNavigateBack: () -> Unit) {
    TopAppBarGlobal(
        title = stringResource(id = R.string.settings),
        firstIcon = { IconButtonNavigateBack { onNavigateBack() } },
    )
}

@Suppress("MagicNumber")
@Composable
fun PanelItemTimeOut(value: Int, execute: (Command<out CommandReceiver>) -> Unit) {
    val timeOuts = (30..300).toList()
    var showPopUpPicker by remember { mutableStateOf(false) }

    PanelRowStandard(
        valueLabel = stringResource(id = R.string.timeout),
        value = stringResource(id = R.string.minute_short, value),
    ) {
        IconButtonEdit { showPopUpPicker = true }
    }

    if (showPopUpPicker) {
        PopUpPickerStandardWheel(
            title = stringResource(id = R.string.timeout),
            info = stringResource(id = R.string.info_timeout),
            pickerValues = (30..300).toList(),
            pickerInitIdx = timeOuts.indexOf(value),
            pickerItemRow = { TextRowTimeDuration(minutes = it) },
            onDismiss = { showPopUpPicker = false },
            onConfirm = { execute(SetTimeOutCommand(it)) },
        )
    }
}

@Suppress("MagicNumber")
@Composable
fun PanelItemSnooze(value: Int, execute: (Command<out CommandReceiver>) -> Unit) {
    val snoozes = (5..60).toList()
    var showPopUpPicker by remember { mutableStateOf(false) }

    PanelRowStandard(
        valueLabel = stringResource(id = R.string.snooze),
        value = stringResource(id = R.string.minute_short, value),
    ) {
        IconButtonEdit { showPopUpPicker = true }
    }

    if (showPopUpPicker) {
        PopUpPickerStandardWheel(
            title = stringResource(id = R.string.snooze),
            info = stringResource(id = R.string.info_snooze),
            pickerValues = snoozes,
            pickerInitIdx = snoozes.indexOf(value),
            pickerItemRow = { TextRowTimeDuration(minutes = it) },
            onDismiss = { showPopUpPicker = false },
            onConfirm = { execute(SetSnoozeCommand(it)) },
        )
    }
}

@Composable
fun PanelItemTheme(value: Theme, execute: (Command<out CommandReceiver>) -> Unit) {
    val themes = Theme.entries.map { it.name.toCamelCase() }
    var displayPopUpPicker by remember { mutableStateOf(false) }

    PanelRowStandard(
        valueLabel = stringResource(id = R.string.theme),
        value = value.name.toCamelCase(),
    ) {
        IconButtonEdit { displayPopUpPicker = true }
    }

    if (displayPopUpPicker) {
        PopUpPickerStandardWheel(
            title = stringResource(id = R.string.theme),
            pickerValues = themes,
            pickerInitIdx = themes.indexOf(value.name.toCamelCase()),
            pickerItemRow = { TextTitleStandardLarge(it) },
            onDismiss = { displayPopUpPicker = false },
            onConfirm = { execute(SetThemeCommand(Theme.valueOf(it.uppercase()))) },
        )
    }
}

private fun String.toCamelCase() = this.lowercase().replaceFirstChar(Char::titlecase)

@Composable
fun SettingsPanel(
    timeOut: Int,
    snooze: Int,
    theme: Theme,
    executor: (Command<out CommandReceiver>) -> Unit,
) {
    PanelStandard(
        panelItems = listOf(
            { PanelItemTimeOut(value = timeOut, execute = executor) },
            { PanelItemSnooze(value = snooze, execute = executor) },
            { PanelItemTheme(value = theme, execute = executor) },
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
    PanelRowStandard(
        valueLabel = stringResource(id = R.string.version),
        value = BuildConfig.VERSION_NAME,
    )
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
fun SettingsScreen(viewModel: SettingsViewModel) {
    val state = viewModel.state.collectAsState()

    ColumnScreenStandard {
        SettingsTopAppBar(onNavigateBack = {})
        SpacerLarge()

        when (state.value) {
            is SettingsStateWithContent -> {
                val settings = (state.value as SettingsStateWithContent)
                SettingsPanel(
                    timeOut = settings.timeOut.input,
                    snooze = settings.snooze.input,
                    theme = settings.theme,
                    executor = { viewModel.runCommand(it) },
                )
            }

            is SettingsContract.Error -> {
                val errorMessage = (state.value as SettingsContract.Error).error.name
                TextRowWarning(text = errorMessage)
            }
        }
        SpacerXLarge()
        InfoPanel()
    }
}
