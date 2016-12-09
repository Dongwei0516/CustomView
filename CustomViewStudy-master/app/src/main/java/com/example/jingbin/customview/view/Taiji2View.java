package com.example.jingbin.customview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by dongwei on 2016/12/7.
 */

public class Taiji2View extends View {

    private Paint WhitePaint;
    private Paint BlackPaint;
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private int mSmallerRadius;
    private int mSmallestRadius;
    private float degrees;
    private boolean isStop = true ;
    private boolean isPause = false;

    public Taiji2View(Context context) {
        super(context);
    }

    public Taiji2View(final Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStop){
                startAnimation();
                Log.d("11", "start");
                }else if (!isPause){
                    pauseAnimation();
                }else if (isPause){
                    resumeAnimation();
                }
            }
        });
    }

    private void initPaints() {
        WhitePaint = new Paint();
        WhitePaint.setAntiAlias(true);
        WhitePaint.setColor(Color.GRAY);

        BlackPaint = new Paint(WhitePaint);
        BlackPaint.setAntiAlias(true);
        BlackPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.translate(mWidth/2, mHeight/2);

        mRadius = 300;
        RectF rectF = new RectF(-mRadius,-mRadius,mRadius,mRadius);
        canvas.drawArc(rectF, 90, 180, true, BlackPaint);
        canvas.drawArc(rectF, -90, 180, true, WhitePaint);

        mSmallerRadius = mRadius/2;
        canvas.drawCircle( 0, -mSmallerRadius, mSmallerRadius , BlackPaint);
        canvas.drawCircle( 0, mSmallerRadius, mSmallerRadius , WhitePaint);

        mSmallestRadius =  mRadius/8;
        canvas.drawCircle( 0, -mSmallerRadius, mSmallestRadius ,WhitePaint);
        canvas.drawCircle( 0, mSmallerRadius, mSmallestRadius ,BlackPaint);

    }

    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"rotationY", 0, 360);

    private void startAnimation(){
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.start();
        isStop = false;
    }

    private void pauseAnimation(){
        objectAnimator.pause();
        isPause = true;
    }

    private void resumeAnimation(){
        objectAnimator.resume();
        isPause = false;
    }

}
