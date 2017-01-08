package com.xuexiang.view.floatingview.effect;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import com.xuexiang.view.floatingview.spring.SimpleReboundListener;
import com.xuexiang.view.floatingview.spring.SpringHelper;
import com.xuexiang.view.floatingview.transition.FloatingTransition;
import com.xuexiang.view.floatingview.transition.YumFloating;


/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public class ScaleFloatingTransition implements FloatingTransition {

    private long duration;
    private double bounciness;
    private double speed;

    public ScaleFloatingTransition() {
        duration = 1000;
        bounciness = 10;
        speed = 15;
    }

    public ScaleFloatingTransition(long duration) {
        this.duration = duration;
        bounciness = 10;
        speed = 15;
    }

    public ScaleFloatingTransition(long duration, double bounciness, double speed) {
        this.duration = duration;
        this.bounciness = bounciness;
        this.speed = speed;
    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        
        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(1.0f, 0.0f);
        alphaAnimator.setDuration(duration);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yumFloating.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });
        alphaAnimator.start();

        SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,bounciness, speed)
                .reboundListener(new SimpleReboundListener(){
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        yumFloating.setScaleX((float) currentValue);
                        yumFloating.setScaleY((float) currentValue);
                    }
                }).start(yumFloating);
    }
    
}
