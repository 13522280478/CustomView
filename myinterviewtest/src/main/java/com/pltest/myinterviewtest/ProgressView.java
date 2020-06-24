package com.pltest.myinterviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2020/4/3
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ProgressView extends View {

    private int length = 200;
    private int width = 50;
    private int valueLength = 100;
    private Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int changeValue = 50;



    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        mBgPaint.setColor(Color.BLACK);
        mValuePaint.setColor(Color.RED);
    }


    public void setChangeValue(int value){
        changeValue = value;
        invalidate();
        Log.w("tagg","value  : "+value);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,length,width,mBgPaint);
        canvas.drawRect(changeValue,0,changeValue+valueLength,width,mValuePaint);
    }
}
