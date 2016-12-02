package com.example.jingbin.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.jingbin.customview.R;


public class CustomProgressBar extends View {

    // 第一种颜色
    private int mFirstColor;
    // 第二种颜色
    private int mSecondColor;
    // 圆环宽度
    private int mCircleWidth;
    // 速度
    private int mSpeed;
    // 当前进度
    private int mProgress;
    // 是否开始下一步
    private boolean isNext;
    // 画笔
    private Paint mPaint;
    // 用来开关线程
    private boolean isContinue;

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int arr = typedArray.getIndex(i);
            switch (arr) {
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = typedArray.getColor(arr, Color.BLUE);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = typedArray.getColor(arr, Color.BLUE);
                    break;
                case R.styleable.CustomProgressBar_circleWidth://圆环宽度
                    mCircleWidth = typedArray.getDimensionPixelSize(arr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));  //获取手机屏幕参数
                    break;
                case R.styleable.CustomProgressBar_speed://旋转速度
                    mSpeed = typedArray.getInt(arr, 20);
                    break;
            }
        }

        typedArray.recycle();
        mPaint = new Paint();
        isContinue = true;
        // 绘图线程
        new Thread() {
            public void run() {
                while (isContinue) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        isNext = !isNext;
                    }
                    postInvalidate();

                    try {
                        Thread.sleep(100 / mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int center = width / 2;// 获取圆心的x坐标         getMeasuredWidth()
        int radius = center - mCircleWidth / 2;// b半径
        mPaint.setStrokeWidth(mCircleWidth);//设置圆环的宽度
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        // 用于定义圆弧的形状和大小的界限
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);

        if (!isNext) {
            mPaint.setColor(mFirstColor);// 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint);// 画圆环
            mPaint.setColor(mSecondColor);// 设置圆环的颜色
            canvas.drawArc(rectF, -90, mProgress, false, mPaint);// 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(rectF, -90, mProgress, false, mPaint);
        }
    }

    // 用来关闭线程
    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }

    private int width;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {//默认宽度
            width = (int) getContext().getResources().getDimension(R.dimen.width);
        }
        setMeasuredDimension(width, width);
    }
}
