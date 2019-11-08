package com.example.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author YueShuai
 * @date 2019-11-06
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class DrawUtils {

    public static Bitmap getAvatar(Resources res, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.drawable.ic_share_wx_scene_time_line_back_w32_h28, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(res, R.drawable.ic_share_wx_scene_time_line_back_w32_h28, options);
    }
}
