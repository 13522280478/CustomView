package com.example.viewprocessstudy;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author YueShuai
 * @date 2019-11-05
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class SquareImageView extends AppCompatImageView {

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public void layout(int l, int t, int r, int b) {
//        int witch = r - l;
//        int height = b - t;
//        int size = Math.min(witch,height);
//        super.layout(l, t, size, size);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();
        int size = Math.min(measuredWidth,measureHeight);
        setMeasuredDimension(size,size);
    }
}
