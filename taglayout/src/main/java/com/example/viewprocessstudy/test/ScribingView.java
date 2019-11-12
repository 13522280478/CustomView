package com.example.viewprocessstudy.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author YueShuai
 * @date 2019-11-11
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ScribingView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //    Path path = new Path();
    SparseArray<Path> paths = new SparseArray<>();

    public ScribingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Path path = new Path();
                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                path.moveTo(event.getX(pointerId), event.getY(pointerId));
                paths.append(pointerId, path);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pointerId1 = event.getPointerId(i);
                    Path path1 = paths.get(pointerId1);
                    path1.lineTo(event.getX(i), event.getY(i));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                int actionIndex1 = event.getActionIndex();
                int pointerId1 = event.getPointerId(actionIndex1);
                paths.remove(pointerId1);
                invalidate();
                break;
        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.valueAt(i);
            canvas.drawPath(path, paint);
        }

    }
}
