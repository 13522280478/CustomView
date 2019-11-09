package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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
//GestureDetector.OnGestureListener
//    GestureDetector.OnDoubleTapListener
public class ScalableImageView extends View {

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
    float currentScale;
    float maxOffSetX;
    float maxOffSetY;
    GestureDetectorCompat detector;
    ObjectAnimator scaleAnimator;
    OverScroller scroller;
    GestureDetector.OnGestureListener henGestureListener = new HenGestureDetector();
    Runnable henAnimationRunner = new HenRunnable();
    ScaleGestureDetector scaleDetector;
    ScaleGestureDetector.OnScaleGestureListener henScaleListener = new HenScaleListener();

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = DrawUtils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        detector = new GestureDetectorCompat(context, henGestureListener);
        scroller = new OverScroller(context);
        scaleDetector = new ScaleGestureDetector(context, henScaleListener);
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
        currentScale = smallScale;
        maxOffSetX = (bitmap.getWidth() * bigScale - getWidth()) / 2;
        maxOffSetY = (bitmap.getHeight() * bigScale - getHeight()) / 2;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    public ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
//            scaleAnimator.addListener();
        }
        scaleAnimator.setFloatValues(smallScale, bigScale);
        return scaleAnimator;
    }

    public void setScaleAnimator(ObjectAnimator scaleAnimator) {
        this.scaleAnimator = scaleAnimator;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleDetector.onTouchEvent(event);
        if (!scaleDetector.isInProgress()) {
            detector.onTouchEvent(event);
        }
        return scaleDetector.onTouchEvent(event);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        canvas.scale(currentScale, currentScale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint);
    }


    private void fixOffsets() {
        offsetX = Math.max(Math.min(offsetX, maxOffSetX), -maxOffSetX);
        offsetY = Math.max(Math.min(offsetY, maxOffSetY), -maxOffSetY);
    }


    class HenGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (big) {
                scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                        -(int) maxOffSetX, (int) maxOffSetX, -(int) maxOffSetY, (int) maxOffSetY,
                        (int) DimensionUtils.dp2Px(50), (int) DimensionUtils.dp2Px(50));
                postOnAnimation(henAnimationRunner);
                ViewCompat.postOnAnimation(ScalableImageView.this, henAnimationRunner);
            }
            return false;
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
//            offsetX = Math.min(offsetX, maxOffSetX);
                offsetY -= distanceY;
//            offsetY = Math.min(offsetY, maxOffSetY);
                invalidate();
            }
            return false;
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
                offsetX = (e.getX() - getWidth() / 2f) * (1 - (bigScale / smallScale));
                offsetY = (e.getY() - getHeight() / 2f) * (1 - (bigScale / smallScale));
                fixOffsets();
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

    private class HenRunnable implements Runnable {

        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.getCurrX();
                offsetY = scroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }
        }
    }

    private class HenScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
        private float initialCurrentScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
//            detector.getScaleFactor();
//            detector.getFocusX();
//            detector.getFocusY();
            currentScale = initialCurrentScale * detector.getScaleFactor();
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialCurrentScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
