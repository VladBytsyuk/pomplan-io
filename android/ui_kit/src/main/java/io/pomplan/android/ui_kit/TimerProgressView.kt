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
    data class Data(val goal: Long, val elapsed: Long, val isWork: Boolean) {
        init { require(goal > 0) }
    }


    private var angle: Float = 0f
    var data: Data = Data(goal = 1, elapsed = 1, isWork = false)
        set(value) {
            field = value
            angle =  360f * value.elapsed / value.goal
        }


    private val oval = RectF()
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            oval.left = 0f
            oval.top = 0f
            oval.right = width.toFloat()
            oval.bottom = height.toFloat()
        }
    }

    private val accentPaint = Paint()
        .apply { style = Paint.Style.STROKE }
        .apply { strokeWidth = 4f }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        accentPaint.color = (if (data.isWork) R.color.red_dark else R.color.grey_dark)
            .let { ContextCompat.getColor(context, it) }
        val startAngle = if (data.isWork) -90f else angle - 450
        canvas.drawArc(oval, startAngle, angle, false, accentPaint)
    }
}
