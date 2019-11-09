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
public class MultiTouchView1 extends View {

    private static final int IMAGE_WIDTH = DimensionUtils.dp2Px(200);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    float offsetX;
    float offsetY;
    float downX;
    float downY;
    float originOffsetX;
    float originOffsetY;
    int trackingPointerId;

    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
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
        for (int i = 0; i < event.getPointerCount(); i++) {
            event.getX(i);
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trackingPointerId = event.getPointerId(0);
                downX = event.getX();
                downY = event.getY();
                originOffsetX = offsetX;
                originOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trackingPointerId);
                offsetX = event.getX(index) - downX + originOffsetX;
                offsetY = event.getY(index) - downY + originOffsetY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);
                originOffsetX = offsetX;
                originOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                int actionIndex1 = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex1);
                if (pointerId == trackingPointerId) {
//                    int newIndex = event.getPointerCount() - 1;
                    int newIndex;
                    if (actionIndex1 == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }

                    trackingPointerId = event.getPointerId(newIndex);
                    downX = event.getX(newIndex);
                    downY = event.getY(newIndex);
                    originOffsetX = offsetX;
                    originOffsetY = offsetY;
                }
                break;
        }
        return true;
    }
}
