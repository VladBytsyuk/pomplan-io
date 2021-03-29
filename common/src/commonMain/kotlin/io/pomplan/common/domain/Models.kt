package io.pomplan.common.domain

import io.pomplan.common.elm.State


data class Pomodoro(
    val mode: Mode,
    val goalTime: Time,
    val elapsedTime: Time,
    val lastDoneNumber: Int
) {
    enum class Mode { PRE_WORK, WORK, PRE_BREAK, BREAK }
}

data class Settings(
    val workTime: Time,
    val shortBreakTime: Time,
    val longBreakTime: Time,
    val groupSize: Int
)


val Pomodoro.done: Boolean get() = elapsedTime.isZero
val Pomodoro.number get() = lastDoneNumber + 1
private val State.lastInGroup: Boolean get() = pomodoro.number % settings.groupSize == 0
val State.nextBreakTime: Time get() = if (lastInGroup) settings.longBreakTime else settings.shortBreakTime

data class Time(val milliseconds: Long = 0) : Comparable<Long>{
    init { require(milliseconds >= 0) }

    constructor(minute: Int = 0, second: Int = 0) :
            this(milliseconds = minute * MILLIS_IN_MINUTE + second * MILLIS_IN_SECOND)

    val minute: Int = (milliseconds / MILLIS_IN_MINUTE).toInt()
    val seconds: Int = (milliseconds % MILLIS_IN_MINUTE / MILLIS_IN_SECOND).toInt()
    override fun toString(): String = "$minuteString:$secondsString"

    val minuteString: String get() = if (minute.toString().length == 1) "0$minute" else minute.toString()
    val secondsString: String get() = if (seconds.toString().length == 1) "0$seconds" else seconds.toString()

    val isZero: Boolean = milliseconds == 0L


    override fun compareTo(other: Long): Int = milliseconds.compareTo(other)
    operator fun plus(other: Long): Time = Time(milliseconds = milliseconds + other)
    operator fun minus(other: Long): Time = Time(milliseconds = milliseconds - other)
}
