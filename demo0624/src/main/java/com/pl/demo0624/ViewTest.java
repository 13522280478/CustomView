package com.pl.demo0624;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2020/6/24
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ViewTest extends View {

    private GestureDetector detector;

    public ViewTest(Context context) {
        super(context);
    }

    public ViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
