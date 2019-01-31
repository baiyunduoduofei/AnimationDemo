package com.zhou.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mAnimationBtn0;
    private Button mAnimationBtn1;
    private Button mAnimationBtn2;
    private Button mAnimationBtn3;
    private Button mAnimationBtn4;
    private PropertySwitcher mPropertySwitcher;
    private ValueAnimator widthAnimation2;
    private ValueAnimator widthAnimation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mAnimationBtn0 = this.findViewById(R.id.property_animation_btn0);
        mAnimationBtn1 = this.findViewById(R.id.property_animation_btn1);
        mAnimationBtn2 = this.findViewById(R.id.property_animation_btn2);
        mAnimationBtn3 = this.findViewById(R.id.property_animation_btn3);
        mAnimationBtn4 = this.findViewById(R.id.property_animation_btn4);
        mPropertySwitcher=this.findViewById(R.id.property_animation_switcher);
        mPropertySwitcher.setOpen(true);
        mAnimationBtn0.setOnClickListener(this);
        mAnimationBtn1.setOnClickListener(this);
        mAnimationBtn2.setOnClickListener(this);
        mAnimationBtn3.setOnClickListener(this);
        mAnimationBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.property_animation_btn0:
                ObjectAnimator translationXAnimation = ObjectAnimator.ofFloat(mAnimationBtn0, "translationX", 0, 500);
                translationXAnimation.setDuration(2000);
                translationXAnimation.setRepeatCount(1);
                translationXAnimation.setRepeatMode(ValueAnimator.REVERSE);
                translationXAnimation.start();
                //xml 形式
//                ObjectAnimator objectAnimator= (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.propertyanimation);
//                objectAnimator.setTarget(mAnimationBtn0);
//                objectAnimator.start();
                break;
            case R.id.property_animation_btn1:
                ObjectAnimator widthAnimation = ObjectAnimator.ofInt(new WidthWrapper(mAnimationBtn1), "Width", 0, 600);
                widthAnimation.setDuration(2000);
                widthAnimation.start();
                break;
            case R.id.property_animation_btn2:
                if (null != widthAnimation2 && widthAnimation2.isRunning()) {
                    return;
                }
                widthAnimation2 = ValueAnimator.ofInt(0, 600);
                widthAnimation2.setDuration(2000);
                widthAnimation2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mAnimationBtn2.getLayoutParams().width = (int) animation.getAnimatedValue();
                        mAnimationBtn2.requestLayout();

                    }
                });
                widthAnimation2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mAnimationBtn2.getLayoutParams().width = 0;
                        mAnimationBtn2.requestLayout();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAnimationBtn2.getLayoutParams().width = 600;
                        mAnimationBtn2.requestLayout();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                widthAnimation2.start();
                break;
            case R.id.property_animation_btn3:
                if (null != widthAnimation3 && widthAnimation3.isRunning()) {
                    return;
                }
                widthAnimation3 = ValueAnimator.ofObject(new WidthTypeEvaluator(),new WidthObject(0), new WidthObject(600));
                widthAnimation3.setDuration(2000);
                widthAnimation3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mAnimationBtn3.getLayoutParams().width = ((WidthObject)animation.getAnimatedValue()).getWidth();
                        mAnimationBtn3.requestLayout();

                    }
                });
                widthAnimation3.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mAnimationBtn3.getLayoutParams().width = 0;
                        mAnimationBtn3.requestLayout();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAnimationBtn3.getLayoutParams().width = 600;
                        mAnimationBtn3.requestLayout();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                widthAnimation3.start();
                break;

            case R.id.property_animation_btn4:
                ObjectAnimator animation1 = ObjectAnimator.ofFloat(mAnimationBtn4, "translationX", 0, 500);
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(mAnimationBtn4, "alpha", 0, 1);
                ObjectAnimator animation3 = ObjectAnimator.ofFloat(mAnimationBtn4, "scaleX", 2);
                ObjectAnimator animation4 = ObjectAnimator.ofFloat(mAnimationBtn4,"rotationX",0,270,50);
                AnimatorSet  animatorSet=new AnimatorSet ();
                animatorSet.play(animation1).with(animation2).after(animation3).before(animation4);
                animatorSet.setDuration(2000);
                animatorSet.start();
                break;
        }

    }


}
