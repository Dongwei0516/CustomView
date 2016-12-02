package com.example.jingbin.customview.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.jingbin.customview.R;
import com.example.jingbin.customview.view.ImageDrawable;

/**
 * Created by dongwei on 2016/12/1.
 */

public class CircleImageView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_imagedrawable);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cover_image);
        ImageView imageView = (ImageView)findViewById(R.id.ImageView2);
        imageView.setImageDrawable(new ImageDrawable(bitmap));
    }
}
