package com.example.jingbin.customview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jingbin.customview.R;
import com.example.jingbin.customview.view.CustomProgressBar;

public class CustomProgressBarActivity extends AppCompatActivity {

    private CustomProgressBar customProgressBar01;
    private CustomProgressBar customProgressBar02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_bar);
        setTitle("自定义View (三)");
        initView();
    }

    private void initView() {
        customProgressBar01 = (CustomProgressBar) findViewById(R.id.custom_progress_bar_01);
        customProgressBar02 = (CustomProgressBar) findViewById(R.id.custom_progress_bar_02);

    }


    @Override
    protected void onStop() {
        super.onStop();
        customProgressBar01.setContinue(false);
        customProgressBar02.setContinue(false);
        customProgressBar01 = null;
        customProgressBar02 = null;
    }
}
