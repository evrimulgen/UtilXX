package com.xuexiang.view;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.view.DisplayUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: xx on 16/1/4.
 */
public class CompareIndicator extends View {
	
    /**
     * 反对面的图标
     */
    private Bitmap mOppositeBitmap;
    
    /**
     * 赞成面的图标
     */
    private Bitmap mApproveBitmap;
    private Context context;
    private Paint BitmapPaint;
    private Paint mOppositePaint;
    private Paint mApprovePaint;

    /**
     * 反对面的数量
     */
    private int mOppostiteCount = 0;

    /**
     * 赞成面的数量
     */
    private int mApproveCount = 0;
    private float marginLeft_16dp;
    private float marginTop_12dp;
    private float Start_down_cx;
    private float Start_down_cy;

    /**
     * 赞成线的颜色
     */
    private int approveLineColor;
    
    /**
     * 反对线的颜色
     */
    private int oppositeLineColor;

    public Bitmap getOppositeBitmap() {
        return mOppositeBitmap;
    }

    public void setOppositeBitmap(Bitmap oppositeBitmap) {
        mOppositeBitmap = oppositeBitmap;
    }

    public Bitmap getApproveBitmap() {
        return mApproveBitmap;
    }

    public void setApproveBitmap(Bitmap approveBitmap) {
        mApproveBitmap = approveBitmap;
    }

    public Paint getOppositePaint() {
        return mOppositePaint;
    }

    public void setOppositePaint(Paint oppositePaint) {
        mOppositePaint = oppositePaint;
    }

    public Paint getApprovePaint() {
        return mApprovePaint;
    }

    public void setApprovePaint(Paint approvePaint) {
        mApprovePaint = approvePaint;
    }

    public float getStart_down_cx() {
        return Start_down_cx;
    }

    public void setStart_down_cx(float start_down_cx) {
        Start_down_cx = start_down_cx;
    }

    public float getStart_down_cy() {
        return Start_down_cy;
    }

    public void setStart_down_cy(float start_down_cy) {
        Start_down_cy = start_down_cy;
    }

    public Paint getBitmapPaint() {
        return BitmapPaint;
    }

    public void setBitmapPaint(Paint bitmapPaint) {
        BitmapPaint = bitmapPaint;
    }

    public int getOppostiteCount() {
        return mOppostiteCount;
    }

    public void setOppostiteCount(int oppostiteCount) {
        this.mOppostiteCount = oppostiteCount;
        postInvalidate();
    }
    public void updateView(int approveCount,int oppositeCount){
        this.mApproveCount = approveCount;
        this.mOppostiteCount =oppositeCount;
        postInvalidate();
    }

    public int getApproveCount() {
        return mApproveCount;
    }

    public void setApproveCount(int approveCount) {
        this.mApproveCount = approveCount;
        postInvalidate();
    }

    public CompareIndicator(Context context) {
        super(context);

        this.context = context;
        init(null, 0);
    }

