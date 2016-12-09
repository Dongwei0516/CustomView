package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dongwei on 2016/12/6.
 */

public class ChartsView extends View{

    private float mRadius = 200;
    private float mRadius2 = 250;
    private float mRadius3 = 200;
    private float mCircleWidth = 50;
    private Paint mBottomPaint;
    private Paint mBackgroundPaint;
    private Paint mFillPaint;
    private Paint mLinePaint;
    private Path mPath;
    private Path mPath2;
    private float mLevel = 80;
    private int mWidth;
    private int mHeight;

    public ChartsView(Context context) {
        super(context);
    }

    public ChartsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.translate( mWidth/2 - mRadius, mHeight/2 - mRadius);
        initPaint();
        canvas.drawCircle(mRadius, mRadius, mRadius ,mBottomPaint);
        drawBackground(canvas);
        drawFill(canvas);
    }

    private void initPaint() {
        mBottomPaint = new Paint();
        mBottomPaint.setAntiAlias(true);
        mBottomPaint.setStyle(Paint.Style.STROKE);
        mBottomPaint.setStrokeWidth(mCircleWidth);
        mBottomPaint.setColor(Color.parseColor("#FFC1C1"));

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBackgroundPaint.setStrokeWidth(4);
        mBackgroundPaint.setColor(Color.parseColor("#FFFFFF"));

        mFillPaint = new Paint();
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mFillPaint.setStrokeWidth(4);
        mFillPaint.setColor(Color.parseColor("#EE6363"));

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.parseColor("#EE6363"));
        mLinePaint.setStrokeWidth(4);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    private void drawBackground(Canvas canvas){
        float startPercent = 3;
        float finishPercent = 0;
        float sweepPercent = 0;
        for (int i = 0; i < 8; i++){
            startPercent = startPercent + sweepPercent + finishPercent;
            finishPercent = 6;
            sweepPercent = 39;
            mPath = new Path();
            mPath.addArc(new RectF(mRadius-mRadius2, mRadius-mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                    startPercent,sweepPercent);
            mPath.arcTo(new RectF(mRadius- mRadius3 , mRadius - mRadius3, mRadius + mRadius3,mRadius+mRadius3),
                    startPercent+sweepPercent, -sweepPercent);
            mPath.close();
            canvas.drawPath(mPath,mBackgroundPaint);
            canvas.drawPath(mPath,mLinePaint);
        }
    }

    private void drawFill(Canvas canvas){
        float startPercent = 273;
        float finishPercent = 0;
        float sweepPercent = 0;
        int m = (int)(mLevel*360/100/45);
        int n = (int)(mLevel*360/100%90);
        for (int i = 0; i < m; i++){
            startPercent = startPercent + sweepPercent + finishPercent;
            finishPercent = 6;
            sweepPercent = 39;
            mPath = new Path();
            mPath.addArc(new RectF(mRadius - mRadius2, mRadius - mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                    startPercent,sweepPercent);
            mPath.arcTo(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius + mRadius3, mRadius + mRadius3),
                    startPercent+sweepPercent,-sweepPercent);
            mPath.close();
            canvas.drawPath(mPath,mFillPaint);
        }
        float startPercent2 = (273 + m*45) %360;
        float sweepPercent2 = n - 3;
        mPath2 = new Path();
        mPath2.addArc(new RectF(mRadius - mRadius2, mRadius - mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                startPercent2, sweepPercent2);
        mPath2.arcTo(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius + mRadius3, mRadius + mRadius3),
                startPercent2+sweepPercent2, -sweepPercent2);
        mPath2.close();
        canvas.drawPath(mPath2,mFillPaint);
    }

}
