package com.example.jingbin.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by dongwei on 2016/11/28.
 */

public class NineImgView extends ViewGroup {

    public final static int LINE_MAX_COUNT = 3;
    public int mLineMaxCount = 3;
    private int mPicSpace = 5;  //图片间距
    private int mChildEdgeSize; //子view边长度
    private int mChildVisibleCount;  //子view可见个数
    private int mMaxChildCount = 9;
    private int mWidth;
    private int mHeight;

    public NineImgView(Context context) {
        super(context);

    }

    public NineImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public NineImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getMultyImgLines(int imgSize){
        if (imgSize == 0){
            return 1;
        }
        return (imgSize + mLineMaxCount -1)/mLineMaxCount;
    }

    protected void measureImgWidth(int widthMeasureSpec) {
        if (mChildEdgeSize == 0) {
            int measureSize = MeasureSpec.getSize(widthMeasureSpec);
            mChildEdgeSize = (measureSize - (LINE_MAX_COUNT - 1) * mPicSpace
                    - getPaddingLeft() - getPaddingRight()) / 3;
        }
    }

    public void setMaxChildCount(int len){
        removeAllViews();
        mMaxChildCount = len;
        for (int i =0; i<len; i++){
            ImageView imageView = new ImageView(getContext());
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setTag(i);
            this.addView(imageView, params);
        }
    }

    private int getVisibleChildCount() {
        int childCount = getChildCount();
        int count = 0;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).getVisibility() != View.GONE) {
                count++;
            }
        }
        return count;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        if (getChildCount() == 0){
            setMaxChildCount(mMaxChildCount);
        }
        measureImgWidth(widthMeasureSpec);
        mChildVisibleCount = getVisibleChildCount();
        int lines = getMultyImgLines(mChildVisibleCount);
        int viewHeight = ((lines -1)*mPicSpace + lines*mChildEdgeSize) + getPaddingTop() + getPaddingBottom();
        if (mChildVisibleCount == 1){
            viewHeight = mHeight == 0 ? viewHeight: mHeight;
        }
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, viewHeight);
        int heightSize = mChildEdgeSize;
        widthSize = heightSize;
        if(mChildVisibleCount == 1 && mWidth != 0){
            widthSize = mWidth;
            heightSize = mHeight;
        }
        measureChildren(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lpadding = getPaddingLeft();
        int tpadding = getPaddingTop();
        int left = lpadding, top = tpadding;
        int childCount = getChildCount();
        int visibleChildCount = mChildVisibleCount;
        int breakLineC = 0;
        if (visibleChildCount ==4){
            breakLineC =2;
        }else {
            breakLineC = mLineMaxCount;
        }
        for (int i=0; i<childCount; i++){
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE){
                continue;
            }
            if (visibleChildCount == 1){
                if (mLineMaxCount == 1){
                left = (getMeasuredWidth()- mWidth)/2;
                }
            if (mWidth == 0 || mHeight == 0){
                child.layout(left , top ,left+mChildEdgeSize, top+mChildEdgeSize);
            }else {
                child.layout(left , top, left+mWidth, top + mHeight);
            }
            }else{
                child.layout(left , top, left+mChildEdgeSize,top+mChildEdgeSize);
                left += (mPicSpace + mChildEdgeSize);
                if((i+1)%breakLineC == 0){
                    top += mChildEdgeSize + mPicSpace;
                    left = lpadding;
                }

        }

        }
    }
}
