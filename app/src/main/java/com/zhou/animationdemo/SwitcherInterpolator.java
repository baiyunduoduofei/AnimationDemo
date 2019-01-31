package com.zhou.animationdemo;

import android.view.animation.Interpolator;

/**
 * @author: zhouyunfei
 * @date: 2019/1/30
 * @desc:
 */
public class SwitcherInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        if(input<0.1){
            return 0;
        }else if(input<0.5){
            return 0.34f;
        }else if(input<0.9){
            return 0.67f;
        }else{
            return 1.0f;
        }
    }
}
