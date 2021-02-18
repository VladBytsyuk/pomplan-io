package io.pomplan.common.domain


val workTime = Time(second = 25)
val shortBreakTime = Time(second = 5)
val longBreakTime = Time(second = 15)
val groupSize = 4

data class Pomodoro(
    val mode: Mode,
    val goalTime: Time,
    val elapsedTime: Time,
    val lastDoneNumber: Int
) {
    enum class Mode { PRE_WORK, WORK, PRE_BREAK, BREAK }

    val done: Boolean = elapsedTime.isZero
    val number = lastDoneNumber + 1
    private val lastInGroup: Boolean = number % groupSize == 0
    val nextBreakTime: Time = if (lastInGroup) longBreakTime else shortBreakTime
}

data class Time(val milliseconds: Long = 0) : Comparable<Long>{
    init { require(milliseconds >= 0) }

    constructor(minute: Int = 0, second: Int = 0) :
            this(milliseconds = minute * MILLIS_IN_MINUTE + second * MILLIS_IN_SECOND)

    val minute: Int = (milliseconds / MILLIS_IN_MINUTE).toInt()
    val seconds: Int = (milliseconds % MILLIS_IN_MINUTE / MILLIS_IN_SECOND).toInt()
    override fun toString(): String {
        val m = if (minute.toString().length == 1) "0$minute" else minute.toString()
        val s = if (seconds.toString().length == 1) "0$seconds" else seconds.toString()
        return "$m:$s"
    }

    val isZero: Boolean = milliseconds == 0L


    override fun compareTo(other: Long): Int = milliseconds.compareTo(other)
    operator fun plus(other: Long): Time = Time(milliseconds = milliseconds + other)
    operator fun minus(other: Long): Time = Time(milliseconds = milliseconds - other)
}
