package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.util.DimensionUtils;

/**
 * @author YueShuai
 * @date 2019-11-13
 * @Describe <p>
 * 画仪表盘
 * Email : yueshuai@pupupula.com
 */
public class DashboardView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int RADIUS = DimensionUtils.dp2Px(100);
    private static final int STROKE_WIDTH = DimensionUtils.dp2Px(3);
    private static final int OPEN_ANGLE = 90;
    Path dash = new Path();
    PathEffect effect;

    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dash.addRect(0, 0, DimensionUtils.dp2Px(2), DimensionUtils.dp2Px(10),Path.Direction.CW);
        effect = new PathDashPathEffect(dash, 50, 0, PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                OPEN_ANGLE / 2 + 90, 360 - OPEN_ANGLE, false, paint);
        paint.setPathEffect(effect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                OPEN_ANGLE / 2 + 90, 360 - OPEN_ANGLE, false, paint);
        paint.setPathEffect(null);

    }
}
