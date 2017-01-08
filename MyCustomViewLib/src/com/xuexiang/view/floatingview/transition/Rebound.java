package com.xuexiang.view.floatingview.transition;

import com.xuexiang.view.facebook.rebound.Spring;

/**
 * Author UFreedom
 * Date : 2016 十月 19
 */

public interface Rebound {
    
    public Spring createSpringByBouncinessAndSpeed(double bounciness, double speed);

    public Spring createSpringByTensionAndFriction(double tension, double friction) ;

    public float transition(double progress, float startValue, float endValue);
    
}
