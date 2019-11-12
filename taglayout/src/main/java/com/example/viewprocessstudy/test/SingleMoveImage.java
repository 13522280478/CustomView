package com.example.viewprocessstudy.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-12
 * @Describe <p>
 *
 * 单手指滑动image
 *
 * Email : yueshuai@pupupula.com
 */
public class SingleMoveImage extends View {

    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float offSetX;
    private float offSetY;
    private float downX;
    private float downY;
    private float originOffSetX;
    private float originOffSetY;

    public SingleMoveImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), 300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                originOffSetX = offSetX;
                originOffSetY = offSetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offSetX = event.getX() - downX + originOffSetX;
                offSetY = event.getY() - downY + originOffSetY;
                invalidate();
                break;
        }
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, offSetX, offSetY, paint);
        super.onDraw(canvas);
    }
}
