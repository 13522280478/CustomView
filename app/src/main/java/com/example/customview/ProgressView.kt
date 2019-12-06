package com.example.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import todp

/**
 * @author YueShuai
 * @date 2019-12-04
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */
class ProgressView : View {

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)


    private var mProgressAnimator = ObjectAnimator.ofFloat(this, "mFloatProgress", 0f, 0f)


    private var mPath = Path()
    private var PROGRESS_BOTTOM = 20f
    var PARH_RECT_LEFT_X = 0f
    var PARH_RECT_RIGHT_X = 0f
    var PARH_RECT_BOTTOM_Y = 0f
    var PARH_RECT_TOP_Y = 0f
    var PARH_RECT_HEIGHT = 0f
    var PATH_ARROW_HEIGHT = 20f
    var PATH_ARROW_WEIGHT = 20f
    var PATH_ARRAW_LEFT_X = 0f
    var PATH_ARRAW_RIGHT_X = 0f
    private var metrics = Paint.FontMetrics()
    var mProgress = 0f
        set(value) {
            if (mProgressAnimator.isRunning) {
                mProgressAnimator.end()
            }
            mProgressAnimator.setFloatValues(mProgressAnimator.animatedValue as Float, value)
            mProgressAnimator.start()
            field = value
            invalidate()
        }

    private var mFloatProgress = 0f
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        canvas?.drawLine(0f,PROGRESS_BOTTOM/2,width+0f,PROGRESS_BOTTOM/2,paint)
        val fl = mFloatProgress / 100f * width
        canvas?.drawRoundRect(0f, 0f, fl, PROGRESS_BOTTOM, PROGRESS_BOTTOM/2, PROGRESS_BOTTOM/2, paint)

        var textString = "${mProgress.toInt()}%"

        paint.textSize = 20.todp()
        paint.color = Color.RED

        var textRect = Rect()
        paint.getTextBounds(textString, 0, textString.length, textRect)
        val offsetY = (textRect.top + textRect.bottom) / 2

        paint.getFontMetrics(metrics)
        val offsetY1 = (metrics.ascent + metrics.descent) / 2
        val height = metrics.bottom - metrics.top
        val width = textRect.width()

        canvas?.drawText(textString, fl - (width / 2), PROGRESS_BOTTOM+(height/2) + PATH_ARROW_HEIGHT - offsetY1, paint)

        PARH_RECT_LEFT_X = fl - (width / 2)
        PARH_RECT_RIGHT_X = fl + (width / 2)
        PARH_RECT_HEIGHT = height
        PARH_RECT_BOTTOM_Y = PROGRESS_BOTTOM + PATH_ARROW_HEIGHT + PARH_RECT_HEIGHT
        PARH_RECT_TOP_Y = PROGRESS_BOTTOM + PATH_ARROW_HEIGHT
        PATH_ARRAW_LEFT_X = fl - (PATH_ARROW_WEIGHT / 2)
        PATH_ARRAW_RIGHT_X = fl + (PATH_ARROW_WEIGHT / 2)

        mPath.reset()
        mPath.moveTo(PARH_RECT_LEFT_X, PARH_RECT_BOTTOM_Y)
        mPath.lineTo(PARH_RECT_LEFT_X, PARH_RECT_TOP_Y)
        mPath.lineTo(PATH_ARRAW_LEFT_X, PARH_RECT_TOP_Y)
        mPath.lineTo(fl, PROGRESS_BOTTOM)
        mPath.lineTo(PATH_ARRAW_RIGHT_X, PARH_RECT_TOP_Y)
        mPath.lineTo(PARH_RECT_RIGHT_X, PARH_RECT_TOP_Y)
        mPath.lineTo(PARH_RECT_RIGHT_X, PARH_RECT_BOTTOM_Y)
        mPath.lineTo(PARH_RECT_LEFT_X, PARH_RECT_BOTTOM_Y)
        paint.style = Paint.Style.STROKE
        canvas?.drawPath(mPath, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
//                mDrawWidth = event.x
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
//                mDrawWidth = event.x
                invalidate()
            }
        }
        return true
    }


}