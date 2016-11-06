package com.xuexiang.view.expandtextview;

import java.util.ArrayList;
import java.util.List;

import com.xuexiang.util.resource.RUtils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * ����չ���Ķ�����Textview
 *
 * Created by igeek on 2016/9/1.
 * @author igeek2014@hotmail.com
 */
public class ExpandTextView extends View implements View.OnClickListener {

    //���ı���¼��
    private List<LineText> lineTexts = new ArrayList<LineText>();
    //�����ʾ�ı�����
    private int maxLines;
    //Ŀ���ı���
    private int targetLine;
    //��������ʱ�����ʾͼ��
    private Drawable expandDrawable;
    //չ��ʱ�����ʾͼ��
    private Drawable shrinkDrawable;
    //��ʾͼ��Ŀ��
    private int drawableWidth;
    //��ʾͼ��ĸ߶�
    private int drawableHeight;
    //�����ʾ�ı��ж�Ӧ�ı���ͼ�߶�
    private int maxLinesHeight;
    //չ��ʱ�����ͼ�߶�
    private int expandHeight;
    //��ǰ��ͼ�ĸ߶�
    private int viewHeight;
    //�����н�β��ʾ���ı����
    private float ellipsizWidth;
    //�����н�β��ʾ���ı�����ˮƽ���
    private float ellipsizStartX;

    //�ı������С
    private int textSize;
    //�ı���ɫ
    private int textColor;
    //��ǰ�ı�
    private String text;
    private String ellipsizText = "...";
    //�������ı�
    private String shrinkLineText;
    //������ʾʱ��
    private int animDuration;
    //�Ƿ��ܹ���ʾ ellipsizText ����Ҫ�����е�ǰ�ı��Ŀ�ȡ�
    private boolean showEllipsizText = false;
    private boolean showTipDrawalbe = false;
    private boolean needMeasure = true;

