/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope

internal expect abstract class ViewModel constructor() {
    protected val scope: CoroutineScope
}
