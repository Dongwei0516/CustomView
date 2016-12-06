package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
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
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Path mPath;
    private Path mPath2;
    private Path mPath3;
    private Path mPath4;
    private float mLevel = 80;
    private Bitmap bitmap;
    private int mWidth;
    private int mHeight;
    private Rect mRect;

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
        drawLines(canvas);
        drawLines2(canvas);
        drawLines3(canvas);
    }

    private void initPaint() {
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(mCircleWidth);
        mPaint1.setColor(Color.parseColor("#FFC1C1"));

        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint2.setStrokeWidth(4);
        mPaint2.setColor(Color.parseColor("#FFFFFF"));

        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint3.setStrokeWidth(4);
        mPaint3.setColor(Color.parseColor("#EE6363"));

        mPaint4 = new Paint();
        mPaint4.setAntiAlias(true);
        mPaint4.setColor(Color.parseColor("#EE6363"));
        mPaint4.setStrokeWidth(4);
        mPaint4.setStyle(Paint.Style.STROKE);

    }

    private void drawLines(Canvas canvas){
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
            canvas.drawPath(mPath,mPaint2);
        }
    }

    private void drawLines2(Canvas canvas){
        float startPercent = 3;
        float finishPercent = 0;
        float sweepPercent = 0;
        float startX1;
        float startY1;
        float stopX1;
        float stopY1;
        float startX2;
        float startY2;
        float stopX2;
        float stopY2;
        for (int i = 0; i < 8; i++){
            startPercent = startPercent + sweepPercent + finishPercent;
            finishPercent = 6;
            sweepPercent = 39;
            mPath = new Path();
            startX1 = (float) (mRadius + mRadius2*Math.sin(Math.toRadians(startPercent)));
            startY1 = (float) (mRadius - mRadius2*Math.cos(Math.toRadians(startPercent)));
            stopX1 = (float) (mRadius + mRadius3*Math.sin(Math.toRadians(startPercent)));
            stopY1 = (float) (mRadius - mRadius3*Math.cos(Math.toRadians(startPercent)));

            startX2 = (float) (mRadius + mRadius2*Math.sin(Math.toRadians(startPercent+sweepPercent)));
            startY2 = (float) (mRadius - mRadius2*Math.cos(Math.toRadians(startPercent+sweepPercent)));
            stopX2 = (float) (mRadius + mRadius3*Math.sin(Math.toRadians(startPercent+sweepPercent)));
            stopY2 = (float) (mRadius - mRadius3*Math.cos(Math.toRadians(startPercent+sweepPercent)));

            mPath.addArc(new RectF(mRadius-mRadius2, mRadius-mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                    startPercent,sweepPercent);
            mPath.addArc(new RectF(mRadius-mRadius3, mRadius-mRadius3, mRadius + mRadius3, mRadius + mRadius3),
                    startPercent,sweepPercent);
            mPath.moveTo(stopX1,stopY1);
            mPath.lineTo(startX1,startY1);
            mPath.moveTo(stopX2,stopY2);
            mPath.lineTo(startX2,startY2);
            mPath.close();
            mPath.setFillType(Path.FillType.WINDING);
            canvas.drawPath(mPath,mPaint4);
        }
    }

    private void drawLines3(Canvas canvas){
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
            mPath.addArc(new RectF(mRadius-mRadius2, mRadius-mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                    startPercent,sweepPercent);
            mPath.arcTo(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius+mRadius3,mRadius+mRadius3),
                    startPercent+sweepPercent,-sweepPercent);
            mPath.close();
            canvas.drawPath(mPath,mPaint3);
        }
        float startPercent2 = (273 + m*45)%360;
        float sweepPercent2 = n - 3;
        mPath3 = new Path();
        mPath3.addArc(new RectF(mRadius-mRadius2, mRadius-mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                startPercent2, sweepPercent2);
        mPath3.arcTo(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius+mRadius3,mRadius+mRadius3),
                startPercent2+sweepPercent2, -sweepPercent2);
        mPath3.close();
        canvas.drawPath(mPath3,mPaint3);
    }

}



