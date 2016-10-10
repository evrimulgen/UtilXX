package com.xuexiang.view;

import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * ��ʾ�����������ؼ�
 * Created by CaptionDeng on 2016/8/30.
 */
public class StarBarView extends View {
    //����ˮƽ����
    public static final int HORIZONTAL = 0;
    //���Ǵ�ֱ����
    public static final int VERTICAL = 1;
    //ʵ��ͼƬ
    private Bitmap mSolidBitmap;
    //����ͼƬ
    private Bitmap mHollowBitmap;
    //��������
    private int starMaxNumber;
    private float starRating;
    private Paint paint;
    private int mSpaceWidth;//���Ǽ��
    private int mStarWidth;//���ǿ��
    private int mStarHeight;//���Ǹ߶�
    private boolean isIndicator;//�Ƿ���һ��ָʾ�����û��޷����и��ģ�
    private int mOrientation;

    public StarBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, RUtils.getStyleable(context, "StarBarView"), defStyle, 0);
        mSpaceWidth = a.getDimensionPixelSize(RUtils.getStyleableAttribute(context, "StarBarView_space_width"), 0);
        mStarWidth = a.getDimensionPixelSize(RUtils.getStyleableAttribute(context, "StarBarView_star_width"), 0);
        mStarHeight = a.getDimensionPixelSize(RUtils.getStyleableAttribute(context, "StarBarView_star_height"), 0);
        starMaxNumber = a.getInt(RUtils.getStyleableAttribute(context, "StarBarView_star_max"), 0);
        starRating = a.getFloat(RUtils.getStyleableAttribute(context, "StarBarView_star_rating"), 0);
        mSolidBitmap = getZoomBitmap(BitmapFactory.decodeResource(context.getResources(), a.getResourceId(RUtils.getStyleableAttribute(context, "StarBarView_star_solid"), 0)));
        mHollowBitmap = getZoomBitmap(BitmapFactory.decodeResource(context.getResources(), a.getResourceId(RUtils.getStyleableAttribute(context, "StarBarView_star_hollow"), 0)));
        mOrientation = a.getInt(RUtils.getStyleableAttribute(context, "StarBarView_star_orientation"), HORIZONTAL);
        isIndicator = a.getBoolean(RUtils.getStyleableAttribute(context, "StarBarView_star_isIndicator"), false);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mHollowBitmap == null || mSolidBitmap == null) {
            return;
        }
        //����ʵ�Ľ���
        int solidStarNum = (int) starRating;
        //����ʵ�ĵ����λ��
        int solidStartPoint = 0;
        if (mOrientation == HORIZONTAL)
            for (int i = 1; i <= solidStarNum; i++) {
                canvas.drawBitmap(mSolidBitmap, solidStartPoint, 0, paint);
                solidStartPoint = solidStartPoint + mSpaceWidth + mSolidBitmap.getWidth();
            }
        else
            for (int i = 1; i <= solidStarNum; i++) {
                canvas.drawBitmap(mSolidBitmap, 0, solidStartPoint, paint);
                solidStartPoint = solidStartPoint + mSpaceWidth + mSolidBitmap.getHeight();
            }
        //���Ŀ�ʼλ��
        int hollowStartPoint = solidStartPoint;
        //�����ʵ�Ĳ������
        int extraSolidStarPoint = hollowStartPoint;
        //��������
        int hollowStarNum = starMaxNumber - solidStarNum;
        if (mOrientation == HORIZONTAL)
            for (int j = 1; j <= hollowStarNum; j++) {
                canvas.drawBitmap(mHollowBitmap, hollowStartPoint, 0, paint);
                hollowStartPoint = hollowStartPoint + mSpaceWidth + mHollowBitmap.getWidth();
            }
        else
            for (int j = 1; j <= hollowStarNum; j++) {
                canvas.drawBitmap(mHollowBitmap, 0, hollowStartPoint, paint);
                hollowStartPoint = hollowStartPoint + mSpaceWidth + mHollowBitmap.getWidth();
            }
        //�����ʵ�ĳ���
        int extraSolidLength = (int) ((starRating - solidStarNum) * mHollowBitmap.getWidth());
        Rect rectSrc = new Rect(0, 0, extraSolidLength, mHollowBitmap.getHeight());
        Rect dstF = new Rect(extraSolidStarPoint, 0, extraSolidStarPoint + extraSolidLength, mHollowBitmap.getHeight());
        canvas.drawBitmap(mSolidBitmap, rectSrc, dstF, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isIndicator) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mOrientation == HORIZONTAL) {
                        float TotalWidth = starMaxNumber * (mStarWidth + mSpaceWidth);
                        if (event.getX() <= TotalWidth) {
                            float newStarRating = (int) event.getX() / (mStarWidth + mSpaceWidth) + 1;
                            setStarRating(newStarRating);
                        }
                    }else{
                        float TotalHeight = starMaxNumber * (mStarHeight + mSpaceWidth);
                        if (event.getY() <= TotalHeight) {
                            float newStarRating = (int) event.getY() / (mStarHeight + mSpaceWidth) + 1;
                            setStarRating(newStarRating);
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
//                    float starTotalWidth = starMaxNumber * (mStarWidth + mSpaceWidth);
//                    if (event.getX() <= starTotalWidth) {
//                        float newStarRating = (int) event.getX() / (mStarWidth + mSpaceWidth) + 1;
//                    setStarRating(newStarRating);
//                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * �������ǵĽ���
     *
     * @param starRating
     */
    public void setStarRating(float starRating) {
        this.starRating = starRating;
        invalidate();
    }

    public float getStarRating() {
        return starRating;
    }


    /**
     * ��ȡ����ͼƬ
     *
     * @param bitmap
     * @return
     */
    public Bitmap getZoomBitmap(Bitmap bitmap) {
        if (mStarWidth == 0 || mStarHeight == 0) {
            return bitmap;
        }
        // ���ͼƬ�Ŀ��
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // ������Ҫ�Ĵ�С
        int newWidth = mStarWidth;
        int newHeight = mStarHeight;
        // �������ű���
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // ȡ����Ҫ���ŵ�matrix����
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // �õ��µ�ͼƬ
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbm;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOrientation == HORIZONTAL) {
            //�ж��Ǻ��������򣬲�������
            setMeasuredDimension(measureLong(widthMeasureSpec), measureShort(heightMeasureSpec));
        } else {
            setMeasuredDimension(measureShort(widthMeasureSpec), measureLong(heightMeasureSpec));
        }
    }

    private int measureLong(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if ((specMode == MeasureSpec.EXACTLY)) {
            result = specSize;
        } else {
            result = (int) (getPaddingLeft() + getPaddingRight() + (mSpaceWidth + mStarWidth) * (starMaxNumber));
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureShort(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (mStarHeight + getPaddingTop() + getPaddingBottom());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public int getStarMaxNumber() {
        return starMaxNumber;
    }

    public void setStarMaxNumber(int starMaxNumber) {
        this.starMaxNumber = starMaxNumber;
        //����invalidate()��ˢ�½���
        invalidate();
    }

    public boolean isIndicator() {
        return isIndicator;
    }

    public void setIsIndicator(boolean isIndicator) {
        this.isIndicator = isIndicator;
    }
}