    public CompareIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public CompareIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(attrs, 0);
    }

    private void init(AttributeSet attrs, int defStyle) {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "compareindicator"));
        int approveIconId = typedArray.getResourceId(RUtils.getStyleableAttribute(context, "compareindicator_approve_res"), RUtils.getDrawable(context, "good"));
        int oppositeIconId = typedArray.getResourceId(RUtils.getStyleableAttribute(context, "compareindicator_opposite_res"), RUtils.getDrawable(context, "bad"));
        approveLineColor = typedArray.getColor(RUtils.getStyleableAttribute(context, "compareindicator_approve_line_color"), Color.parseColor("#009900"));
        oppositeLineColor = typedArray.getColor(RUtils.getStyleableAttribute(context, "compareindicator_oppose_line_color"), Color.parseColor("#fb4747"));
        float lineWidthDimen = typedArray.getDimension(RUtils.getStyleableAttribute(context, "compareindicator_lineWidth"), 5f);
        typedArray.recycle();
        mOppositeBitmap = BitmapFactory.decodeResource(getResources(), approveIconId);
        mApproveBitmap = BitmapFactory.decodeResource(getResources(), oppositeIconId);
        BitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOppositePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOppositePaint.setColor(oppositeLineColor);
        mApprovePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mApprovePaint.setColor(approveLineColor);
        float lineWidth = DisplayUtils.dip2px(context, lineWidthDimen);
        /**
         * 此处不能开启canvas绘图硬件加速 会导致部分api 显示不正常
         */

        /**
         * 画 第三个圆弧
         */

        /**
         * 画涨直线
         */
        mApprovePaint.setStrokeWidth(lineWidth);
        mApprovePaint.setStrokeCap(Paint.Cap.ROUND);
        /**
         * 画 跌直线
         */
        mOppositePaint.setStrokeWidth(lineWidth);
        mOppositePaint.setStrokeCap(Paint.Cap.ROUND);
        /**
         * 画左右涨跌图
         */
        marginLeft_16dp = DisplayUtils.dip2px(context, 16f);
        marginTop_12dp = DisplayUtils.dip2px(context, 10f);
        Start_down_cx = marginLeft_16dp + mOppositeBitmap.getWidth() + DisplayUtils.dip2px(context, 8f);
        Start_down_cy = marginTop_12dp + mOppositeBitmap.getHeight() / 2;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float end_down_cy = Start_down_cy;
        float end_up_cx = getWidth() - marginLeft_16dp - mApproveBitmap.getWidth() - DisplayUtils.dip2px(context, 8);
        float width = end_up_cx - Start_down_cx - DisplayUtils.dip2px(context, 8);
        float approveLineWidth;
        float oppositeLineWidth;
        if (mOppostiteCount != 0 && mApproveCount != 0) {
            approveLineWidth = width / (mOppostiteCount + mApproveCount) * mApproveCount;
            oppositeLineWidth = width / (mOppostiteCount + mApproveCount) * mOppostiteCount;
        } else if (mOppostiteCount == 0 && mApproveCount != 0) {
            oppositeLineWidth = DisplayUtils.dip2px(context, 1);
            approveLineWidth = width - oppositeLineWidth;

        } else if (mOppostiteCount != 0 && mApproveCount == 0) {
            approveLineWidth = DisplayUtils.dip2px(context, 1);
            oppositeLineWidth = width - approveLineWidth;
        } else {
            oppositeLineWidth = width / 2;
            approveLineWidth = width / 2;
        }
        float end_down_cx = Start_down_cx + oppositeLineWidth;
        /**
         * 画 第二个圆弧
         */
        float start_up_cx = end_down_cx + DisplayUtils.dip2px(context, 8);
        canvas.drawBitmap(mOppositeBitmap, marginLeft_16dp, marginTop_12dp, BitmapPaint);
        canvas.drawBitmap(mApproveBitmap, getWidth() - marginLeft_16dp - mApproveBitmap.getWidth(), marginTop_12dp, BitmapPaint);


        canvas.drawLine(Start_down_cx, Start_down_cy, end_down_cx, Start_down_cy, mOppositePaint);

        canvas.drawLine(start_up_cx, end_down_cy, end_up_cx, end_down_cy, mApprovePaint);
        /**
         * 画 第四个圆弧
         */
        mOppositePaint.setTextSize(DisplayUtils.dip2px(context, 12));
        canvas.drawText(String.valueOf(mOppostiteCount), Start_down_cx, Start_down_cy + DisplayUtils.dip2px(context, 18), mOppositePaint);
        mApprovePaint.setTextSize(DisplayUtils.dip2px(context, 12));
        canvas.drawText(String.valueOf(mApproveCount), end_up_cx - DisplayUtils.calcTextWidth(mApprovePaint, String.valueOf(mApproveCount)), Start_down_cy + DisplayUtils.dip2px(context, 18), mApprovePaint);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthSpecMode ==MeasureSpec.AT_MOST&&heightSpecMode ==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthSpecMode ==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightMeasureSpec);
        }else if(heightSpecMode ==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSpec,200);
        }
    }

}
