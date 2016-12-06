package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dongwei on 2016/12/5.
 */

public class CircularChartView extends View{

    private String[] strings = new String[]{"1", "2", "3", "4", "5", "6"};
    private int[] percent = new int[]{10, 25, 18, 41, 2, 5};
    private int[] mColor = new int[]{0xFFF06292, 0xFF9575CD, 0xFFE57373, 0xFF4FC3F7, 0xFFFFF176, 0xFF81C784};
    private float mRadius = 300;
    private float mStrokeWidth = 40;
    private int textSize = 20;
    private Paint cyclePaint;
    private Paint textPaint;
    private Paint labelPaint;
    private int textColor = 0xFF000000;
    private int mWidth;
    private int mHeight;

    public CircularChartView(Context context) {
        super(context);
    }

    public CircularChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularChartView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.translate(mWidth/2 - mRadius/2, mHeight/2 - mRadius /2);
        initPaint();
        drawCycle(canvas);
        drawText(canvas);
    }

    private void initPaint(){
        cyclePaint = new Paint();
        cyclePaint.setAntiAlias(true);
        cyclePaint.setStyle(Paint.Style.STROKE);
        cyclePaint.setStrokeWidth(mStrokeWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(textSize);

        labelPaint = new Paint();
        labelPaint.setAntiAlias(true);
        labelPaint.setStyle(Paint.Style.STROKE);
        labelPaint.setStrokeWidth(2);
    }

    private void drawCycle(Canvas canvas){
        float startPercent = 0;
        float sweepPercent = 0;
        for (int i = 0; i < percent.length; i++ ){
            cyclePaint.setColor(mColor[i]);
            startPercent = sweepPercent + startPercent;
            sweepPercent = percent[i] * 360 / 100;
            canvas.drawArc(new RectF(0, 0,mRadius, mRadius), startPercent, sweepPercent, false, cyclePaint);
        }
    }

    private void drawText(Canvas canvas){
        for (int i = 0; i < percent.length; i++){
            canvas.drawText(strings[i], mRadius + 60, i*40, textPaint);
            labelPaint.setColor(mColor[i]);
            canvas.drawCircle(mRadius + 40, i * 40 -5, 10, labelPaint);
        }
    }
}
