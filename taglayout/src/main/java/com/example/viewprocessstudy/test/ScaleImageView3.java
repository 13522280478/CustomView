package com.example.viewprocessstudy.test;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-12
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ScaleImageView3 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private int IMAGE_WIDTH;
    private float offSetX;
    private float offSetY;
    private Boolean isBig = false;
    private float smallScale;
    private float bigScale;
    private float scale;
    private GestureDetector.OnGestureListener gestureListener = new HenGestureListener();
    private GestureDetectorCompat detectorCompat;

    private float scaleFraction;
    private ObjectAnimator objectAnimator;

    public ScaleImageView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), IMAGE_WIDTH);
        detectorCompat = new GestureDetectorCompat(context, gestureListener);
    }

    public ObjectAnimator getObjectAnimator() {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return objectAnimator;
    }

    public void setObjectAnimator(ObjectAnimator objectAnimator) {
        this.objectAnimator = objectAnimator;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detectorCompat.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offSetX = (getWidth() - bitmap.getWidth()) / 2f;
        offSetY = (getHeight() - bitmap.getHeight()) / 2f;

//        if ((float) (getWidth() / getHeight()) > (float) (bitmap.getWidth() / bitmap.getHeight())) {
//            smallScale = getHeight() / bitmap.getHeight();
//            bigScale = getWidth() / bitmap.getWidth();
//        } else {
//            bigScale = getHeight()/ bitmap.getHeight();
//            smallScale = getWidth() / bitmap.getWidth();
//        }
        if ((float) (bitmap.getWidth() / bitmap.getHeight()) > ((float) getWidth() / getHeight())) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight();
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        scale = isBig ? bigScale : smallScale;
        scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, offSetX, offSetY, paint);
    }


    class HenGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            isBig = !isBig;
            if (isBig){
                getObjectAnimator().start();
            }else {
                getObjectAnimator().reverse();
            }
            return true;
        }
    }

}
