package com.example.viewprocessstudy.test;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author YueShuai
 * @date 2019-11-11
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class SquareImageViewTest extends AppCompatImageView {

    public SquareImageViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }

        @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = Math.min(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size, size);
    }
}
