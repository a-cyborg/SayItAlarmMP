/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.acyb.sayit.app.molecule

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.acyb.sayit.app.atom.IconButtonSaveText
import org.acyb.sayit.app.token.Color

// This function is a very close implementation for quick implementation.
// It needs to be fixed later so that it can be used flexibly. (Check note at the end of the file)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> WheelPicker(
    values: List<T>,
    initIdx: Int = 0,
    itemRow: @Composable (T) -> Unit,
    pickerHeight: Dp = 280.dp,
    visibleItemNum: Int = 5,
    onConfirm: (T) -> Unit,
) {
    val itemRowHeight = remember { pickerHeight / visibleItemNum }
    val placeholderHeight = remember { itemRowHeight * (visibleItemNum / 2) }
    val lazyListState = rememberLazyListState()

    fun getSelectedItemIdx(): Int = when (lazyListState.firstVisibleItemIndex) {
        0 -> if (lazyListState.firstVisibleItemScrollOffset == 0) 0 else 1
        else -> lazyListState.firstVisibleItemIndex + 1
    }

    Column {
        IconButtonSaveText {
            onConfirm(values[getSelectedItemIdx()])
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(20.dp),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(pickerHeight)
                    .background(Color.surface.subtle),
                state = lazyListState,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(all = 0.dp)
            ) {
                // This item serves as a placeholder for the first item to be vertically centered.
                item { Spacer(modifier = Modifier.height(placeholderHeight)) }

                items(
                    count = values.size,
                    key = { it }
                ) {
                    Row(
                        modifier = Modifier.height(itemRowHeight),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        itemRow(values[it])
                    }
                }

                item { Spacer(modifier = Modifier.height(placeholderHeight)) }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemRowHeight)
                    .background(Color.surface.accent.copy(alpha = 0.3f))
            )
        }

        val context = LocalContext.current
        LaunchedEffect(Unit) {
            val rowHeightPx by lazy { itemRowHeight.value.toPx(context) }
            // Note: This implementation is optimized for 5 visible items.
            // Later to support a different number of items,
            // adjust the idx, scrollOffset calculation logic accordingly.
            // The scrollOffset should be a multiple of rowHeightPx,
            // based on the number of visible items above.
            val idx = if (initIdx == 0) initIdx else initIdx - 1
            val scrollOffset = if (initIdx == 1) rowHeightPx.toInt() else 0

            lazyListState.scrollToItem(idx, scrollOffset)
        }
    }
}

private fun Float.toPx(context: Context): Float =
    this * context.resources.displayMetrics.density
