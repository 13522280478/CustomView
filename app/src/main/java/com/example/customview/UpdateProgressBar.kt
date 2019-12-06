package com.example.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import toTextSizeValue
import todp
import kotlin.math.cos

/**
 * @author TuXin
 * @date 2019-10-24 13:31.
 *
 * Email : tuxin@pupupula.com
 */
class UpdateProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        val DEFAULT_PROGRESS_BAR_RADIUS = 6.todp()
        val DEFAULT_PROGRESS_HEIGHT = 8.todp()
        val DEFAULT_PROGRESS_BAR_COLOR = Color.argb(0xFF, 0xFF, 0xE1, 0x26)
        val DEFAULT_PROGRESS_BACKGROUND_BAR_COLOR = Color.argb(0xFF, 0xF5, 0xF5, 0xF5)
        val DEFAULT_PROGRESS_BACKGROUND_BAR_HEIGHT = 2.todp()
        val DEFAULT_PROGRESS_BACKGROUND_BAR_RADIUS = 6.todp()
        val DEFAULT_INDICATOR_RADIUS = 3.todp()
        val DEFAULT_INDICATOR_WIDTH = 48.todp()
        val DEFAULT_INDICATOR_RECT_HEIGHT = 28.todp()
        val DEFAULT_INDICATOR_TRIANGLE_WIDTH = 9.todp()

        val DEFAULT_INDICATOR_TRIANGLE_HEIGHT =
            cos(Math.toRadians(30.0)) * DEFAULT_INDICATOR_TRIANGLE_WIDTH
        const val DEFAULT_INDICATOR_COLOR = Color.WHITE
        val DEFAULT_DEFAULT_INDICATOR_MARGIN_TOP = 11.todp()
        const val DEFAULT_TEXT_COLOR = Color.BLACK
        val DEFAULT_TEXT_SIZE = 16.toTextSizeValue()
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mIndicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mIndicatorPath = Path()
    private val mFontMetrics = Paint.FontMetricsInt()

    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextBounds = Rect()
    private val mProgressAnimator = ObjectAnimator.ofFloat(this, "mFloatProgress", 0F, 0F)

    var mProgressRadius = DEFAULT_PROGRESS_BAR_RADIUS
        set(value) {
            field = value
            invalidate()
        }

    var mProgressHeight = DEFAULT_PROGRESS_HEIGHT
        set(value) {
            field = value
            invalidate()
        }

    var mProgress: Int = 0
        set(value) {
            if (mProgressAnimator.isRunning) {
                mProgressAnimator.end()
            }
            mProgressAnimator.setFloatValues(
                mProgressAnimator.animatedValue as Float,
                value.toFloat()
            )
            mProgressAnimator.start()
            field = value
        }

    var mFloatProgress = 0F
        set(value) {
            field = value
            invalidate()
        }

    var mProgressBarColor = DEFAULT_PROGRESS_BAR_COLOR
        set(value) {
            field = value
            invalidate()
        }
    var mProgressBackgroundBarHeight = DEFAULT_PROGRESS_BACKGROUND_BAR_HEIGHT
        set(value) {
            field = value
            invalidate()
        }

    var mProgressBackgroundBarRadius = DEFAULT_PROGRESS_BACKGROUND_BAR_RADIUS
        set(value) {
            field = value
            invalidate()
        }

    init {
        mIndicatorPaint.style = Paint.Style.FILL
        mIndicatorPaint.color = DEFAULT_INDICATOR_COLOR

        mTextPaint.color = DEFAULT_TEXT_COLOR
        mTextPaint.textSize = DEFAULT_TEXT_SIZE
//        mTextPaint.typeface = FontUtil.getAxiformaSemiBold()
        mTextPaint.getFontMetricsInt(mFontMetrics)

        val floatArray = floatArrayOf(
            DEFAULT_INDICATOR_RADIUS, DEFAULT_INDICATOR_RADIUS,
            DEFAULT_INDICATOR_RADIUS, DEFAULT_INDICATOR_RADIUS,
            DEFAULT_INDICATOR_RADIUS, DEFAULT_INDICATOR_RADIUS,
            DEFAULT_INDICATOR_RADIUS, DEFAULT_INDICATOR_RADIUS
        )
        val indicatorRectTop = DEFAULT_INDICATOR_TRIANGLE_HEIGHT.toFloat()
        mIndicatorPath.addRoundRect(
            0F,
            indicatorRectTop,
            DEFAULT_INDICATOR_WIDTH,
            indicatorRectTop + DEFAULT_INDICATOR_RECT_HEIGHT,
            floatArray,
            Path.Direction.CW
        )

        // 先移动到顶点
        mIndicatorPath.moveTo(DEFAULT_INDICATOR_WIDTH / 2, 0F)
        // 左下角
        mIndicatorPath.lineTo(
            DEFAULT_INDICATOR_WIDTH / 2 - DEFAULT_INDICATOR_TRIANGLE_WIDTH / 2,
            DEFAULT_INDICATOR_TRIANGLE_HEIGHT.toFloat()
        )
        // 右下角
        mIndicatorPath.lineTo(
            DEFAULT_INDICATOR_WIDTH / 2 + DEFAULT_INDICATOR_TRIANGLE_WIDTH / 2,
            DEFAULT_INDICATOR_TRIANGLE_HEIGHT.toFloat()
        )

        mIndicatorPath.lineTo(DEFAULT_INDICATOR_WIDTH / 2, 0F)

        mProgressAnimator.duration = 200
        mProgressAnimator.interpolator = LinearInterpolator()
    }

    override fun onDraw(canvas: Canvas) {
        drawProgressBackgroundBar(canvas)
        drawProgressBar(canvas)
        drawIndicator(canvas)
        drawText(canvas)
    }

    private fun drawProgressBar(canvas: Canvas) {
        if (mFloatProgress == 0F) {
            return
        }
        mPaint.color = mProgressBarColor
        val progressWidth = (mFloatProgress / 100F) * width
        canvas.drawRoundRect(
            0F,
            0F,
            progressWidth,
            mProgressHeight,
            mProgressRadius,
            mProgressRadius,
            mPaint
        )
    }

    private fun drawProgressBackgroundBar(canvas: Canvas) {
        mPaint.color = DEFAULT_PROGRESS_BACKGROUND_BAR_COLOR
        val top = (mProgressHeight - mProgressBackgroundBarHeight) / 2
        canvas.drawRoundRect(
            0F,
            top,
            width.toFloat(),
            top + mProgressBackgroundBarHeight,
            mProgressBackgroundBarRadius,
            mProgressBackgroundBarRadius,
            mPaint
        )
    }

    private fun drawIndicator(canvas: Canvas) {
        val progressWidth = (mFloatProgress / 100F) * width

        canvas.save()
        canvas.translate(
            // 先左移，然后偏移到 progress长度
            -DEFAULT_INDICATOR_WIDTH / 2 + progressWidth,
            // margin top + progress 的高度
            DEFAULT_DEFAULT_INDICATOR_MARGIN_TOP + DEFAULT_PROGRESS_HEIGHT
        )
        canvas.drawPath(mIndicatorPath, mIndicatorPaint)
        canvas.restore()
    }

    private fun drawText(canvas: Canvas) {
        val text = "$mProgress%"
        mTextPaint.getTextBounds(text, 0, text.length, mTextBounds)

        val topOffset =
            // indicator 的开始位置 + 三角形的高度 ==> indicator rect 的开始位置
            DEFAULT_DEFAULT_INDICATOR_MARGIN_TOP + DEFAULT_PROGRESS_HEIGHT + DEFAULT_INDICATOR_TRIANGLE_HEIGHT.toFloat() +
                    // 字体的偏移量
                    (-mTextBounds.top) +

                    // 针对方形居中
                    (DEFAULT_INDICATOR_RECT_HEIGHT - mTextBounds.height()) / 2

        val progressWidth = (mFloatProgress / 100F) * width
        val leftOffset =
            // 文字先居左
            -mTextBounds.left +
                    // 进度条偏移度
                    progressWidth +
                    // 文字居中处理
                    (-mTextBounds.width() / 2)

        canvas.drawText(text, leftOffset, topOffset, mTextPaint)

    }
}
