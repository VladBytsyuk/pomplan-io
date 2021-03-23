package io.pomplan.android.ui_kit

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.Time


class TimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init { inflateLayout(R.layout.view_timer) }

    data class Data(val goal: Time, val elapsed: Time, val mode: Pomodoro.Mode)
    var data: Data = Data(goal = Time(), elapsed = Time(), mode = PRE_WORK)
        set (value) {
            field = value
            findViewById<TimerProgressView>(R.id.timerProgress).data = TimerProgressView.Data(
                goal = value.goal.milliseconds,
                elapsed = value.elapsed.milliseconds,
                isWork = value.mode in listOf(WORK, PRE_WORK)
            )
            findViewById<TextView>(R.id.timerMinutes).text = value.elapsed.minute
                .let { if (it.toString().length == 1) "0$it" else it.toString() }
            findViewById<TextView>(R.id.timerSeconds).text = value.elapsed.seconds
                .let { if (it.toString().length == 1) "0$it" else it.toString() }
        }
}
