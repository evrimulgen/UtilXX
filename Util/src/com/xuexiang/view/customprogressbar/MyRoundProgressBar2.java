package com.xuexiang.view.customprogressbar;

import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by C058 on 2016/5/26.
 * ģ��ios app storeӦ������Բ�ν�ͼ��
 */
public class MyRoundProgressBar2 extends MyHoriztalProgressBar {

    private static final int DEFAULT_PROGRESS_RADIUS = 30;
    private int mRadius = dp2px(DEFAULT_PROGRESS_RADIUS);
    private int mInRadius;
    private RectF mRectf, mInRectf;

    public MyRoundProgressBar2(Context context) {
        this(context, null);
    }

    public MyRoundProgressBar2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRoundProgressBar2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, RUtils.getStyleable(context, "MyRoundProgressBar"));
        mRadius = (int) ta.getDimension(MResource.getIdByName(context, "styleable", "MyRoundProgressBar_progressbar_radius"), mRadius);
        ta.recycle();

        mReachHeight = mUnReachHeight * 2;
        mPaint.setAntiAlias(true);//�����
        mPaint.setDither(true); //������ģʽ
        mPaint.setStyle(Paint.Style.STROKE);//���ʷ������Ϊ����
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int diameter = mRadius * 2 + getPaddingLeft() + getPaddingRight() + mUnReachHeight * 2; //�ؼ���� Ĭ���ĸ�paddingһ��
        int width = resolveSize(diameter, widthMeasureSpec);
        int height = resolveSize(diameter, heightMeasureSpec);
        int realWidth = Math.min(width, height);//��������ò�һ�£�ȡС���Ǹ�
        //��Բ�İ뾶
        mRadius = (realWidth - getPaddingLeft() - getPaddingRight() - mUnReachHeight) / 2;
        mRectf = new RectF(0, 0, mRadius * 2, mRadius * 2);
        //��Բ�İ뾶
        mInRadius = mRadius - mUnReachHeight;
        mInRectf = new RectF(0, 0, mInRadius * 2, mInRadius * 2);
        setMeasuredDimension(realWidth, realWidth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        //draw unreachbar
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        //��Բ�㿪ʼ��Բ
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        //draw reachbar
        //�������ƶ�������Բ��λ��
        canvas.translate(mUnReachHeight, mUnReachHeight);
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(mInRectf, 0, sweepAngle, false, mPaint);
//        //draw text
//        String text = getProgress() + "%";
//        int textWidth = (int) mPaint.measureText(text);
//        int textHeight = (int) ((mPaint.descent() + mPaint.ascent()) / 2);
//        mPaint.setColor(mTextColor);
//        canvas.drawText(text, mRadius - textWidth / 2, mRadius - textHeight, mPaint);
        canvas.restore();
    }
}
