package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by dongwei on 2016/12/1.
 */

public class ZoomImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener,
        View.OnTouchListener,ViewTreeObserver.OnGlobalLayoutListener{

    private static final String TAG = ZoomImageView.class.getSimpleName();
    public static final float SCALE_MAX = 4.0f;  //最大缩放比例
    public static final float SCALE_MIN = 0.5f; //最小值
    private float initScale = 1.0f;   //初始缩放比例
    private final float[] matrixValues = new float[9];  //三维矩阵元素
    private boolean once = true;
    private ScaleGestureDetector mScaleGestureDetector = null;  //缩放手势检测
    private final Matrix mScaleMatrix = new Matrix();

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context , this);
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable() == null){
            return true;
        }
        if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > initScale && scaleFactor < 1.0f)){  //缩放范围
            if (scaleFactor * scale < SCALE_MIN){   //最小值
                scaleFactor = SCALE_MIN / scale;
            }
            if (scaleFactor * scale > SCALE_MAX){   //最大这
                scaleFactor = SCALE_MAX / scale;
            }
//            mScaleMatrix.postScale(scaleFactor, scaleFactor , getWidth() / 2, getHeight() / 2 );  //设置缩放比例
            mScaleMatrix.postScale(scaleFactor, scaleFactor , detector.getFocusX() , detector.getFocusY());
            checkBorderAndCenter();
            setImageMatrix(mScaleMatrix);
        }
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mScaleGestureDetector.onTouchEvent(event);
    }

    public final float getScale()
    {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    protected void onAttachedToWindow(){
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }


    @Override
    protected void onDetachedFromWindow(){
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {

        if (once){
            Drawable d = getDrawable();
            if (d == null){
                return;
            }
            int width = getWidth();
            int height = getHeight();
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale = 1.0f;
            if (dw > width && dh <= height){
                scale = width *1.0f /dw;
            }
            if (dh > height && dw<= width){
                scale = height *1.0f /dh;
            }
            if (dw > width && dh > height){
                scale = Math.min(dw * 1.0f / width, dh * 1.0f /height);
            }
            initScale = scale;
            mScaleMatrix.postTranslate((width - dw)/2 , (height - dh)/2 );
            mScaleMatrix.postScale(scale, scale, getWidth()/2, getHeight()/2);
            setImageMatrix(mScaleMatrix);
            once = false;
        }

    }

    private void checkBorderAndCenter(){
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;
        int width = getWidth();
        int height = getHeight();

        if (rectF.width() >= width){
            if (rectF.left > 0){
                deltaX = -rectF.left;
            }
            if (rectF.right < width){
                deltaX = width - rectF.right;
            }
        }
        if (rectF.height() >= height){
            if (rectF.top > 0){
                deltaY = -rectF.top;
            }
            if (rectF.bottom < height){
                deltaY = height - rectF.bottom;
            }
        }

        if (rectF.width() < width){
            deltaX = width * 0.5f - rectF.right + 0.5f * rectF.width();
        }
        if (rectF.height() < height){
            deltaY = height * 0.5f - rectF.bottom + 0.5f * rectF.height();
        }
        mScaleMatrix.postTranslate(deltaX,deltaY);
    }

    private RectF getMatrixRectF()   //根据图片Matrix获得图片范围
    {
        Matrix matrix = mScaleMatrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d)
        {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }
}
