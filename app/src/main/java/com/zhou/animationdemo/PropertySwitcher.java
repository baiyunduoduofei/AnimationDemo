package com.zhou.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author: zhouyunfei
 * @date: 2019/1/29
 * @desc:
 */
public class PropertySwitcher extends View implements View.OnClickListener {
    private static final long DURATION_TIME = 4000;
    private boolean isOpen = false;//是否打开
    private ValueAnimator mAnimator = null;//动画
    private int mInterval = 2;//间隙2个像素
    private int mWidth;//当前控件宽度
    private int mHeight;//当前控件高度
    private int mInnerStartX;//控件内部椭圆起始X
    private int mInnerEndX;//控件内部椭圆结束X
    private int mInnerStartY;//控件内部椭圆起始Y
    private int mInnerEndY;//控件内部椭圆结束Y
    private int mBgOnOpen;//打开时背景
    private int mBgOnClose;//关闭时背景
    private int mBgOnCurrent;//当前背景
    private SwitcherCallback mCallBack;//结果回调
    private Paint mPaint;//画笔

    public PropertySwitcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBgOnOpen = Color.parseColor("#189AFE");
        mBgOnClose = Color.parseColor("#D9D9DE");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mInnerStartY = mInterval;
        mInnerEndY = mHeight - mInterval;
        if (isOpen) {
            buildOpenStatusParams();
        } else {
            buildCloseStatusParams();
        }
    }

    /***
     * 设置switcher打开，该方法不会有状态回调
     * 也可以通过自定义属性的方式获取（本版未添加）
     * @param isOpen
     */
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
        if (isOpen) {
            buildOpenStatusParams();
        } else {
            buildCloseStatusParams();
        }
        invalidate();
    }


    /***
     * 构建switcher关闭状态参数
     */
    private void buildCloseStatusParams() {
        mInnerStartX = mInnerStartY;
        mInnerEndX = mInnerStartX + (mHeight - 2 * mInterval);
        mBgOnCurrent = mBgOnClose;
    }

    /***
     * 构建switcher打开状态参数
     */
    private void buildOpenStatusParams() {
        mBgOnCurrent = mBgOnOpen;
        mInnerEndX = mWidth - mInterval;
        mInnerStartX = mInnerEndX - (mHeight - mInterval * 2);
    }

    /****
     * 构建switcher打开过程1参数，启动起始X位置为间隔，
     * 结束X位置为宽度的2/3，当前背景为关闭
     */
    private void buildLeftToOpenStatusParams() {
        mInnerStartX = mInnerStartY;
        mInnerEndX = mWidth / 3 * 2;
        mBgOnCurrent = mBgOnClose;
    }

    /****
     * 构建switcher打开过程2参数，启动起始X位置为宽度的1/3，
     * 结束X位置为宽度-间隔，当前背景为打开
     */
    private void buildRightToOpenStatusParams() {
        mInnerStartX = mWidth / 3;
        mInnerEndX = mWidth - mInterval;
        mBgOnCurrent = mBgOnOpen;
    }

    /***
     * 构建switcher关闭过程1参数，启动起始X位置为宽度的1/3，
     * 结束X位置为宽度-间隔，当前北京为打开
     */
    private void buildRightToCloseStatusParams() {
        mInnerStartX = mWidth / 3;
        mInnerEndX = mWidth - mInterval;
        mBgOnCurrent = mBgOnOpen;
    }

    /****
     * 构建switcher关闭过程2参数，启动起始X位置为间隔，
     * 结束X位置为宽度的2/3，当前背景为关闭
     */
    private void buildLeftToCloseStatusParams() {
        mInnerStartX = mInnerStartY;
        mInnerEndX = mWidth / 3 * 2;
        mBgOnCurrent = mBgOnClose;
    }

    /***
     * 设置状态改变回调
     * @param callback
     */
    public void SetCallBack(SwitcherCallback callback) {
        mCallBack = callback;
    }

    @Override
    public void onClick(View v) {
        if (null == mAnimator) {
            mAnimator = ValueAnimator.ofInt(0, 3);
            mAnimator.setDuration(DURATION_TIME);
            mAnimator.setInterpolator(new SwitcherInterpolator());
            //设置监听，动画结束改变按钮的当前状态值
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isOpen = !isOpen;
                    if (null != mCallBack) {
                        mCallBack.IsOpen(isOpen);
                    }
                }
            });
            //根据进度判断处于何种状态，计算对应的绘制参数，0-起始状态，1-移动过程1,2-移动过程2,3-结束状态
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    switch ((int) animation.getAnimatedValue()) {
                        case 0:
                            if (isOpen) {
                                buildOpenStatusParams();
                            } else {
                                buildCloseStatusParams();
                            }
                            break;
                        case 1:
                            if (isOpen) {
                                buildRightToCloseStatusParams();
                            } else {
                                buildLeftToOpenStatusParams();
                            }
                            break;
                        case 2:
                            if (isOpen) {
                                buildLeftToCloseStatusParams();
                            } else {
                                buildRightToOpenStatusParams();
                            }
                            break;
                        case 3:
                            if (isOpen) {
                                buildCloseStatusParams();
                            } else {
                                buildOpenStatusParams();
                            }
                            break;
                    }
                    invalidate();
                }
            });
        } else if (mAnimator.isRunning()) {
            return;
        }
        mAnimator.start();
    }

    public interface SwitcherCallback {
        void IsOpen(boolean aIsOpen);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawSwitcher(canvas);
        super.onDraw(canvas);
    }

    /**
     * 根据当前背景和计算出的坐标信息绘制switcher
     * @param canvas
     */
    private void drawSwitcher(Canvas canvas) {
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        mPaint.setColor(mBgOnCurrent);
        canvas.drawRoundRect(rect, rect.height() / 2, rect.height() / 2, mPaint);//画外部椭圆
        RectF innerRect = new RectF(mInnerStartX, mInnerStartY, mInnerEndX, mInnerEndY);
        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(innerRect, innerRect.height() / 2, innerRect.height() / 2, mPaint);//画内部椭圆
    }


}
