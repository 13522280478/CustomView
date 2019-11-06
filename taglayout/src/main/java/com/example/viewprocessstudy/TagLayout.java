package com.example.viewprocessstudy;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YueShuai
 * @date 2019-11-05
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class TagLayout extends ViewGroup {

    List<Rect> childrenBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthUsed = 0;
        int heightUsed = 0;
        int lineMaxHeight = 0;
        int lineWidthUsed = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED &&
                    lineWidthUsed + child.getMeasuredWidth() > widthSize) {
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect childBounds;
            if (childrenBounds.size() <= i) {
                childBounds = new Rect();
                childrenBounds.add(childBounds);
            } else {
                childBounds = childrenBounds.get(i);
            }
            childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(lineWidthUsed, widthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }

        int measureWidth = widthUsed;
        heightUsed += lineMaxHeight;
        int measureHeight = heightUsed;
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds.get(i);
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}

//            if (i % 2 == 0) {
//                child.layout(0, 0, (r - l) / 2, (b - t) / 2);
//            } else {
//                child.layout((r - l) / 2, (b - t) / 2, r - l, b - t);
//            }

//layoutParams.width;
//        layoutParams.height;
//        child.measure(childWithSpec, childHeightSpec);
//        Rect childBounds = childrenBouns.get(i);
//        childrenBouns.set();

//    int childWidthSpec;
//    int childHeightSpecl;
//    LayoutParams layoutParams = child.getLayoutParams();
//            switch (layoutParams.width) {
//                    case LayoutParams.MATCH_PARENT:
//                    switch (widthMode) {
//                    case MeasureSpec.EXACTLY:
//                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize - widthUsed, MeasureSpec.EXACTLY);
//                    break;
//                    case MeasureSpec.AT_MOST:
//                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize - widthUsed, MeasureSpec.EXACTLY);
//                    break;
//                    case MeasureSpec.UNSPECIFIED:
//                    childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
//                    break;
//                    }
//                    break;
//                    }
