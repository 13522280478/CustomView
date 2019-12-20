package com.example.customview.sideslip;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2019-12-20
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class SideslipView extends LinearLayout {

    public SideslipView(Context context) {
        super(context);
    }

    public SideslipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SideslipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SideslipView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
