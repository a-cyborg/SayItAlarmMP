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