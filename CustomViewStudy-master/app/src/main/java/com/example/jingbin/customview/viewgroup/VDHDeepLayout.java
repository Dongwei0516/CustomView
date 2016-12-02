package com.example.jingbin.customview.viewgroup;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class VDHDeepLayout extends LinearLayout {

    private ViewDragHelper mViewDragHelper;

    //  第一个View,可自由拖动的 view

    private View mDragView;

//      第二个View,拖动松手后可自动返回原来的位置 View
    private View mAutoBackView;
    //  第三个View，只能从边界拖动的View
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();

    public VDHDeepLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                // mEdgeTrackerView禁止直接拖动
                return child == mAutoBackView || child == mDragView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - mDragView.getWidth() - leftBound;
                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int topBound = getPaddingTop();
                int bottomBound = getHeight() - mDragView.getHeight() - topBound;
                int newTop = Math.min(Math.max(top, topBound), bottomBound);
                return newTop;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                super.onViewReleased(releasedChild, xvel, yvel);
                //手指释放时回调
                if (releasedChild == mAutoBackView) {
                    mViewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate();
                }
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//                super.onEdgeDragStarted(edgeFlags, pointerId);
                //边界拖动时回调
                mViewDragHelper.captureChildView(mEdgeTrackerView, pointerId);
            }

            // childview横向纵向移动范围
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }
        });
        // 边界检测
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }
}
