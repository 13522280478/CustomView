package com.example.viewprocessstudy.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.util.DimensionUtils;
import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-12
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class MoreMoveImage2 extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float offSetX;
    private float offSetY;
    private float originOffsetX;
    private float originOffsetY;
    private float downX;
    private float downY;
    private int IMAGE_WIDTH = DimensionUtils.dp2Px(200);
    private Bitmap bitmap;

    public MoreMoveImage2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        float focusX;
        float focusY;

        Boolean isActionUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        for (int i = 0; i < event.getPointerCount(); i++) {
            /**
             * 没想明白为啥  i != event.getActionIndex()
             */
            if (!isActionUp || i != event.getActionIndex()) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        int pointerCount = event.getPointerCount();
        if (isActionUp) {
            pointerCount--;
        }
        focusX = sumX / pointerCount;
        focusY = sumY / pointerCount;


        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX;
                downY = focusY;
                originOffsetX = offSetX;
                originOffsetY = offSetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offSetX = focusX - downX + originOffsetX;
                offSetY = focusY - downY + originOffsetY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offSetX, offSetY, paint);
    }
}
