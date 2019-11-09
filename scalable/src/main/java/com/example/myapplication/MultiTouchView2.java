package com.example.myapplication;

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
 * @date 2019-11-09
 * @Describe <p>®
 * Email : yueshuai@pupupula.com
 */
public class MultiTouchView2 extends View {

    private static final int IMAGE_WIDTH = DimensionUtils.dp2Px(200);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    float offsetX;
    float offsetY;
    float downX;
    float downY;
    float originOffsetX;
    float originOffsetY;

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), IMAGE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        for (int i = 0; i < event.getPointerCount(); i++) {
            if (!isPointerUp || i != event.getActionIndex()) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        int pointerCount = event.getPointerCount();
        if (isPointerUp) {
            pointerCount--;
        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX;
                downY = focusY;
                originOffsetX = offsetX;
                originOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = focusX - downX + originOffsetX;
                offsetY = focusY - downY + originOffsetY;
                invalidate();
                break;
        }
        return true;
    }
}


//    int actionIndex = event.getActionIndex();
//                trackingPointerId = event.getPointerId(actionIndex);
//                        downX = focusX;
//                        downY = focusY;
//                        originOffsetX = offsetX;
//                        originOffsetY = offsetY;

//
//    int actionIndex1 = event.getActionIndex();
//    int pointerId = event.getPointerId(actionIndex1);
//                if (pointerId == trackingPointerId) {
//                        int newIndex;
//                        if (actionIndex1 == event.getPointerCount() - 1) {
//                        newIndex = event.getPointerCount() - 2;
//                        } else {
//                        newIndex = event.getPointerCount() - 1;
//                        }
//
//                        trackingPointerId = event.getPointerId(newIndex);
//                        downX = focusX;
//                        downY = focusY;
//                        originOffsetX = offsetX;
//                        originOffsetY = offsetY;
//                        }
