package com.example.viewprocessstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2019-11-05
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class CircleView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = 80;
    private static final float PADDING = 30;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int) (PADDING + RADIUS) * 2;
        int specWithMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWithSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = resolveSize(size,widthMeasureSpec);
        int height = resolveSize(size,heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private void reset(int size, int specWithMode, int specWithSize) {
        int width;
        switch (specWithMode) {
            case MeasureSpec.EXACTLY:
                width = specWithSize;
                break;
            case MeasureSpec.AT_MOST:
                if (size > specWithSize) {
                    width = specWithSize;
                } else {
                    width = size;
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                width = size;
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint);
    }
}
