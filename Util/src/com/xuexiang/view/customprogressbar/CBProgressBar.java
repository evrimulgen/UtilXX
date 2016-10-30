package com.xuexiang.view.customprogressbar;


import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * �Զ����progressBar
 * @author zhl
 */
public class CBProgressBar extends View {
    private static final int STYLE_HORIZONTAL = 0;
    private static final int STYLE_ROUND = 1;
    private static final int STYLE_SECTOR=2;
    /**���ȱ�������**/
//	private Paint mBgpaint;
    /**���Ȼ���**/
//	private Paint mPrgpaint;
    /**�������ֻ���**/
//	private Paint mTextpaint;
    /**
     * Բ�ν������߿���
     **/
    private int strokeSize=20;
    /**
     * ����������X����
     **/
    private int centerX;
    /**
     * ����������Y����
     **/
    private int centerY;
    /**
     * ������ʾ���ִ�С
     **/
    private int percenttextsize = 18;
    /**
     * ������ʾ������ɫ
     **/
    private int percenttextcolor = 0xff009ACD;
    /**
     * ������������ɫ
     **/
    private int progressBarBgColor = 0xff636363;
    /**
     * ������ɫ
     **/
    private int progressColor = 0xff00C5CD;
    /**
     * ����ɨ����ȵ���ɫ
     */
    private int sectorColor=0xaaffffff;
    /**
     * ����ɨ�豳��
     */
    private int unSweepColor = 0xaa5e5e5e;
    /**
     * ��������ʽ��ˮƽ/Բ�Σ�
     **/
    private int orientation = STYLE_HORIZONTAL;
    /**
     * Բ�ν������뾶
     **/
    private int radius = 30;
    /**
     * �������ֵ
     **/
    private int max = 100;
    /**
     * ����ֵ
     **/
    private int progress = 0;
    /**
     * ˮƽ�������Ƿ��ǿ���
     **/
    private boolean isHorizonStroke;
    /**
     * ˮƽ����Բ��ֵ
     **/
    private int rectRound=5;
    /**���������Ƿ���ʾ�ٷֺ�**/
    private boolean showPercentSign;
    private Paint mPaint;

    public CBProgressBar(Context context) {
        this(context, null);
    }

    public CBProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CBProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "cbprogressbar"));
        percenttextcolor = array.getColor(RUtils.getStyleableAttribute(context, "cbprogressbar_percent_text_color"), percenttextcolor);
        progressBarBgColor = array.getColor(RUtils.getStyleableAttribute(context, "cbprogressbar_progressBarBgColor"), progressBarBgColor);
        progressColor = array.getColor(RUtils.getStyleableAttribute(context, "cbprogressbar_progressColor"), progressColor);
        sectorColor = array.getColor(RUtils.getStyleableAttribute(context, "cbprogressbar_sectorColor"), sectorColor);
        unSweepColor = array.getColor(RUtils.getStyleableAttribute(context, "cbprogressbar_unSweepColor"), unSweepColor);
        percenttextsize = (int) array.getDimension(RUtils.getStyleableAttribute(context, "cbprogressbar_percent_text_size"), percenttextsize);
        strokeSize = (int) array.getDimension(RUtils.getStyleableAttribute(context, "cbprogressbar_stroke_size"), strokeSize);
        rectRound = (int) array.getDimension(RUtils.getStyleableAttribute(context, "cbprogressbar_rect_round"), rectRound);
        orientation = array.getInteger(RUtils.getStyleableAttribute(context, "cbprogressbar_orientation"), STYLE_HORIZONTAL);
        isHorizonStroke = array.getBoolean(RUtils.getStyleableAttribute(context, "cbprogressbar_isHorizonStroke"), false);
        showPercentSign = array.getBoolean(RUtils.getStyleableAttribute(context, "cbprogressbar_showPercentSign"), true);
