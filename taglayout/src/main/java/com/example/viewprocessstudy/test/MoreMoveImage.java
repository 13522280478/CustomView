package com.example.viewprocessstudy.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-12
 * @Describe <p>
 * 多个手指滑动Image
 * Email : yueshuai@pupupula.com
 */
public class MoreMoveImage extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private float downX;
    private float downY;
    private float offSetX;
    private float offSetY;
    private float originOffSetX;
    private float originOffSetY;

    private int trackingPointerId;

    public MoreMoveImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), 300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                int actionIndex = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionIndex);
                downX = event.getX();
                downY = event.getY();
                originOffSetX = offSetX;
                originOffSetY = offSetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerIndex = event.findPointerIndex(trackingPointerId);
                offSetX = event.getX(pointerIndex) - downX + originOffSetX;
                offSetY = event.getY(pointerIndex) - downY + originOffSetY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex2 = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionIndex2);
                downX = event.getX(actionIndex2);
                downY = event.getY(actionIndex2);
                originOffSetX = offSetX;
                originOffSetY = offSetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                int actionIndex1 = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex1);
                if (pointerId == trackingPointerId) {
                    int newPointer;
                    if (actionIndex1 == event.getPointerCount() - 1) {
                        newPointer = event.getPointerCount() - 2;
                    } else {
                        newPointer = event.getPointerCount() - 1;
                    }
                    trackingPointerId = event.getPointerId(newPointer);
                    downX = event.getX(newPointer);
                    downY = event.getY(newPointer);
                    originOffSetX = offSetX;
                    originOffSetY = offSetY;
//                invalidate();
                }

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
