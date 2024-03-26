/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.entity

import kotlin.jvm.JvmInline

@JvmInline
value class TimeOut(val timeOut: Int)

@JvmInline
value class Snooze(val snooze: Int)

enum class Theme {
    LIGHT,
    DARK
}

data class Setting(
    val timeOut: TimeOut,
    val snooze: Snooze,
    val theme: Theme,
)