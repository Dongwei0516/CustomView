package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.jingbin.customview.R;

/**
 * Created by dongwei on 2016/12/5.
 */

public class LightningView extends View {

    private float mRadius = 200;
    private float mRadius2 = 250;
    private float mRadius3 = 200;
    private float mCircleWidth = 50;
    private Paint mBottomPaint;
    private Paint mBackgroundPaint;
    private Paint mLinePaint;
    private Paint mFillPaint;
    private Path mPath;
    private Path mPath2;
    private Path mPath3;
    private Path mPath4;
    private float mLevel = 80;
    private Bitmap bitmap;
    private int mWidth;
    private int mHeight;
    private Rect mRect;

    public LightningView(Context context) {
        super(context);
    }

    public LightningView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LightningView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lightning);
        canvas.drawCircle(mRadius, mRadius, mRadius ,mBottomPaint);
//        drawCycle(canvas);
        drawLines(canvas);
        drawLines2(canvas);
        drawLines3(canvas);
//        drawCycle2(canvas);
        canvas.drawBitmap(bitmap, null, mRect, mBottomPaint);
    }

    private void initPaint(){
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

        mRect = new Rect();
        mRect.left = (int) (mRadius - Math.sqrt(2)/2*mRadius);
        mRect.top = (int) (mRadius - Math.sqrt(2)/2*mRadius);
        mRect.right = (int)(mRect.left + Math.sqrt(2)*mRadius);
        mRect.bottom = (int)(mRect.top + Math.sqrt(2)*mRadius);
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
            mPath.lineTo(mRadius,mRadius);
            mPath.setFillType(Path.FillType.WINDING);
            mPath.close();

            mPath2 = new Path();
            mPath2.addArc(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius+mRadius3,mRadius+mRadius3),
                    startPercent,sweepPercent);
            mPath2.lineTo(mRadius,mRadius);
            mPath2.close();

            mPath.op(mPath2, Path.Op.DIFFERENCE);
            canvas.drawPath(mPath,mBackgroundPaint);
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
            canvas.drawPath(mPath,mLinePaint);
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
            mPath.lineTo(mRadius,mRadius);
            mPath.setFillType(Path.FillType.WINDING);
            mPath.close();

            mPath2 = new Path();
            mPath2.addArc(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius+mRadius3,mRadius+mRadius3),
                    startPercent,sweepPercent);
            mPath2.lineTo(mRadius,mRadius);
            mPath2.close();
            mPath.op(mPath2, Path.Op.DIFFERENCE);
            canvas.drawPath(mPath,mFillPaint);
        }
        float startPercent2 = (273 + m*45)%360;
        float sweepPercent2 = n - 3;
        mPath3 = new Path();
        mPath3.addArc(new RectF(mRadius-mRadius2, mRadius-mRadius2, mRadius + mRadius2, mRadius + mRadius2),
                startPercent2, sweepPercent2);
        mPath3.lineTo(mRadius, mRadius);
        mPath3.close();

        mPath4 = new Path();
        mPath4.addArc(new RectF(mRadius - mRadius3, mRadius - mRadius3, mRadius+mRadius3,mRadius+mRadius3),
                startPercent2, sweepPercent2);
        mPath4.lineTo(mRadius, mRadius);
        mPath4.close();
        mPath3.op(mPath4, Path.Op.DIFFERENCE);
        canvas.drawPath(mPath3,mFillPaint);
    }

}
