package io.pomplan.android.ui_kit

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleableRes


fun ViewGroup.inflateLayout(@LayoutRes layoutId: Int, attachToRoot: Boolean = true) =
    LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)!!

@SuppressLint("Recycle")
fun ViewGroup.withTypedArray(
    attrs: AttributeSet?,
    @StyleableRes styleable: IntArray,
    block: TypedArray.() -> Unit
) = context.obtainStyledAttributes(attrs, styleable)
    .apply { block() }
    .recycle()

fun View.withTypedArray(
    attrs: AttributeSet?,
    @StyleableRes styleable: IntArray,
    block: TypedArray.() -> Unit
) = context.obtainStyledAttributes(attrs, styleable)
    .apply { block() }
    .recycle()
