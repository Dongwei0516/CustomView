package com.example.jingbin.customview.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dongwei on 2016/12/1.
 */

public class ImageDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF rectF;
    private int mWidth;

    public ImageDrawable(Bitmap bitmap){
        mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
        mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
    }

    @Override
    public void setBounds(int left , int top , int right , int bottom){
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top ,right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mWidth/2, mWidth/2, mWidth/2, mPaint);
    }

    @Override
    public int getIntrinsicWidth(){
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight(){
        return mWidth;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {     // 不透明度
        return PixelFormat.TRANSLUCENT;  //使窗口支持透明度
    }
}