    private StaticLayout layout;
    private TextPaint textPaint;


    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "ExpandTextView"));

        maxLines = ta.getInt(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_maxLines"), -1);
        animDuration = ta.getInt(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_animDuration"), 300);
        textSize = ta.getDimensionPixelSize(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_textSize"), 14);
        textColor = ta.getColor(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_textColor"), 14);
        drawableWidth = ta.getDimensionPixelSize(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_drawableWidth"), 14);
        drawableHeight = ta.getDimensionPixelSize(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_drawableHeight"), 14);
        expandDrawable = ta.getDrawable(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_expandDrawable"));
        shrinkDrawable = ta.getDrawable(RUtils.getStyleableAttribute(context, "ExpandTextView_etv_shrinkDrawable"));

        ta.recycle();

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(textSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);

        if (needMeasure && (!TextUtils.isEmpty(text))) {
            needMeasure = false;
            measureHeightState(text, width);
            startDrawAnim(0, viewHeight);
        }else{
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void updateText(String text) {
        if (!TextUtils.isEmpty(text)) {
            this.text = text;
            needMeasure = true;
            requestLayout();
        }
    }

    private synchronized void measureHeightState(String text, int width) {

        layout = new StaticLayout(text, textPaint, width - getPaddingLeft() - getPaddingRight(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, true);
        final int lineCount = layout.getLineCount();
        maxLines = (maxLines == -1 || maxLines > lineCount) ? lineCount : maxLines;

        int text_Height = 0;

        List<LineText> tempLines = new ArrayList<LineText>();

        for (int index = 0; index < lineCount; index++) {
            int start = layout.getLineStart(index);
            int end = layout.getLineEnd(index);
            LineText lineText = new LineText();
            lineText.setStartIndex(start);
            lineText.setEndIndex(end - 1);
            lineText.setText(text.substring(start, end));
            lineText.setTopOffset(layout.getLineTop(index));
            lineText.setBottomOffset(layout.getLineBottom(index));
            lineText.setBaseLine(layout.getLineBaseline(index)+getPaddingTop());
            lineText.setWidth(layout.getLineWidth(index));
            lineText.setHeight(lineText.getBottomOffset() - lineText.getTopOffset());
            tempLines.add(lineText);

            if (index < maxLines) {
                maxLinesHeight += lineText.getHeight();
            }

            text_Height += lineText.getHeight();
        }

        maxLinesHeight+=getPaddingTop()+getPaddingBottom();
        expandHeight+=getPaddingTop()+getPaddingBottom();

        ellipsizWidth = textPaint.measureText(ellipsizText);

        if (maxLines < lineCount) {

            showTipDrawalbe = expandDrawable != null && shrinkDrawable != null;

            float textWidth = tempLines.get(maxLines - 1).getWidth();
            float contentWidth = width - getPaddingLeft() - getPaddingRight();
            float toMarginRight = ellipsizWidth + (showTipDrawalbe ? drawableWidth : 0);

            String ellipsizLineText = tempLines.get(maxLines - 1).getText();

            if (contentWidth - textWidth < toMarginRight) {
                showEllipsizText = true;
                String subString = null;
                for (int index = ellipsizLineText.length() - 1; index > 0; index--) {
                    subString = ellipsizLineText.substring(0, index);
                    float subStrWidth = textPaint.measureText(subString);
                    if (contentWidth - subStrWidth >= toMarginRight) {
                        ellipsizStartX = subStrWidth + getPaddingLeft();
                        shrinkLineText = subString;
                        break;
                    }
                }
            } else {
                shrinkLineText = ellipsizLineText;
                showEllipsizText = false;
            }
        } else {
            showTipDrawalbe = false;
            showEllipsizText = false;
        }

        expandHeight += text_Height + ((expandDrawable != null && showTipDrawalbe) ? drawableHeight : 0);

        viewHeight = maxLines == lineCount ? expandHeight : maxLinesHeight;

        targetLine = maxLines;

        lineTexts = tempLines;


        if (viewHeight < expandHeight) {
            setClickable(true);
            setOnClickListener(this);
        } else {
            setClickable(false);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lineTexts.size() == 0) return;

        for (int index = 0; index < targetLine; index++) {

            LineText lineText = lineTexts.get(index);

            if (index < targetLine - 1) {
                canvas.drawText(lineText.getText(), getPaddingLeft(), lineText.getBaseLine(), textPaint);
            } else {
                if (targetLine == maxLines && maxLines < lineTexts.size()) {
                    //����ת̬
                    if (showEllipsizText)
                        canvas.drawText(ellipsizText, ellipsizStartX, lineText.getBaseLine(), textPaint);
                    canvas.drawText(shrinkLineText, getPaddingLeft(), lineText.getBaseLine(), textPaint);
                    if (showTipDrawalbe){
                        int left=getWidth() - drawableWidth - getPaddingRight();
                        int top=getHeight() - drawableHeight-getPaddingBottom();
                        canvas.drawBitmap(drawabletoZoomBitmap(shrinkDrawable, drawableWidth, drawableHeight), left, top, null);
                    }
                } else if (targetLine == lineTexts.size()) {
                    //չ��״̬
                    canvas.drawText(lineText.getText(), getPaddingLeft(), lineText.getBaseLine(), textPaint);
                    if (showTipDrawalbe){
                        int left=getWidth() - drawableWidth - getPaddingRight();
                        int top=getHeight() - drawableHeight-getPaddingBottom();
                        canvas.drawBitmap(drawabletoZoomBitmap(expandDrawable, drawableWidth, drawableHeight), left, top, null);
                    }
                }
            }
        }

    }


    @Override
    public void onClick(View view) {

        if (maxLines == lineTexts.size())
            return;

        if (targetLine == maxLines) {
            targetLine = lineTexts.size();
            startDrawAnim(maxLinesHeight, expandHeight);
        } else if (targetLine == lineTexts.size()) {
            targetLine = maxLines;
            startDrawAnim(expandHeight, maxLinesHeight);
        }
    }

    private void startDrawAnim(int startHeight, int endHeight) {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "viewHeight", startHeight, endHeight);
        animator.setDuration(animDuration);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }


    public  int getViewHeight() {
        return viewHeight;
    }

    public  void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
        requestLayout();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * drawlable ����
     *
     * @return
     */
    public static Bitmap drawabletoZoomBitmap(Drawable drawable, int w, int h) {
        // ȡ drawable �ĳ���
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawableת����bitmap
        Bitmap oldbmp = drawabletoBitmap(drawable);
        // ��������ͼƬ�õ�Matrix����
        Matrix matrix = new Matrix();
        // �������ű���
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // �������ű���
        matrix.postScale(sx, sy);
        // �����µ�bitmap���������Ƕ�ԭbitmap�����ź��ͼ
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    /**
     * Drawableת����Bitmap
     */
    public static Bitmap drawabletoBitmap(Drawable drawable) {
        // ȡ drawable �ĳ���
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // ȡ drawable ����ɫ��ʽ
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
