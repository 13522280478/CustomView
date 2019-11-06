package com.example.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author YueShuai
 * @date 2019-11-06
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class TouchLayout extends ViewGroup {

    int downY;

    public TouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            downY = (int) ev.getY();
        }
        Log.w("tagg", "onInterceptTouchEvent");
//        int delta = ev.getY() - downY;
//        if (Math.abs(delta) < SLOP) {
//            return false;
//        } else {
//            return true;
//        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.w("tagg", "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
