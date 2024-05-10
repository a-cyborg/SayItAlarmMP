/*
 * Copyright (c) 2024 Mima Kang / All rights reserved.
 *
 * Use of this source code is governed by Apache v2.0
 */

package org.a_cyb.sayit.entity

import kotlin.jvm.JvmInline

@JvmInline
value class CombinedMinutes(val minutes: Int)

@JvmInline
value class Label(val label: String)

@JvmInline
value class Ringtone(val ringtone: String)

data class Alarm(
    val id: Long,
    val combinedMinutes: CombinedMinutes,
    val weeklyRepeat: WeeklyRepeat,
    val label: Label,
    val enabled: Boolean,
    val ringtone: Ringtone,
    val sayItScripts: List<String>,
)
