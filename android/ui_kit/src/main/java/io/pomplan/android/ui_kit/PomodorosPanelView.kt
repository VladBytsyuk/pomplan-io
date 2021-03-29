package io.pomplan.android.ui_kit

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.pomplan.android.ui_kit.PomodoroView.Data.*


class PomodoroPanelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init { inflateLayout(R.layout.view_pomodoro_panel) }


    data class Data(val doneCount: Int, val currentInProgress: Boolean)

    var count: Data = Data(doneCount = 0, currentInProgress = false)
        set (value) {
            field = value
            val (donePomodoroViews, currentPomodoroView, restPomodoroViews) = splitViews(value.doneCount)
            donePomodoroViews.forEach { it.data = FILLED }
            currentPomodoroView.data = if (value.currentInProgress) CONTOURED else EMPTY
            restPomodoroViews.forEach { it.data = EMPTY }
        }


    private val views = arrayOf<PomodoroView>(
        findViewById(R.id.pv1),
        findViewById(R.id.pv2),
        findViewById(R.id.pv3),
        findViewById(R.id.pv4),
        findViewById(R.id.pv5),
        findViewById(R.id.pv6),
        findViewById(R.id.pv7),
        findViewById(R.id.pv8),
        findViewById(R.id.pv9),
        findViewById(R.id.pv10),
        findViewById(R.id.pv11),
        findViewById(R.id.pv12),
        findViewById(R.id.pv13),
        findViewById(R.id.pv14),
        findViewById(R.id.pv15),
        findViewById(R.id.pv16),
    )

    private fun splitViews(value: Int): Triple<List<PomodoroView>, PomodoroView, List<PomodoroView>> {
        val donePomodoroViews = views.filterIndexed { index, _ -> index + 1 < value }
        val currentPomodoroView = views[value - 1]
        val restPomodoroViews = views.filterIndexed { index, _ -> index + 1 > value }
        return Triple(donePomodoroViews, currentPomodoroView, restPomodoroViews)
    }
}
