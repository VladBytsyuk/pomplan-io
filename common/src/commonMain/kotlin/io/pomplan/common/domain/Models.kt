package io.pomplan.common.domain


data class Pomodoro(
    val mode: Mode,
    val goalTime: Time,
    val elapsedTime: Time,
    val currentPomodoro: Int
) {
    enum class Mode { PRE_WORK, WORK, PRE_BREAK, BREAK }
}

data class Time(val milliseconds: Long = 0) {
    init { require(milliseconds >= 0) }

    constructor(minute: Int = 0, second: Int = 0) :
            this(milliseconds = minute * MILLIS_IN_MINUTE + second * MILLIS_IN_SECOND)

    val minute: Int get() = (milliseconds / MILLIS_IN_MINUTE).toInt()
    val seconds: Int get() = (milliseconds % MILLIS_IN_MINUTE / MILLIS_IN_SECOND).toInt()
}
