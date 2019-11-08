package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;

import com.example.util.DimensionUtils;
import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-06
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private static final float IMAGE_WIDTH = DimensionUtils.dp2Px(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Bitmap bitmap;

    float originOffsetX;
    float originOffsetY;
    float offsetX;
    float offsetY;
    float bigScale;
    float smallScale;
    boolean big;
    float scaleFraction;
    float maxOffSetX;
    float maxOffSetY;
    GestureDetectorCompat detector;
    ObjectAnimator scaleAnimator;
    OverScroller scroller;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = DrawUtils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        detector = new GestureDetectorCompat(context, this);
        scroller = new OverScroller(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        originOffsetY = (getHeight() - bitmap.getHeight()) / 2f;

        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        maxOffSetX = (bitmap.getWidth() * bigScale - getWidth()) / 2;
        maxOffSetY = (bitmap.getHeight() * bigScale - getHeight()) / 2;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    public ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }

    public void setScaleAnimator(ObjectAnimator scaleAnimator) {
        this.scaleAnimator = scaleAnimator;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX, offsetY);
//        float scale = big ? bigScale : smallScale;
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
//        float scale = (float) getHeight() / bitmap.getHeight();
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //onMove
        if (big) {
            offsetX -= distanceX;
            offsetX = Math.max(Math.min(offsetX, maxOffSetX), -maxOffSetX);
//            offsetX = Math.min(offsetX, maxOffSetX);
            offsetY -= distanceY;
            offsetY = Math.max(Math.min(offsetY, maxOffSetY), -maxOffSetY);
//            offsetY = Math.min(offsetY, maxOffSetY);

            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (big) {
            scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                    -(int) maxOffSetX, (int) maxOffSetX, -(int) maxOffSetY, (int) maxOffSetY,
                    (int) DimensionUtils.dp2Px(50), (int) DimensionUtils.dp2Px(50));
            postOnAnimation(this);
            ViewCompat.postOnAnimation(this, this);
        }
        return false;
    }

    @Override
    public void run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //处理关键双击放大逻辑
        big = !big;
        if (big) {
            getScaleAnimator().start();
        } else {
            getScaleAnimator().reverse();
        }
        return false;
    }

    @Override


    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


}
