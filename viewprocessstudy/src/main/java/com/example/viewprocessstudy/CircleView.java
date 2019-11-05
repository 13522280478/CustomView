package com.example.viewprocessstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2019-11-05
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class CircleView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final  float RADIUS = 80;
    private static final float PADDING = 30;
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(PADDING + RADIUS,PADDING+RADIUS,RADIUS,paint);
    }
}
