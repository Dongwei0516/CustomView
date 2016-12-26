package com.example.jingbin.customview.activity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jingbin.customview.R;

/**
 * Created by dongwei on 2016/12/23.
 */

public class AnimatorActivity extends Activity {

    private TextView mText1, mText2, mText3, mText4, mText5;
    private RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        mLayout = (RelativeLayout)findViewById(R.id.Layout);
        mText1 = (TextView)findViewById(R.id.text1);
        mText2 = (TextView)findViewById(R.id.text2);
        mText3 = (TextView)findViewById(R.id.text3);
        mText4 = (TextView)findViewById(R.id.text4);
        mText5 = (TextView)findViewById(R.id.text5);

//        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.scale);
//        animatorSet.setTarget(mText1);
//        animatorSet.start();
//        mText1.setTextSize();

//        ObjectAnimator animator = ObjectAnimator.ofInt(mText1,"height", 150, 0);
//        animator.setDuration(1000);
//        animator.start();



        ValueAnimator animator = ValueAnimator.ofInt(100, 0);
        animator.setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                mText1.getLayoutParams().height = value.intValue();
                mText1.requestLayout();
            }
        });

        animatorSet.play(animator);
        animatorSet.start();

//        mText1.setScaleY(0.5f);

    }


}
