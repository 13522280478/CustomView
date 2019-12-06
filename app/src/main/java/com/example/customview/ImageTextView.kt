package com.example.customview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.util.DrawUtils
import todp

/**
 * @author YueShuai
 * @date 2019-12-05
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */

class ImageTextView : View {

    var str = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in" +
            " a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard " +
            "McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the " +
            "more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the " +
            "cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum" +
            " comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes" +
            " of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics," +
            " very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit " +
            "amet..\", comes from a line in section 1.10.32."


    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val metrics = Paint.FontMetrics()
    val rectBounds = Rect()

    val IMAGE_OFFSET = 100.todp()

    val IMAGE_WIDHT = 100.todp()

    private var bitmap: Bitmap? = null

    val cutWidth = floatArrayOf(1f)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs);

    init {
        mPaint.getFontMetrics(metrics)
        bitmap = DrawUtils.getAvatar(context.resources, IMAGE_WIDHT.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.getTextBounds(str, 0, str.length, rectBounds)
        mPaint.textSize = 20.todp()
//        val fl = (rectBounds.bottom - rectBounds.top) / 2

        canvas?.drawBitmap(bitmap!!, width - IMAGE_WIDHT, IMAGE_OFFSET, mPaint)
        var start = 0
        var count = 0
        var offsetY = mPaint.fontSpacing
        while (count < str.length) {
            if (((offsetY + rectBounds.bottom) > IMAGE_OFFSET && offsetY + rectBounds.bottom <( IMAGE_WIDHT + IMAGE_OFFSET)) ||
                ((rectBounds.top + offsetY )< IMAGE_OFFSET + IMAGE_WIDHT && (offsetY + rectBounds.top) > IMAGE_OFFSET)
            ) {
                count += mPaint.breakText(
                    str,
                    start,
                    str.length,
                    true,
                    width.toFloat() - IMAGE_OFFSET,
                    cutWidth
                )
            } else {
                count += mPaint.breakText(str, start, str.length, true, width.toFloat(), cutWidth)

            }
            canvas?.drawText(str, start, count, 0f, offsetY, mPaint)
            offsetY += mPaint.fontSpacing
            start = count
        }
    }
}