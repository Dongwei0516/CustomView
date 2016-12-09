package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.icu.text.DecimalFormat;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dongwei on 2016/12/9.
 */

public class NumberAnimatorView extends View{

    private Paint mPaint;
    private Rect mRect;
    private double mCount;
    private double mTarget = 12471.14;

    public NumberAnimatorView(Context context) {
        super(context);
    }

    public NumberAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
    }

    public NumberAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas){
        mPaint.setColor(Color.parseColor("#0082D7"));
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(100);

        String str = new DecimalFormat("00.00").format(mCount);
        mPaint.getTextBounds(str, 0, str.length(), mRect);
        canvas.drawText(str,getWidth()/2 - mRect.width()/2, getHeight()/2 - mRect.height()/2,mPaint);
        mHandle.sendEmptyMessage(0);
    }

    Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                double step = mTarget / 400d;
                if (mCount <= mTarget){
                    mCount+=step;
                    if (mCount >= mTarget){
                        mCount = mTarget;
                    }
                    invalidate();
                }
            }
        }
    };
}
