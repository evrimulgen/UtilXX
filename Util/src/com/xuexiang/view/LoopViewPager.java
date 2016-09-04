package com.xuexiang.view;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.loopviewpager.LoopScroller;
import com.xuexiang.view.loopviewpager.animate.DepthPageTransformer;
import com.xuexiang.view.loopviewpager.animate.ZoomOutPageTransformer;

/**
 * ViewPager�����ֲ�
 *
 * @USER Edwin
 * @DATE 16/6/11 ����5:56
 */
public class LoopViewPager extends ViewPager implements View.OnTouchListener {
    private static final int MESSAGE_LOOP = 5;
    private Context context;
    private static int loop_ms = 4000;//�ֲ���ʱ��(����)

    public int getLoop_ms() {
        //̫��Ҳ�ܲ���,������
        if (loop_ms < 1000)
            loop_ms = 1000;
        return loop_ms;
    }

    public void setLoop_ms(int loop_ms) {
        this.loop_ms = loop_ms;
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        setOnTouchListener(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "LoopViewPager"));
        int loop_ms = typedArray.getInteger(MResource.getIdByName(context, "styleable", "LoopViewPager_loop_ms"), 4000);
        int loop_duration = typedArray.getInteger(MResource.getIdByName(context, "styleable", "LoopViewPager_loop_duration"), 2000);
        int loop_style = typedArray.getInteger(MResource.getIdByName(context, "styleable", "LoopViewPager_loop_style"), -1);
        setLoop_ms(loop_ms);

        //TODO ��ֹ����
        if (loop_duration > loop_ms)
            loop_duration = loop_ms;

        //TODO ���û���������
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);

            LoopScroller mScroller = new LoopScroller(context,
                    new AccelerateInterpolator());
            //������setDuration�ķ�ʽ��������
            mScroller.setmDuration(loop_duration);
            mField.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO ������ʽ
        if (loop_style == 1) {
            setPageTransformer(true, new DepthPageTransformer());
        } else if (loop_style == 2) {
            setPageTransformer(true, new ZoomOutPageTransformer());
        }
        typedArray.recycle();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            	LogUtils.e("ACTION_DOWN");
                stopLoop();
                break;
            case MotionEvent.ACTION_MOVE:
            	LogUtils.e("ACTION_MOVE");
                stopLoop();
                break;
            case MotionEvent.ACTION_UP:
            	LogUtils.e("ACTION_UP");
                startLoop();
                break;
            default:
                break;
        }
        return false;
    }


    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == MESSAGE_LOOP) {
                if (getCurrentItem() < Short.MAX_VALUE - 1) {
                    setCurrentItem(getCurrentItem() + 1, true);
                    sendEmptyMessageDelayed(MESSAGE_LOOP, getLoop_ms());
                }
            }
        }
    };


    /**
     * ��ʼѭ��
     */
    public void startLoop() {
        handler.removeCallbacksAndMessages(MESSAGE_LOOP);
        handler.sendEmptyMessageDelayed(MESSAGE_LOOP, getLoop_ms());
        LogUtils.e("��ʼѭ�� startLoop");
    }

    /**
     * ֹͣѭ��
     * �����onDestoryִ��
     */
    public void stopLoop() {
        handler.removeMessages(MESSAGE_LOOP);
        LogUtils.e("ֹͣѭ�� stopLoop");
    }


    /**
     * ѹ��ͼƬ
     *
     * @param res       ��Դ
     * @param resId     ͼƬID
     * @param reqWidth  0��ʾ��ȡViewPager�Ŀ�
     * @param reqHeight 0��ʾ��ȡViewPager�ĸ�
     * @return
     */
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {
        // ��һ�ν���inJustDecodeBounds = true���ߴ�
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // ����inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // ������inSampleSize����λͼ
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * �㷨
     * ������Ҫ����200*200
     * ͼƬʵ�ʿ��1000*1000������2�Ժ���500*500
     * ���ֱ�Ҫ��Ļ��Ǵ󣬾��ٳ���2.��ֱ���ﵽҪ��
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return ѹ������
     */
    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // ԭʼͼ��ĸ߶ȺͿ��
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // �������inSampleSizeֵ��2����,�����߸߶ȺͿ�ȴ�������ĸ߶ȺͿ�ȡ�
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


}
