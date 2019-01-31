package com.zhou.animationdemo;

import android.view.View;

/**
 * @author: zhouyunfei
 * @date: 2019/1/25
 * @desc:
 */
public class WidthWrapper {
    private View mTargetView;

    WidthWrapper(View view) {
        mTargetView = view;
    }

    public int getWidth(){
        return mTargetView.getLayoutParams().width;
    }
    public void setWidth(int width) {
        mTargetView.getLayoutParams().width = width;
        mTargetView.requestLayout();
    }

}
