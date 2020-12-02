package com.rustfisher.tutorial2020.text

import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt

/**
 * 2020-12-2
 */
class DrawableSpan(private val drawable: Drawable) : ReplacementSpan() {
    private val padding: Rect = Rect()
    var textColor = Color.parseColor("#3792e5")

    init {
        drawable.getPadding(padding)
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val rect = RectF(x, top.toFloat(), x + measureText(paint, text, start, end), bottom.toFloat())
        drawable.setBounds(rect.left.toInt() - padding.left,
                rect.top.toInt() - padding.top,
                rect.right.toInt() + padding.right,
                (rect.bottom.toInt() + rect.height() / 1.2f).toInt())
        paint.color = textColor
        canvas.drawText(text, start, end, x, y.toFloat(), paint)
        drawable.draw(canvas)
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int = paint.measureText(text, start, end).roundToInt()

    private fun measureText(paint: Paint, text: CharSequence, start: Int, end: Int): Float = paint.measureText(text, start, end)

}
