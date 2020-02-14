package com.pltest.customtext0113

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import todp

/**
 * @author YueShuai
 * @date 2020-01-13
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */
class CustomText : View {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mRadius = 150.todp()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 30f
        canvas?.drawCircle(width / 2f, height / 2f, mRadius, mPaint)

        mPaint.color = Color.RED
        mPaint.strokeCap = Paint.Cap.ROUND
        canvas?.drawArc(width / 2f - mRadius,height/2f - mRadius,width/2+mRadius,height/2f+mRadius,-90f,225f,false,mPaint)
    }

}