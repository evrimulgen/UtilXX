package com.xuexiang.view.floatingview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;

import com.xuexiang.view.floatingview.spring.SimpleReboundListener;
import com.xuexiang.view.floatingview.spring.SpringHelper;
import com.xuexiang.view.floatingview.transition.BaseFloatingPathTransition;
import com.xuexiang.view.floatingview.transition.FloatingPath;
import com.xuexiang.view.floatingview.transition.PathPosition;
import com.xuexiang.view.floatingview.transition.YumFloating;

/**
 * Author UFreedom
 * Date : 2016 十月 20
 */

public class CurveFloatingPathTransition extends BaseFloatingPathTransition {

    private Path path;

    public CurveFloatingPathTransition() {
        
    }

    public CurveFloatingPathTransition(Path path) {
        this.path = path;
    }

    @Override
    public FloatingPath getFloatingPath() {
        if (path == null){
            path = new Path();
            path.moveTo(0, 0);
            path.quadTo(-100, -200, 0, -300);
            path.quadTo(200, -400, 0, -500);
        }
        return FloatingPath.create(path, false);
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator translateAnimator;
        ValueAnimator alphaAnimator;

        
        translateAnimator = ObjectAnimator.ofFloat(getStartPathPosition(), getEndPathPosition());
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (Float) valueAnimator.getAnimatedValue();
                PathPosition floatingPosition = getFloatingPosition(value);
                yumFloating.setTranslationX(floatingPosition.x);
                yumFloating.setTranslationY(floatingPosition.y);

            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setTranslationX(0);
                yumFloating.setTranslationY(0);
                yumFloating.setAlpha(0f);
            }
        });


        alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0f);
        alphaAnimator.setDuration(3000);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });
        
        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,11, 15)
                .reboundListener(new SimpleReboundListener(){
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);  
        
        translateAnimator.setDuration(3000);
        translateAnimator.setStartDelay(50);
        translateAnimator.start();
        alphaAnimator.start();
    }
    
}
