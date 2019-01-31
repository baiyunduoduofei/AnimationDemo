package com.zhou.animationdemo;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity {
    private ImageView mXmlImg;
    private ImageView mJavaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        mXmlImg = this.findViewById(R.id.frame_xml_img);
        mJavaImg = this.findViewById(R.id.frame_java_img);
        final AnimationDrawable xmlDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.frameanimation);
        mXmlImg.setImageDrawable(xmlDrawable);
        final AnimationDrawable javaDrawable = new AnimationDrawable();
        javaDrawable.setOneShot(false);//是否执行1次
        int resId;
        for (int i = 1; i < 12; i++) {
            resId = getResources().getIdentifier("hj" + i, "mipmap", getPackageName());
            javaDrawable.addFrame(getResources().getDrawable(resId), 200);
        }
        mJavaImg.setImageDrawable(javaDrawable);
        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                xmlDrawable.start();
                javaDrawable.start();
            }
        });
    }
}
