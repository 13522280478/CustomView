package com.example.viewprocessstudy.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.util.DimensionUtils;
import com.example.util.DrawUtils;

/**
 * @author YueShuai
 * @date 2019-11-12
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ScaleImageView extends View {
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int IMAGE_WIDTH = DimensionUtils.dp2Px(200);

    private float offSetX;
    private float offSetY;

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = DrawUtils.getAvatar(getResources(), IMAGE_WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offSetX = (getWidth() - bitmap.getHeight()) /2f;
        offSetY = (getHeight() - bitmap.getHeight()) /2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,offSetX,offSetY, paint);
    }
}
