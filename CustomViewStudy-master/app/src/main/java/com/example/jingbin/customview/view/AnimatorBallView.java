package com.example.jingbin.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.example.jingbin.customview.app.Point;
import com.example.jingbin.customview.app.PointEvaluator;

/**
 * Created by dongwei on 2016/12/8.
 */

public class AnimatorBallView  extends View {

    public static final int Radius = 50;
    private Point mPoint;
    private Paint mPaint;

    public AnimatorBallView(Context context) {
        super(context);
    }

    public AnimatorBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas){
        if (mPoint == null){
            mPoint = new Point(Radius, Radius);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas){
        float x = mPoint.getX();
        float y = mPoint.getY();
        canvas.drawCircle(x,y,Radius,mPaint);
    }

    private void startAnimation(){
        Point startPoint = new Point(Radius,Radius);
        Point endPoint = new Point(getWidth() -Radius, getHeight() -Radius);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(5000).setInterpolator(new BounceInterpolator());
        animator.start();
    }
}
