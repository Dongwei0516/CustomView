package com.example.jingbin.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jingbin.customview.activity.AnimatorBallActivity;
import com.example.jingbin.customview.activity.ArrowActivity;
import com.example.jingbin.customview.activity.ChartsActivity;
import com.example.jingbin.customview.activity.CircleActivity;
import com.example.jingbin.customview.activity.CircleImageView;
import com.example.jingbin.customview.activity.CircularChartActivity;
import com.example.jingbin.customview.activity.CustomImageViewActivity;
import com.example.jingbin.customview.activity.CustomImgContainerActivity;
import com.example.jingbin.customview.activity.CustomProgressBarActivity;
import com.example.jingbin.customview.activity.CustomVolumControlBarActivity;
import com.example.jingbin.customview.activity.GraphsActivity;
import com.example.jingbin.customview.activity.LoadingActivity;
import com.example.jingbin.customview.activity.NineImgActivity;
import com.example.jingbin.customview.activity.NumberAnimatorActivity;
import com.example.jingbin.customview.activity.PathActivity;
import com.example.jingbin.customview.activity.BezierViewActivity;
import com.example.jingbin.customview.activity.Taiji2Activity;
import com.example.jingbin.customview.activity.TaijiActivity;
import com.example.jingbin.customview.activity.VDHDeepLayoutActivity;
import com.example.jingbin.customview.activity.ZoomImageActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_custom_view_02).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_03).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_04).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_05).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_07).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_08).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_09).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_10).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_11).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_12).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_13).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_14).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_15).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_16).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_17).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_18).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_19).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_20).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_21).setOnClickListener(this);
        findViewById(R.id.bt_custom_view_22).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_custom_view_02:// 图文搭配
                startActivity(new Intent(v.getContext(), CustomImageViewActivity.class));
                break;
            case R.id.bt_custom_view_03:// 自定义圆形进度条
                startActivity(new Intent(v.getContext(), CustomProgressBarActivity.class));
                break;
            case R.id.bt_custom_view_04:// 音量控件
                startActivity(new Intent(v.getContext(), CustomVolumControlBarActivity.class));
                break;
            case R.id.bt_custom_view_05:// 左上右下的图片显示(ViewGroup)
                startActivity(new Intent(v.getContext(), CustomImgContainerActivity.class));
                break;
            case R.id.bt_custom_view_07:// ViewDragHelper
//                startActivity(new Intent(v.getContext(), VDHLayoutActivity.class));
                startActivity(new Intent(v.getContext(), VDHDeepLayoutActivity.class));
                break;
            case R.id.bt_custom_view_08:// ☯️
                startActivity(new Intent(v.getContext(), TaijiActivity.class));
                break;
            case R.id.bt_custom_view_09:// 多边形Path
                startActivity(new Intent(v.getContext(), PathActivity.class));
                break;
            case R.id.bt_custom_view_10:
                startActivity(new Intent(v.getContext(), NineImgActivity.class));
                break;
            case R.id.bt_custom_view_11:
                startActivity(new Intent(v.getContext(), CircleImageView.class));
                break;
            case R.id.bt_custom_view_12:
                startActivity(new Intent(v.getContext(), ZoomImageActivity.class));
                break;
            case R.id.bt_custom_view_13:
                startActivity(new Intent(v.getContext(), CircularChartActivity.class));
                break;
            case R.id.bt_custom_view_14:
                startActivity(new Intent(v.getContext(), CircleActivity.class));
                break;
            case R.id.bt_custom_view_15:
                startActivity(new Intent(v.getContext(), ChartsActivity.class));
                break;
            case R.id.bt_custom_view_16:
                startActivity(new Intent(v.getContext(), ArrowActivity.class));
                break;
            case R.id.bt_custom_view_17:
                startActivity(new Intent(v.getContext(), LoadingActivity.class));
                break;
            case R.id.bt_custom_view_18:
                startActivity(new Intent(v.getContext(), Taiji2Activity.class));
                break;
            case R.id.bt_custom_view_19:
                startActivity(new Intent(v.getContext(), AnimatorBallActivity.class));
                break;
            case R.id.bt_custom_view_20:
                startActivity(new Intent(v.getContext(), BezierViewActivity.class));
                break;
            case R.id.bt_custom_view_21:
                startActivity(new Intent(v.getContext(), NumberAnimatorActivity.class));
                break;
            case R.id.bt_custom_view_22:
                startActivity(new Intent(v.getContext(), GraphsActivity.class));
            default:
                break;
        }
    }
}
