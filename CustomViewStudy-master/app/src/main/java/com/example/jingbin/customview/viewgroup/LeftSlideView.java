package com.example.jingbin.customview.viewgroup;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dongwei on 2016/11/29.
 */

public class LeftSlideView extends ViewGroup{

    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private int mMinDrawerMargin;
    private View mLeftMenuView;
    private View mContentView;
    private ViewDragHelper mHelper;
    private float mLeftMenuOnScreen;

    public LeftSlideView(Context context) {
        super(context);
    }

    public LeftSlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final float density = getResources().getDisplayMetrics().density;
        final float minVel = MIN_FLING_VELOCITY * density;
        mMinDrawerMargin = (int)(MIN_DRAWER_MARGIN * density + 0.5f);

        mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return false;
            }
        });
    }

    public LeftSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
