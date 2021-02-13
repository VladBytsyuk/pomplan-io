package io.pomplan.common.domain


data class Timer(
    val mode: Mode,
    val goalTime: Time,
    val elapsedTime: Time,
) {
    enum class Mode { PRE_WORK, WORK, PRE_BREAK, BREAK }
}

data class Time(val milliseconds: Long)