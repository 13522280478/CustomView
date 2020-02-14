package com.pltest.customtext0113;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2020-02-13
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class CustomView2 extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float mWidth = 200;
    private int mHeight = 100;

    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String str = "删除";
        mPaint.setTextSize(50);

        mWidth = mPaint.measureText(str)+40;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.drawRoundRect(0, 0, mWidth, mHeight, 20, 20, mPaint);
        Path path = new Path();
        path.moveTo(mWidth / 2 - 10, mHeight);
        path.lineTo(mWidth / 2, mHeight + 10);
        path.lineTo(mWidth / 2 + 10, mHeight);
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.WHITE);


        Rect rect = new Rect();
        mPaint.setTextAlign(Paint.Align.CENTER);

        mPaint.getTextBounds(str, 0, str.length(), rect);
        int offx = (rect.top + rect.bottom) / 2;
        canvas.drawText(str, mWidth / 2, mHeight / 2 - offx, mPaint);
    }
}
