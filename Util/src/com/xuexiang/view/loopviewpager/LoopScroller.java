package com.xuexiang.view.loopviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Scroller
 * 更好的用户体验
 *
 * @USER Edwin
 * @DATE 16/6/12 下午9:27
 */
public class LoopScroller extends Scroller {
    private int mDuration = 1000;//速率必须小于延迟时间loop_ms

    public LoopScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }

}
