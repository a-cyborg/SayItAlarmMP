/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import org.a_cyb.sayit.entity.Alarm

interface ListTaskerContract {
    val alarms: SharedFlow<Result<List<Alarm>>>

    fun load(scope: CoroutineScope)
    fun deleteAlarm(id: Long, scope: CoroutineScope)
}
