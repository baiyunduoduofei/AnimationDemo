package com.zhou.animationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ViewAnimationActivity extends AppCompatActivity {
    private TextView mViewAnimationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        mViewAnimationTv = this.findViewById(R.id.view_animation_tv);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.viewanimation);
        mViewAnimationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewAnimationTv.startAnimation(animation);
            }
        });
    }
}
