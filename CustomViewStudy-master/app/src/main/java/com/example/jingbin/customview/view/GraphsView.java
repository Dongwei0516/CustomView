package com.example.jingbin.customview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.example.jingbin.customview.app.Point;

import java.util.ArrayList;

/**
 * Created by dongwei on 2016/12/9.
 */

public class GraphsView extends View{

    private static final int CIRCLE_SIZE = 10;
    private static enum Linestyle{Line, Curve}
    private Context mContext;
    private Paint mPaint;
    private Resources res;
    private DisplayMetrics dm;

    private Linestyle mStyle = Linestyle.Curve;
    private int mHeight;
    private int mWidth;
    private int height = 0;
    private int width;
    private boolean isMeasure = true;
    private int maxValue;
    private int averageValue;
    private int marginTop = 20;
    private int marginBottom = 40;

    private Point[] mPoints;
    private ArrayList<Double> yRawData;
    private ArrayList<String> xRawData;
    private ArrayList<Integer> xList = new ArrayList<Integer>();

    public GraphsView(Context context) {
        super(context);
    }

    public GraphsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView(){
        this.res = mContext.getResources();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        if (isMeasure){
            this.mHeight = getHeight();
            this.mWidth = getWidth();
            if (height == 0){
                height = mHeight - marginBottom;
            }
            width =dip2px(30);
            isMeasure = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        mPaint.setColor(Color.WHITE);

    }

    private int dip2px(float dpValue)
    {
        return (int) (dpValue * dm.density + 0.5f);
    }

}
