package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.util.DimensionUtils;

/**
 * @author YueShuai
 * @date 2019-11-13
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class CustomView2 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final float RADIUS = DimensionUtils.dp2Px(100);
    Path path = new Path();
    PathMeasure pathMeasure = new PathMeasure();

    public CustomView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pathMeasure.getLength();
//        pathMeasure.getPosTan();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);//EVEN_ODD
        path.addCircle(getWidth() / 2f, getHeight() / 2f, RADIUS, Path.Direction.CCW);
        path.addRect(getWidth() / 2f - RADIUS, getHeight() / 2f, getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS * 2, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
     }
}
