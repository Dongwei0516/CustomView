package com.example.jingbin.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class CustomImgContainer extends ViewGroup {

    public CustomImgContainer(Context context) {
        super(context);
    }

    public CustomImgContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //计算ChildView的宽度和高度，根据ChildView的计算结果，设置自己的宽高

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //获得ViewGroup父类默认的宽高和计算模式
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childview的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // viewgroup的宽高
        int width = 0;
        int height = 0;

        // childview的宽高
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        int childCount = getChildCount();

        //用于计算左边两个childview的高度和
        int lHeight = 0;
        //用于计算右边两个childview的高度和,最终取最大值
        int rHeight = 0;
        //用于计算上边两个childview的宽度和
        int tWidth = 0;
        //用于计算下面两个childview的宽度和,最终取最大值
        int bWidth = 0;


         // 根据childview计算出的宽和高,以及设置的margin计算容器的宽和高

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();// childview的宽
            cHeight = childView.getMeasuredHeight();// childview的高
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            if (i == 0 || i == 1) {// 上面的宽度
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if (i == 2 || i == 3) {// 下面的宽度
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if (i == 0 || i == 2) {//左边的高度
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
            if (i == 1 || i == 3) {//右边的高度
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
        }
        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width, modeHight == MeasureSpec.EXACTLY ? sizeHeight : height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childWidth = 0;
        int childHeight = 0;
        MarginLayoutParams childParams = null;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;
            switch (i) {
                case 0:
                    cl = childParams.leftMargin;
                    ct = childParams.topMargin;
                    break;
                case 1:
                    cl = getWidth() - childWidth - childParams.rightMargin;
                    ct = childParams.topMargin;
                    break;
                case 2:
                    cl = childParams.leftMargin;
                    ct = getHeight() - childHeight - childParams.bottomMargin;
                    break;
                case 3:
                    cl = getWidth() - childWidth - childParams.rightMargin;
                    ct = getHeight() - childHeight - childParams.bottomMargin;
                    break;
            }
            cr = childWidth + cl;
            cb = childHeight + ct;
            childView.layout(cl, ct, cr, cb);
        }

    }
}
