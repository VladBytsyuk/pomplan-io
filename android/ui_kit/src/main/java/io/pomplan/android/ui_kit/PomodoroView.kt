package io.pomplan.android.ui_kit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import io.pomplan.android.ui_kit.PomodoroView.Data.*


class PomodoroView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val stroke = context.resources.getDimension(R.dimen.pomodoro_stroke_width)
    private val activeColor = ContextCompat.getColor(context, R.color.red_dark)
    private val inactiveColor = ContextCompat.getColor(context, R.color.grey_dark)

    enum class Data { EMPTY, CONTOURED, FILLED }
    var data: Data = EMPTY
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint()
        .apply { strokeWidth = stroke }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        with (paint) {
            color = if (data == EMPTY) inactiveColor else activeColor
            style = if (data == FILLED) Paint.Style.FILL_AND_STROKE else Paint.Style.STROKE
        }
        canvas.drawCircle(width / 2f, height / 2f, width / 2f - stroke / 2, paint)
    }
}
