package com.xuexiang.view.loopviewpager;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.LoopViewPager;


/**
 * LoopViewPager����
 * <p/>
 * ������LoopViewPager��LinearLayout
 * ����ͨ��view_loop_viewpager�޸�һЩ����
 *
 * @USER Edwin
 * @DATE 16/6/14 ����11:58
 */
public class LoopViewPagerLayout extends RelativeLayout implements OnPageChangeListener {
    private LoopViewPager loopViewPager;
    private LinearLayout indicatorLayout;
    private OnBannerItemClickListener onBannerItemClickListener = null;
    private LoopPagerAdapterWrapper loopPagerAdapterWrapper;
    private int totalDistance;//С���Ҫ�ƶ���ȫ������
    private int startX;//С��㿪ʼλ��
    private int size = DisplayUtils.dip2px(getContext(), 8);//���õ�Ĵ�С;
    private ArrayList<BannerInfo> bannerInfos;//banner����
    private TextView animIndicator;//�ƶ���С���
    private TextView[] indicators;//��ʼ��С�׵�

    public LoopViewPagerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(RUtils.getLayout(context, "view_loop_viewpager"), this);
        loopViewPager = (LoopViewPager) findViewById(RUtils.getId(context, "edwin_LoopViewPager"));
        indicatorLayout = (LinearLayout) findViewById(RUtils.getId(context, "ll_main_indicatorLayout"));
    }

    public void setLoopData(ArrayList<BannerInfo> bannerInfos, OnBannerItemClickListener onBannerItemClickListener) {
        this.bannerInfos = bannerInfos;
        this.onBannerItemClickListener = onBannerItemClickListener;

        indicators = new TextView[bannerInfos.size()];
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new TextView(getContext());
            indicators[i].setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            if (i != indicators.length - 1) {
                params.setMargins(0, 0, size, 0);
            } else {
                params.setMargins(0, 0, 0, 0);
            }
            indicators[i].setLayoutParams(params);
            indicators[i].setBackgroundResource(RUtils.getDrawable(getContext(), "indicator_normal_background"));//����Ĭ�ϵı�����ɫ
            indicatorLayout.addView(indicators[i]);
        }

        //TODO СԲ��
        animIndicator = new TextView(getContext());
        animIndicator.setLayoutParams(new LinearLayout.LayoutParams(size, size));
        animIndicator.setBackgroundResource(RUtils.getDrawable(getContext(), "indicator_selected_background"));//����ѡ�еı�����ɫ
        addView(animIndicator);

        indicatorLayout.getViewTreeObserver().addOnPreDrawListener(new MyOnPreDrawListener());

        loopPagerAdapterWrapper = new LoopPagerAdapterWrapper();
        loopViewPager.setAdapter(loopPagerAdapterWrapper);
        loopViewPager.setOnPageChangeListener(this);

        int index = Short.MAX_VALUE / 2 - (Short.MAX_VALUE / 2) % bannerInfos.size();
        loopViewPager.setCurrentItem(index);
    }


    class MyOnPreDrawListener implements OnPreDrawListener {

        @Override
        public boolean onPreDraw() {
            Rect rootRect = new Rect();
            Point globalOffset = new Point();
            getGlobalVisibleRect(rootRect, globalOffset);

            Rect firstRect = new Rect();
            indicatorLayout.getChildAt(0).getGlobalVisibleRect(firstRect);
            firstRect.offset(-globalOffset.x, -globalOffset.y);

            Rect lastRect = new Rect();
            indicatorLayout.getChildAt(indicators.length - 1).getGlobalVisibleRect(lastRect);

            totalDistance = lastRect.left - indicatorLayout.getLeft();
            startX = firstRect.left;

            ViewCompat.setTranslationX(animIndicator, firstRect.left);
            ViewCompat.setTranslationY(animIndicator, firstRect.top);
            indicatorLayout.getViewTreeObserver().removeOnPreDrawListener(this);
            return false;
        }
    }


    class LoopPagerAdapterWrapper extends PagerAdapter {

        @Override
        public int getCount() {
            return Short.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final int index = position % bannerInfos.size();
            final BannerInfo bannerInfo = bannerInfos.get(index);
            final ImageView child = new ImageView(getContext());
            child.setImageResource(bannerInfo.resId);
            child.setScaleType(ImageView.ScaleType.CENTER_CROP);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBannerItemClickListener != null)
                        onBannerItemClickListener.onBannerClick(index, bannerInfos);
                }
            });
            container.addView(child);
            return child;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (loopPagerAdapterWrapper.getCount() > 0) {
            float length = ((position % 4) + positionOffset) / (bannerInfos.size() - 1);
            //TODO ��ֹ���һ��ͼƬС��㻬��ȥ��.
            if (length >= 1)
                length = 1;
            float path = length * totalDistance;
            ViewCompat.setTranslationX(animIndicator, startX + path);

        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class BannerInfo {
        public int resId;
        public String title;
        public String url;

        public BannerInfo(int resId, String title) {
            this.resId = resId;
            this.title = title;
        }

        public BannerInfo(int resId, String title, String url) {
            this.resId = resId;
            this.title = title;
            this.url = url;
        }

    }


    public interface OnBannerItemClickListener {
        void onBannerClick(int index, ArrayList<BannerInfo> banner);
    }

    /**
     * ��ʼѭ��
     */
    public void startLoop() {
        loopViewPager.startLoop();
    }

    /**
     * ֹͣѭ��
     * �����onDestoryִ��
     */
    public void stopLoop() {
        loopViewPager.stopLoop();
    }
}
