package com.example.customview.utils;

import com.example.customview.App;

/**
 * @author YueShuai
 * @date 2019-11-06
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class DimensionUtils {

    public static int dp2Px(float dp) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
