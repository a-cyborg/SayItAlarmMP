/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.ViewModel as AndroidViewModel

internal actual abstract class ViewModel actual constructor() : AndroidViewModel() {
    actual val scope: CoroutineScope = viewModelScope
}
