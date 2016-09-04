/**
 * Copyright 2014 Zhenguo Jin (jinzhenguo1990@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.util.view;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * ��ͼ���������䣬�ṩ�򵥵Ŀ�����ͼ�Ķ����Ĺ��߷���
 *
 * @author zhenguo
 */
public final class ViewAnimationUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private ViewAnimationUtils() {
        throw new Error("Do not need instantiate!");
    }

    // /**
    // * Ĭ�϶�������ʱ��
    // */
    // public static final long DEFAULT_ANIMATION_DURATION = 300;

	/*
     *  ************************************************************* ��ͼ͸���Ƚ��䶯��
	 * ********************************************************************
	 */

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)��
     *
     * @param view              ���������ͼ
     * @param isBanClick        ��ִ�ж����Ĺ������Ƿ��ֹ���
     * @param durationMillis    ����ʱ�䣬����
     * @param animationListener ����������
     */
    public static void invisibleViewByAlpha(final View view,
                                            long durationMillis, final boolean isBanClick,
                                            final AnimationListener animationListener) {
        if (view.getVisibility() != View.INVISIBLE) {
            view.setVisibility(View.INVISIBLE);
            AlphaAnimation hiddenAlphaAnimation = AnimationUtils
                    .getHiddenAlphaAnimation(durationMillis);
            hiddenAlphaAnimation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (isBanClick) {
                        view.setClickable(false);
                    }
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationRepeat(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (isBanClick) {
                        view.setClickable(true);
                    }
                    if (animationListener != null) {
                        animationListener.onAnimationEnd(animation);
                    }
                }
            });
            view.startAnimation(hiddenAlphaAnimation);
        }
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)��
     *
     * @param view              ���������ͼ
     * @param durationMillis    ����ʱ�䣬����
     * @param animationListener ����������
     */
    public static void invisibleViewByAlpha(final View view,
                                            long durationMillis, final AnimationListener animationListener) {
        invisibleViewByAlpha(view, durationMillis, false, animationListener);
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)��
     *
     * @param view           ���������ͼ
     * @param durationMillis ����ʱ�䣬����
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void invisibleViewByAlpha(final View view,
                                            long durationMillis, boolean isBanClick) {
        invisibleViewByAlpha(view, durationMillis, isBanClick, null);
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)��
     *
     * @param view           ���������ͼ
     * @param durationMillis ����ʱ�䣬����
     */
    public static void invisibleViewByAlpha(final View view, long durationMillis) {
        invisibleViewByAlpha(view, durationMillis, false, null);
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view              ���������ͼ
     * @param isBanClick        ��ִ�ж����Ĺ������Ƿ��ֹ���
     * @param animationListener ����������
     */
    public static void invisibleViewByAlpha(final View view,
                                            boolean isBanClick, final AnimationListener animationListener) {
        invisibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                isBanClick, animationListener);
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view              ���������ͼ
     * @param animationListener ����������
     */
    public static void invisibleViewByAlpha(final View view,
                                            final AnimationListener animationListener) {
        invisibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                false, animationListener);
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view       ���������ͼ
     * @param isBanClick ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void invisibleViewByAlpha(final View view, boolean isBanClick) {
        invisibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                isBanClick, null);
    }

    /**
     * ��������ͼ������ȥ��view.setVisibility(View.INVISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view ���������ͼ
     */
    public static void invisibleViewByAlpha(final View view) {
        invisibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                false, null);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)��
     *
     * @param view              ���������ͼ
     * @param durationMillis    ����ʱ�䣬����
     * @param isBanClick        ��ִ�ж����Ĺ������Ƿ��ֹ���
     * @param animationListener ����������
     */
    public static void goneViewByAlpha(final View view, long durationMillis,
                                       final boolean isBanClick, final AnimationListener animationListener) {
        if (view.getVisibility() != View.GONE) {
            view.setVisibility(View.GONE);
            AlphaAnimation hiddenAlphaAnimation = AnimationUtils
                    .getHiddenAlphaAnimation(durationMillis);
            hiddenAlphaAnimation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (isBanClick) {
                        view.setClickable(false);
                    }
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationRepeat(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (isBanClick) {
                        view.setClickable(true);
                    }
                    if (animationListener != null) {
                        animationListener.onAnimationEnd(animation);
                    }
                }
            });
            view.startAnimation(hiddenAlphaAnimation);
        }
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)��
     *
     * @param view              ���������ͼ
     * @param durationMillis    ����ʱ�䣬����
     * @param animationListener ����������
     */
    public static void goneViewByAlpha(final View view, long durationMillis,
                                       final AnimationListener animationListener) {
        goneViewByAlpha(view, durationMillis, false, animationListener);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)��
     *
     * @param view           ���������ͼ
     * @param durationMillis ����ʱ�䣬����
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void goneViewByAlpha(final View view, long durationMillis,
                                       final boolean isBanClick) {
        goneViewByAlpha(view, durationMillis, isBanClick, null);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)��
     *
     * @param view           ���������ͼ
     * @param durationMillis ����ʱ�䣬����
     */
    public static void goneViewByAlpha(final View view, long durationMillis) {
        goneViewByAlpha(view, durationMillis, false, null);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view              ���������ͼ
     * @param isBanClick        ��ִ�ж����Ĺ������Ƿ��ֹ���
     * @param animationListener ����������
     */
    public static void goneViewByAlpha(final View view,
                                       final boolean isBanClick, final AnimationListener animationListener) {
        goneViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                isBanClick, animationListener);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view              ���������ͼ
     * @param animationListener ����������
     */
    public static void goneViewByAlpha(final View view,
                                       final AnimationListener animationListener) {
        goneViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION, false,
                animationListener);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view       ���������ͼ
     * @param isBanClick ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void goneViewByAlpha(final View view, final boolean isBanClick) {
        goneViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                isBanClick, null);
    }

    /**
     * ��������ͼ������ȥ���ӽ������Ƴ���view.setVisibility(View.GONE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view ���������ͼ
     */
    public static void goneViewByAlpha(final View view) {
        goneViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION, false,
                null);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)��
     *
     * @param view              ���������ͼ
     * @param durationMillis    ����ʱ�䣬����
     * @param isBanClick        ��ִ�ж����Ĺ������Ƿ��ֹ���
     * @param animationListener ����������
     */
    public static void visibleViewByAlpha(final View view, long durationMillis,
                                          final boolean isBanClick, final AnimationListener animationListener) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
            AlphaAnimation showAlphaAnimation = AnimationUtils
                    .getShowAlphaAnimation(durationMillis);
            showAlphaAnimation.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (isBanClick) {
                        view.setClickable(false);
                    }
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationRepeat(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (isBanClick) {
                        view.setClickable(true);
                    }
                    if (animationListener != null) {
                        animationListener.onAnimationEnd(animation);
                    }
                }
            });
            view.startAnimation(showAlphaAnimation);
        }
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)��
     *
     * @param view              ���������ͼ
     * @param durationMillis    ����ʱ�䣬����
     * @param animationListener ����������
     */
    public static void visibleViewByAlpha(final View view, long durationMillis,
                                          final AnimationListener animationListener) {
        visibleViewByAlpha(view, durationMillis, false, animationListener);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)��
     *
     * @param view           ���������ͼ
     * @param durationMillis ����ʱ�䣬����
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void visibleViewByAlpha(final View view, long durationMillis,
                                          final boolean isBanClick) {
        visibleViewByAlpha(view, durationMillis, isBanClick, null);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)��
     *
     * @param view           ���������ͼ
     * @param durationMillis ����ʱ�䣬����
     */
    public static void visibleViewByAlpha(final View view, long durationMillis) {
        visibleViewByAlpha(view, durationMillis, false, null);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view              ���������ͼ
     * @param animationListener ����������
     * @param isBanClick        ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void visibleViewByAlpha(final View view,
                                          final boolean isBanClick, final AnimationListener animationListener) {
        visibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                isBanClick, animationListener);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view              ���������ͼ
     * @param animationListener ����������
     */
    public static void visibleViewByAlpha(final View view,
                                          final AnimationListener animationListener) {
        visibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                false, animationListener);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view       ���������ͼ
     * @param isBanClick ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void visibleViewByAlpha(final View view,
                                          final boolean isBanClick) {
        visibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                isBanClick, null);
    }

    /**
     * ��������ͼ������ʾ������view.setVisibility(View.VISIBLE)����
     * Ĭ�ϵĳ���ʱ��ΪDEFAULT_ALPHA_ANIMATION_DURATION
     *
     * @param view ���������ͼ
     */
    public static void visibleViewByAlpha(final View view) {
        visibleViewByAlpha(view, AnimationUtils.DEFAULT_ANIMATION_DURATION,
                false, null);
    }

	/*
	 *  ************************************************************* ��ͼ�ƶ�����
	 * ********************************************************************
	 */

    /**
     * ��ͼ�ƶ�
     *
     * @param view           Ҫ�ƶ�����ͼ
     * @param fromXDelta     X�Ὺʼ����
     * @param toXDelta       X���������
     * @param fromYDelta     Y�Ὺʼ����
     * @param toYDelta       Y���������
     * @param cycles         �ظ�
     * @param durationMillis ����ʱ��
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void translate(final View view, float fromXDelta,
                                 float toXDelta, float fromYDelta, float toYDelta, float cycles,
                                 long durationMillis, final boolean isBanClick) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setDuration(durationMillis);
        if (cycles > 0.0) {
            translateAnimation.setInterpolator(new CycleInterpolator(cycles));
        }
        translateAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isBanClick) {
                    view.setClickable(false);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isBanClick) {
                    view.setClickable(true);
                }
            }
        });
        view.startAnimation(translateAnimation);
    }

    /**
     * ��ͼ�ƶ�
     *
     * @param view           Ҫ�ƶ�����ͼ
     * @param fromXDelta     X�Ὺʼ����
     * @param toXDelta       X���������
     * @param fromYDelta     Y�Ὺʼ����
     * @param toYDelta       Y���������
     * @param cycles         �ظ�
     * @param durationMillis ����ʱ��
     */
    public static void translate(final View view, float fromXDelta,
                                 float toXDelta, float fromYDelta, float toYDelta, float cycles,
                                 long durationMillis) {
        translate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles,
                durationMillis, false);
    }

    /**
     * ��ͼҡ��
     *
     * @param view           Ҫҡ������ͼ
     * @param fromXDelta     X�Ὺʼ����
     * @param toXDelta       X���������
     * @param cycles         �ظ�����
     * @param durationMillis ����ʱ��
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void shake(View view, float fromXDelta, float toXDelta,
                             float cycles, long durationMillis, final boolean isBanClick) {
        translate(view, fromXDelta, toXDelta, 0.0f, 0.0f, cycles,
                durationMillis, isBanClick);
    }

    /**
     * ��ͼҡ��
     *
     * @param view           Ҫҡ������ͼ
     * @param fromXDelta     X�Ὺʼ����
     * @param toXDelta       X���������
     * @param cycles         �ظ�����
     * @param durationMillis ����ʱ��
     */
    public static void shake(View view, float fromXDelta, float toXDelta,
                             float cycles, long durationMillis) {
        translate(view, fromXDelta, toXDelta, 0.0f, 0.0f, cycles,
                durationMillis, false);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10���ظ�7��
     *
     * @param view
     * @param cycles         �ظ�����
     * @param durationMillis ����ʱ��
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void shake(View view, float cycles, long durationMillis,
                             final boolean isBanClick) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, cycles, durationMillis,
                isBanClick);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10������700����
     *
     * @param view
     * @param cycles         �ظ�����
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void shake(View view, float cycles, final boolean isBanClick) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, cycles, 700, isBanClick);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10
     *
     * @param view
     * @param cycles         �ظ�����
     * @param durationMillis ����ʱ��
     */
    public static void shake(View view, float cycles, long durationMillis) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, cycles, durationMillis, false);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10���ظ�7��
     *
     * @param view
     * @param durationMillis ����ʱ��
     * @param isBanClick     ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void shake(View view, long durationMillis,
                             final boolean isBanClick) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7, durationMillis, isBanClick);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10������700����
     *
     * @param view   Ҫҡ������ͼ
     * @param cycles �ظ�����
     */
    public static void shake(View view, float cycles) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, cycles, 700, false);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10���ظ�7��
     *
     * @param view
     * @param durationMillis ����ʱ��
     */
    public static void shake(View view, long durationMillis) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7, durationMillis, false);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10���ظ�7�Σ�����700����
     *
     * @param view
     * @param isBanClick ��ִ�ж����Ĺ������Ƿ��ֹ���
     */
    public static void shake(View view, final boolean isBanClick) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7, 700, isBanClick);
    }

    /**
     * ��ͼҡ�Σ�Ĭ��ҡ�η���Ϊ10���ظ�7�Σ�����700����
     *
     * @param view
     */
    public static void shake(View view) {
        translate(view, 0.0f, 10.0f, 0.0f, 0.0f, 7, 700, false);
    }

}
