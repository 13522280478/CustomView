package com.example.viewprocessstudy.test;

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

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.example.util.DimensionUtils;
import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-12
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ScaleImageView2 extends View {

    private Bitmap bitmap;
    private float offSetX;
    private float offSetY;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int IMAGE_WIDTH = DimensionUtils.dp2Px(200);
    private float bigScale;
    private float smallScale;
    private float scale = 1;
    GestureDetectorCompat detector;
    boolean big;

    private float scaleFraction;

    private ObjectAnimator scaleAnimator;

    GestureDetector.OnGestureListener detectorCompat = new HenGestureDetectorCompat();
    ScaleGestureDetector.OnScaleGestureListener scaleListener = new HenScaleGestureDetector();

    public ScaleImageView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), IMAGE_WIDTH);
        detector = new GestureDetectorCompat(context, detectorCompat);
    }

    public float getScaleFraction() {

        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    public ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null){
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offSetX = (getWidth() - bitmap.getWidth()) / 2f;
        offSetY = (getHeight() - bitmap.getHeight()) / 2f;
        /**
         * 算法一
         */
        if ((float) (bitmap.getWidth() / bitmap.getHeight()) > ((float) getWidth() / getHeight())) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight();
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth();
        }

        /**
         * 算法二
         */
//        if ((float)(bitmap.getWidth()/getWidth())< ((float)(bitmap.getHeight()/getHeight()))){
////            相当于长屏幕，图片比较窄
//            smallScale = (float) getWidth()/bitmap.getWidth();
//            bigScale = (float) getHeight()/bitmap.getHeight();
//        }else {
//            smallScale = (float) getHeight()/bitmap.getHeight();
//            bigScale = (float) getWidth() / bitmap.getWidth();
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        scale = big ? bigScale : smallScale;
        scale = smallScale +(bigScale - smallScale) *scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, offSetX, offSetY, paint);
    }

    private class HenGestureDetectorCompat extends GestureDetector.SimpleOnGestureListener {


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
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
//            invalidate();
            if (big){
                getScaleAnimator().start();
            }else {
                getScaleAnimator().reverse();
            }
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return true;
        }

    }

    private class HenScaleGestureDetector implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

}
