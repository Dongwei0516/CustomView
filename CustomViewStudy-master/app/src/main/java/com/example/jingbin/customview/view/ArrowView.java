package com.example.jingbin.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.example.jingbin.customview.R;

/**
 * Created by dongwei on 2016/12/7.
 */

public class ArrowView extends View {

    private float currentValue = 0;     // 记录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的正切值,计算图片所需旋转的角度
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private int mWidth;
    private int mHeight;

    public ArrowView(Context context) {
        super(context);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.translate(mWidth/2,mHeight/2);
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        init();

        PathMeasure measure = new PathMeasure(path, false);
        currentValue += 0.005;   //当前位置在Path上的比例
        if (currentValue >=1){
            currentValue = 0;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        measure.getPosTan(measure.getLength() * currentValue, pos, tan);  //当前位置坐标 切线角度
        mMatrix.reset();
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);  //旋转角度
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);

        canvas.drawPath(path,paint );
        canvas.drawBitmap(mBitmap, mMatrix, paint);

        invalidate();
    }

    private void init() {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();
    }

}
