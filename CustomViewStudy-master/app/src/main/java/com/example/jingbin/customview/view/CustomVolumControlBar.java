package com.example.jingbin.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.jingbin.customview.R;


public class CustomVolumControlBar extends View {

    // 第一个圆的颜色
    private int mFirstColor;
    // 第二个圆的颜色
    private int mSecondColor;
    // 圆弧的宽度
    private int mCircleWidth;
    // 个数
    private int mDotCount;
    // 每个块之间的间隙
    private int mSplitSize;
    // 中间的图片
    private Bitmap mBg;

    // 当前进度
    private int mCurrentCount = 1;

    // 画笔
    private Paint mPaint;

    private Rect mRect;

    public CustomVolumControlBar(Context context) {
        this(context, null);
    }

    public CustomVolumControlBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVolumControlBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomVolumControlBar, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int arr = typedArray.getIndex(i);
            switch (arr) {
                case R.styleable.CustomVolumControlBar_vFirstColor:
                    mFirstColor = typedArray.getColor(arr, Color.BLACK);
                    break;
                case R.styleable.CustomVolumControlBar_vSecondColor:
                    mSecondColor = typedArray.getColor(arr, Color.WHITE);
                    break;
                case R.styleable.CustomVolumControlBar_vCircleWidth:
                    mCircleWidth = typedArray.getDimensionPixelSize(arr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomVolumControlBar_vDotCount:
                    mDotCount = typedArray.getInt(arr, 20);
                    break;
                case R.styleable.CustomVolumControlBar_vSplitSize:
                    mSplitSize = typedArray.getInt(arr, 20);
                    break;
                case R.styleable.CustomVolumControlBar_vBg:
                    mBg = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(arr, 0));
                    break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        mRect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//定义线段断点形状为圆头
        mPaint.setStyle(Paint.Style.STROKE);// 设置空心
        int center = getWidth() / 2;// 获取圆心x坐标
        int radius = center - mCircleWidth / 2;// 半径

        drawOval(canvas, center, radius); //绘制外围圆形

        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径

        mRect.left = (int) (relRadius - Math.sqrt(2) / 2 * relRadius) + mCircleWidth;
        mRect.top = (int) (relRadius - Math.sqrt(2) / 2 * relRadius) + mCircleWidth;

        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);

        if (mBg.getWidth() < Math.sqrt(2) * relRadius) {  //图片设置在圆形中间
            mRect.left = mCircleWidth + (relRadius - mBg.getWidth() / 2);
            mRect.top = mCircleWidth + (relRadius - mBg.getWidth() / 2);
            mRect.right = mCircleWidth + (relRadius + mBg.getWidth() / 2);
            mRect.bottom = mCircleWidth + (relRadius + mBg.getWidth() / 2);
        }
        canvas.drawBitmap(mBg, null, mRect, mPaint);

    }

    private void drawOval(Canvas canvas, int center, int radius) { //根据圆形设置个数

        float itemSize = (360 * 1.0f - mDotCount * mSplitSize) / mDotCount;
        // 用于定义的圆的形状和大小的界限
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        mPaint.setColor(mFirstColor);
        for (int i = 0; i < mDotCount; i++) {
            // 根据进度画圆弧
            canvas.drawArc(oval, (i * (itemSize + mSplitSize)), itemSize, false, mPaint);
        }

        mPaint.setColor(mSecondColor);
        for (int i = 0; i < mCurrentCount; i++) {
            // canvas.drawArc(rectF, -90, mProgress, false, mPaint);// 根据进度画圆弧
            // 根据进度画圆弧
            canvas.drawArc(oval, i * (itemSize + mSplitSize), itemSize, false, mPaint);
        }
    }

    public void up() {   // 触摸监听
        if (mCurrentCount < mDotCount) {
            mCurrentCount++;
        }
        postInvalidate();
    }

    public void down() {
        if (mCurrentCount > 0) {
            mCurrentCount--;
        }
        postInvalidate();
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                xUp = (int) event.getY();
                if (xDown < xUp) {
                    down();
                } else {
                    up();
                }
                break;
        }
        return true;
    }
}