//		mBgpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		mPrgpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		mTextpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        array.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = centerX - strokeSize / 2;
        if (orientation == STYLE_HORIZONTAL) {
            drawHoriRectProgressBar(canvas, mPaint);
        } else if(orientation == STYLE_ROUND) {
            drawRoundProgressBar(canvas, mPaint);
        }else{
            drawSectorProgressBar(canvas, mPaint);
        }
    }

    /**
     * ����Բ�ν�����
     *
     * @param canvas
     */
    private void drawRoundProgressBar(Canvas canvas, Paint piant) {
        // ��ʼ����������
        piant.setColor(progressBarBgColor);
        piant.setStyle(Paint.Style.STROKE);
        piant.setStrokeWidth(strokeSize);
        // ��Բ
        canvas.drawCircle(centerX, centerY, radius, piant);
        // ��Բ�ν���
        piant.setColor(progressColor);
        piant.setStyle(Paint.Style.STROKE);
        piant.setStrokeWidth(strokeSize);
        RectF oval = new RectF(centerX - radius, centerY - radius, radius + centerX, radius + centerY);
        canvas.drawArc(oval, -90, 360 * progress / max, false, piant);
        // ����������
        piant.setStyle(Paint.Style.FILL);
        piant.setColor(percenttextcolor);
        piant.setTextSize(percenttextsize);

        String percent = (int) (progress * 100 / max) + "%";
        Rect rect = new Rect();
        piant.getTextBounds(percent, 0, percent.length(), rect);
        float textWidth = rect.width();
        float textHeight = rect.height();
        if (textWidth >= radius * 2) {
            textWidth = radius * 2;
        }
        Paint.FontMetrics metrics = piant.getFontMetrics();
	    float baseline = (getMeasuredHeight()-metrics.bottom+metrics.top)/2-metrics.top;
	    canvas.drawText(percent, centerX - textWidth / 2, baseline, piant);

    }

    /**
     * ����ˮƽ���ν�����
     *
     * @param canvas
     */
    private void drawHoriRectProgressBar(Canvas canvas, Paint piant) {
        // ��ʼ����������
        piant.setColor(progressBarBgColor);
        if (isHorizonStroke) {
            piant.setStyle(Paint.Style.STROKE);
            piant.setStrokeWidth(1);
        } else {
            piant.setStyle(Paint.Style.FILL);
        }
        // ��ˮƽ����
        canvas.drawRoundRect(new RectF(centerX - getWidth() / 2, centerY - getHeight() / 2,
                centerX + getWidth() / 2, centerY + getHeight() / 2), rectRound, rectRound, piant);

        // ��ˮƽ����
        piant.setStyle(Paint.Style.FILL);
        piant.setColor(progressColor);
        if(isHorizonStroke){
            canvas.drawRoundRect(new RectF(centerX - getWidth() / 2, centerY - getHeight() / 2,
                    ((progress * 100 / max) * getWidth()) / 100, centerY + getHeight() / 2), rectRound, rectRound, piant);
        }else{
            piant.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawRoundRect(new RectF(centerX - getWidth() / 2, centerY - getHeight() / 2,
                    ((progress * 100 / max) * getWidth()) / 100, centerY + getHeight() / 2),rectRound, rectRound, piant);
            piant.setXfermode(null);
        }

        // ����������
        piant.setStyle(Paint.Style.FILL);
        piant.setColor(percenttextcolor);
        piant.setTextSize(percenttextsize);
        String percent = (int) (progress * 100 / max) + "%";
        Rect rect = new Rect();
        piant.getTextBounds(percent, 0, percent.length(), rect);
        float textWidth = rect.width();
        float textHeight = rect.height();
        if (textWidth >= getWidth()) {
            textWidth = getWidth();
        }
        Paint.FontMetrics metrics = piant.getFontMetrics();
	    float baseline = (getMeasuredHeight()-metrics.bottom+metrics.top)/2-metrics.top;
	    canvas.drawText(percent, centerX - textWidth / 2, baseline, piant);

    }

    /**
     * ��������ɨ��ʽ����
     * @param canvas
     * @param piant
     */
    private void drawSectorProgressBar(Canvas canvas, Paint piant) {
        // ��ʼ����������
        piant.setColor(sectorColor);
        piant.setStyle(Paint.Style.STROKE);
        piant.setStrokeWidth(2);
        // ����Ȧ
        canvas.drawCircle(centerX, centerY, radius, piant);
        // ����Ȧ
        piant.setColor(unSweepColor);
        piant.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius - 2, piant);
        piant.setColor(sectorColor);
        RectF oval = new RectF(centerX - radius+2, centerY - radius+2, radius + centerX-2, radius + centerY-2);
        canvas.drawArc(oval,-90, 360 * progress / max, true, piant);
    }

    public void setProgress(int progress) {
        if (progress > max) {
            progress = max;
        } else {
            this.progress = progress;
			postInvalidate();
        }
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStrokeWidth() {
        return strokeSize;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeSize = strokeWidth;
    }

    public int getPercenttextsize() {
        return percenttextsize;
    }

    public void setPercenttextsize(int percenttextsize) {
        this.percenttextsize = percenttextsize;
    }

    public int getPercenttextcolor() {
        return percenttextcolor;
    }

    public void setPercenttextcolor(int percenttextcolor) {
        this.percenttextcolor = percenttextcolor;
    }

    public int getProgressBarBgColor() {
        return progressBarBgColor;
    }

    public void setProgressBarBgColor(int progressBarBgColor) {
        this.progressBarBgColor = progressBarBgColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public boolean isHorizonStroke() {
        return isHorizonStroke;
    }

    public void setHorizonStroke(boolean isHorizonStroke) {
        this.isHorizonStroke = isHorizonStroke;
    }

    public int getRectRound() {
        return rectRound;
    }

    public void setRectRound(int rectRound) {
        this.rectRound = rectRound;
    }

    public int getMax() {
        return max;
    }

    public int getProgress() {
        return progress;
    }


}
