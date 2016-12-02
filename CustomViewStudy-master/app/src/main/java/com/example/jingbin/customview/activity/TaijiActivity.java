package com.example.jingbin.customview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.jingbin.customview.view.TaijiView;

/**
 * Created by dongwei on 2016/11/24.
 */

public class TaijiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_taiji);

        setTitle("Taiji");

        final TaijiView taiJi = new TaijiView(this);
        setContentView(taiJi);
        Handler handler = new Handler(){
            private float degrees = 0;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                taiJi.setRotate(degrees += 5);
                this.sendEmptyMessageDelayed(0, 80);
            }
        };
        handler.sendEmptyMessageDelayed(0, 20);
    }
}
