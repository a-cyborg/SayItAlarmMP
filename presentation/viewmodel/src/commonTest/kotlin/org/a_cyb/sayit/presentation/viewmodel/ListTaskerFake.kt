/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.presentation.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.a_cyb.sayit.entity.Alarm
import org.a_cyb.sayit.presentation.interactor.ListTaskerContract

class ListTaskerFake(
    results: List<Result<List<Alarm>>>,
    scope: CoroutineScope,
) : ListTaskerContract {
    private val results = results.toMutableList()

    private val _alarms: MutableSharedFlow<Result<List<Alarm>>> = MutableSharedFlow()
    override val alarms: SharedFlow<Result<List<Alarm>>> = _alarms

    init {
        scope.launch { load(this) }
    }

    override fun load(scope: CoroutineScope) {
        scope.launch {
            _alarms.emit(results.removeFirst())
        }
    }

    override fun deleteAlarm(id: Long, scope: CoroutineScope) {
        scope.launch {
            _alarms.emit(results.removeFirst())
        }
    }
}
