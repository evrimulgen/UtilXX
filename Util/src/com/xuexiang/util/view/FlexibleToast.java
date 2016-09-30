package com.xuexiang.util.view;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * @ClassName: FlexibleToast
 * @Description: ȫ��Toast��
 * 1. ֧��Ĭ�ϸ�ʽ���������²���ΪImageView��TextView��TextView�������ؼ��������������ʾ������
 * 2. ֧��Top��Center��Bottom��λ����ʾ
 * 3. ֧�ֶ�������ʾ��ʽ�����Դ����Զ����layout��View
 * 4. ����һ��Toast���󣬷�ֹ���Toast�ص�����ʾʱ���ۼӣ��ÿؼ����������һ�ε����ú���ʾ��
 * 5. ������Զ����Application��new��Toast��Activity��Fragment��Adapter�ж�����ֱ�ӵ��á�
 *
 *
 */
public class FlexibleToast {

    public static final int GRAVITY_BOTTOM = 0;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_TOP = 2;
    public static final int TOAST_SHORT = 0;
    public static final int TOAST_LONG = 1;

    private Context mContext;

    private Toast flexibleToast;

    public void toastShow(Builder builder) {
        if (flexibleToast == null) {
            flexibleToast = new Toast(mContext);
        }
        // toast position
        if (builder.mGravity == GRAVITY_CENTER) {
            flexibleToast.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL, 0, 0);
        } else if (builder.mGravity == GRAVITY_TOP) {
            flexibleToast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, 0, DisplayUtils.dip2px(mContext, 20));
        } else {
            flexibleToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, DisplayUtils.dip2px(mContext, 20));
        }
        if (builder.mDuration == TOAST_LONG) {
            flexibleToast.setDuration(Toast.LENGTH_LONG);
        } else {
            flexibleToast.setDuration(Toast.LENGTH_SHORT);
        }
        if (builder.hasCustomerView && builder.mCustomerView != null) {
            flexibleToast.setView(builder.mCustomerView);
        } else {
            flexibleToast.setView(builder.mDefaultView);
        }
        flexibleToast.show();
    }

    public FlexibleToast(Context context) {
        mContext = context;
    }

    /**
     * ����Toast����ʾ��ʽ
     */
    public static class Builder {
        private View mDefaultView;
        private View mCustomerView;
        private ImageView mIvImage;
        private TextView mTvFirst;
        private TextView mTvSecond;

        private View dividerFirst;
        private View dividerSecond;

        private int mDuration = Toast.LENGTH_SHORT;// 0 short, 1 long
        private int mGravity = 0;
        private boolean hasCustomerView = false; // �Ƿ�ʹ���Զ���layout


        /**
         * ʹ��ȫ�ֵ�ApplicationContext���г�ʼ��
         * @param context
         */
        public Builder(Context context) {
            mDefaultView = LayoutInflater.from(context).inflate(RUtils.getLayout(context, "layout_toast_flexible"), null);
            mIvImage = (ImageView) mDefaultView.findViewById(RUtils.getId(context, "iv_img"));
            mTvFirst = (TextView) mDefaultView.findViewById(RUtils.getId(context, "tv_text_first"));
            mTvSecond = (TextView) mDefaultView.findViewById(RUtils.getId(context, "tv_text_second"));
            dividerFirst = mDefaultView.findViewById(RUtils.getId(context, "divider_first"));
            dividerSecond = mDefaultView.findViewById(RUtils.getId(context, "divider_second"));
        }

        public Builder setImageResource(int resId) {
            this.mIvImage.setImageResource(resId);
            this.mIvImage.setVisibility(View.VISIBLE);
            this.dividerFirst.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setFirstText(String firstText) {
            this.mTvFirst.setText(firstText);
            this.mTvFirst.setVisibility(View.VISIBLE);
            this.dividerSecond.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setSecondText(String secondText) {
            this.mTvSecond.setText(secondText);
            this.mTvSecond.setVisibility(View.VISIBLE);
            return this;
        }

        public Builder setDuration(int duration) {
            this.mDuration = duration;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        /**
         * ΪToastָ���Զ����layout����ʱ�����ImageView��TextView������ʧЧ��
         * @param customerView
         * @return
         */
        public Builder setCustomerView(View customerView) {
            this.mCustomerView = customerView;
            this.hasCustomerView = true;
            return this;
        }
    }

}
