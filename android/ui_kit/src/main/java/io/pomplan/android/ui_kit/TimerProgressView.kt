package io.pomplan.android.ui_kit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class TimerProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val stroke = context.resources.getDimension(R.dimen.timer_progress_stroke_width)
    private val workColor = ContextCompat.getColor(context, R.color.red_dark)
    private val breakColor = ContextCompat.getColor(context, R.color.grey_dark)

    data class Data(val goal: Long, val elapsed: Long, val isWork: Boolean) {
        init { require(goal > 0) }
    }


    private var angle: Float = 0f
    var data: Data = Data(goal = 1, elapsed = 1, isWork = false)
        set(value) {
            field = value
            angle = (360f * value.elapsed / value.goal).let { if (value.isWork) -it else it - 360 }
            invalidate()
        }


    private val oval = RectF()
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            val shift = stroke / 2
            oval.left = shift
            oval.top = shift
            oval.right = width - shift
            oval.bottom = height - shift
        }
    }

    private val accentPaint = Paint()
        .apply { style = Paint.Style.STROKE }
        .apply { strokeWidth = stroke  }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        accentPaint.color = if (data.isWork) workColor else breakColor
        canvas.drawArc(oval, -90f, angle, false, accentPaint)
    }
}
