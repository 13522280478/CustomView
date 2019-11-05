package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author YueShuai
 * @date 2019-10-28
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */
class CustomView : View {
    val paint = Paint()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        paint.isAntiAlias = true
//        canvas?.drawColor(Color.RED)
        // 画园
        canvas?.drawColor(Color.RED)
        canvas?.drawCircle(50f, 50f, 50f, paint)

        paint.style = Paint.Style.STROKE
        canvas?.drawCircle(100 + 10 + 50f, 50f, 50f, paint)

        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        canvas?.drawRect(0f,120f,210f,180f,paint)

        paint.strokeWidth = 15f
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawPoint(200f,210f,paint)

        paint.style = Paint.Style.FILL
        canvas?.drawOval(0f,220f,210f,260f,paint)

        paint.color = Color.GREEN
        canvas?.drawLine(0f,300f,210f,390f,paint)

        paint.color = Color.DKGRAY
        canvas?.drawRoundRect(220f,0f,380f,110f,20f,20f,paint)
    }

}