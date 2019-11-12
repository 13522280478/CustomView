package com.example.viewprocessstudy.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;

/**
 * @author YueShuai
 * @date 2019-11-11
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class TextViewRandom extends AppCompatTextView {

    private static int[] TEXT_COLOR = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };

    private static int[] TEXT_SIZE = {16, 22, 28};
    private static final int PADDING_X = 6;
    private static final int PADDING_Y = 10;
    private static final int RADIUS = 4;
    private Paint paint = new Paint();

    private Random random = new Random();

    {
        setTextColor(Color.WHITE);
        setTextSize(TEXT_SIZE[random.nextInt(TEXT_SIZE.length)]);
        paint.setColor(TEXT_COLOR[random.nextInt(TEXT_COLOR.length)]);
        setPadding(PADDING_X, PADDING_Y, PADDING_X, PADDING_Y);
    }

    public TextViewRandom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), RADIUS, RADIUS, paint);
        super.onDraw(canvas);
    }
}
