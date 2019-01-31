package com.zhou.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mViewBtn;
    private Button mFrameBtn;
    private Button mPropertyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewBtn = findViewById(R.id.view_animation_btn);
        mFrameBtn = findViewById(R.id.frame_animation_btn);
        mPropertyBtn = findViewById(R.id.property_animation_btn);
        mViewBtn.setOnClickListener(this);
        mFrameBtn.setOnClickListener(this);
        mPropertyBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_animation_btn:
                startActivityByClass(MainActivity.this, ViewAnimationActivity.class);
                break;
            case R.id.frame_animation_btn:
                startActivityByClass(MainActivity.this, FrameAnimationActivity.class);
                break;
            case R.id.property_animation_btn:
                startActivityByClass(MainActivity.this, PropertyAnimationActivity.class);
                //ValueAnimator
//                        TimeAnimator
//                AnimatorSet
//                ObjectAnimator
                break;
        }
    }

    private void startActivityByClass(Context context, Class viewAnimationActivityClass) {
        Intent intent = new Intent(context, viewAnimationActivityClass);
        context.startActivity(intent);
    }
}
