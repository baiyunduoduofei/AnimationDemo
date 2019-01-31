package com.zhou.animationdemo;

import android.animation.TypeEvaluator;

/**
 * @author: zhouyunfei
 * @date: 2019/1/29
 * @desc:
 */
public class WidthTypeEvaluator implements TypeEvaluator <WidthObject>{
    @Override
    public WidthObject evaluate(float fraction, WidthObject startValue, WidthObject endValue) {
        return new WidthObject((int)(startValue.getWidth()+fraction*(endValue.getWidth()-startValue.getWidth())));
    }
}
