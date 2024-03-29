package com.haozhiyan.zhijian.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by WangZhenKai on 2019/4/23.
 * Describe:
 */
public class AnimationUtil {

    public static AnimationUtil animationUtil = null;

    public static AnimationUtil getInstance() {
        if (animationUtil == null) {
            animationUtil = new AnimationUtil();
        }
        return animationUtil;
    }

    public void animateOpen(View v, int mHiddenViewMeasuredHeight) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, mHiddenViewMeasuredHeight);
        animator.start();
    }

    public void animateOpen(View v, View v2, int mHiddenViewMeasuredHeight) {
        v2.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, mHiddenViewMeasuredHeight);
        animator.start();
    }

    public void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    public void animationIvOpen(ImageView iv) {
        RotateAnimation animation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        iv.startAnimation(animation);
    }

    public void animationIvClose(ImageView iv) {
        RotateAnimation animation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        iv.startAnimation(animation);
    }

    private static ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

    public void animationIvOpenClockwise(ImageView iv, float toDegrees) {
        RotateAnimation animation = new RotateAnimation(0, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        iv.startAnimation(animation);
    }

    public void animationIvCloseAntiClockwise(ImageView iv, float fromDegrees) {
        RotateAnimation animation = new RotateAnimation(fromDegrees, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        iv.startAnimation(animation);
    }
}
