package com.example.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import todp

/**
 * @author YueShuai
 * @date 2019-12-09
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */
class ProgressText : View {

    private val mBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mDataList = mutableListOf<String>()
    private val mBgHeight = 70.todp()
    private val mBgRadius = 35.todp()
    private val mRectBounds = Rect()
    private val mFontMetrics = Paint.FontMetrics()
    //anim
    var mAnimOffX = 0f
      set(value){
          field = value
          invalidate()
      }
    private val mAnim = ObjectAnimator.ofFloat(this,"mAnimOffX",0f,0f);

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        mTextPaint.color = Color.RED
        mTextPaint.textSize = 20.todp()
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextBackgroundPaint.color = Color.GREEN
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawBackground(this)
            drawTextBg(this)
            drawTexts(this)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        mBackgroundPaint.color = Color.GRAY
        mBackgroundPaint.style = Paint.Style.FILL
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            mBgHeight,
            mBgRadius,
            mBgRadius,
            mBackgroundPaint
        )
    }

    private fun drawTexts(canvas: Canvas) {
        val mTextWidth = width / mDataList.size
        var count = 0
        mDataList.forEach {
            mTextPaint.getTextBounds(it, 0, 1, mRectBounds)

            mTextPaint.getFontMetrics(mFontMetrics)
            canvas.drawText(
                it,
                mTextWidth * count.toFloat() + mTextWidth / 2,
                mBgHeight / 2 - (mFontMetrics.bottom + mFontMetrics.top) / 2,
                mTextPaint
            )
            count++
        }
    }

    private fun drawTextBg(canvas: Canvas) {
        val mTextWidth = width / mDataList.size
        val mRight = mAnimOffX + mTextWidth
        canvas.drawRoundRect(
            mAnimOffX,
            0f,
            mRight,
            mBgHeight,
            mBgRadius,
            mBgRadius,
            mTextBackgroundPaint
        )
    }

    fun setDataList(list: List<String>) {
        mDataList.clear()
        mDataList.addAll(list)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val mTextWidth = width / mDataList.size
        when (event?.actionMasked) {
            MotionEvent.ACTION_UP -> {
                val y = event.y
                if (y > mBgHeight) {
                    return true
                }
                val x = event.x
                var count = 0
                while (count < mDataList.size) {
                    count++
                    if (x < count * mTextWidth) {
                        count--
                        var animValued = (count*mTextWidth).toFloat()
                        mAnim.setFloatValues(mAnimOffX,animValued)
                        mAnim.start()
                        break
                    }
                }
            }
        }
        return true
    }

}