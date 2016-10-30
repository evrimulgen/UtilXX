package com.xuexiang.view.guideview;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;

/**
 * ��湤����
 * @since 2014/11/19
 */
public final class MeasureUtil {
	public static final int RATION_WIDTH = 0;
	public static final int RATION_HEIGHT = 1;
	
	/**
	 * ��ȡ��Ļ�ߴ�
	 * 
	 * @param activity
	 *            Activity
	 * @return ��Ļ�ߴ�����ֵ���±�Ϊ0��ֵΪ���±�Ϊ1��ֵΪ��
	 */
	public static int[] getScreenSize(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return new int[] { metrics.widthPixels, metrics.heightPixels };
	}

	/**
	 * �Զ���ؼ���ȡ������ĳߴ緽��
	 * 
	 * @param measureSpec
	 *            �������
	 * @param ratio
	 *            ��߱�ʶ
	 * @param mStr 
	 * 			  �Զ���ؼ�����Ҫ��������
	 * @param mBitmap
	 * 			  �Զ���ؼ�����Ҫ����ͼƬ
	 * @param paddings
	 * 			 �Զ���ؼ���paddingֵint[]{left,top,right,bottom}
	 * @return ���ߵĲ���ֵ
	 */
	public static int getMeasureSize(int measureSpec, int ratio,String mStr,Bitmap mBitmap,Paint mPaint,int[] paddings) {
		// ������ʱ�����������ֵ
		int result = 0;
		/*
		 * ��ȡ����mode��size
		 */
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		/*
		 * �ж�mode�ľ���ֵ
		 */
		switch (mode) {
		case MeasureSpec.EXACTLY:// EXACTLYʱֱ�Ӹ�ֵ
			result = size;
			break;
		default:// Ĭ������½�UNSPECIFIED��AT_MOSTһ������
			if (ratio == RATION_WIDTH) {
				if(mStr!=null&&mBitmap!=null){
					float textWidth = mPaint.measureText(mStr);
					result = ((int)(textWidth >= mBitmap.getWidth() ? textWidth : mBitmap.getWidth())) ;
				}else if(mBitmap!=null){
					result = mBitmap.getWidth();
				}else if(mStr!=null){
					result = (int) mPaint.measureText(mStr);
				}
				if(paddings!=null){
					result+=( paddings[0] + paddings[2]);
				}
			} else if (ratio == RATION_HEIGHT) {
				if(mStr!=null&&mBitmap!=null){
					result = ((int) ((mPaint.descent() - mPaint.ascent()) * 2 + mBitmap.getHeight()));
				}else if(mBitmap!=null){
					result = mBitmap.getHeight();
				}else if(mStr!=null){
					result = (int) ((mPaint.descent() - mPaint.ascent()) * 2);
				}
				if(paddings!=null){
					result+=( paddings[1] + paddings[3]);
				}
			}

			/*
			 * AT_MOSTʱ�ж�size��result�Ĵ�СȡСֵ
			 */
			if (mode == MeasureSpec.AT_MOST) {
				result = Math.min(result, size);
			}
			break;
		}
		return result;
	}
	public static Bitmap changeColor(Bitmap src, int keyColor, int replColor, int tolerance) {
		Bitmap copy = src.copy(Bitmap.Config.ARGB_8888, true);
		int width = copy.getWidth();
		int height = copy.getHeight();
		int[] pixels = new int[width * height];
		src.getPixels(pixels, 0, width, 0, 0, width, height);
		int sR = Color.red(keyColor);
		int sG = Color.green(keyColor);
		int sB = Color.blue(keyColor);
		int tR = Color.red(replColor);
		int tG = Color.green(replColor);
		int tB = Color.blue(replColor);
		float[] hsv = new float[3];
		Color.RGBToHSV(tR, tG, tB, hsv);
		float targetHue = hsv[0];
		float targetSat = hsv[1];
		float targetVal = hsv[2];

		for (int i = 0; i < pixels.length; ++i) {
			int pixel = pixels[i];

			if (pixel == keyColor) {
				pixels[i] = replColor;
			} else {
				int pR = Color.red(pixel);
				int pG = Color.green(pixel);
				int pB = Color.blue(pixel);

				int deltaR = Math.abs(pR - sR);
				int deltaG = Math.abs(pG - sG);
				int deltaB = Math.abs(pB - sB);

				if (deltaR <= tolerance && deltaG <= tolerance && deltaB <= tolerance) {
					Color.RGBToHSV(pR, pG, pB, hsv);
					hsv[0] = targetHue;
					hsv[1] = targetSat;
					hsv[2] *= targetVal;

					int mixTrgColor = Color.HSVToColor(Color.alpha(pixel), hsv);
					pixels[i] = mixTrgColor;
				}
			}
		}

		copy.setPixels(pixels, 0, width, 0, 0, width, height);

		return copy;
	}


	public static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
		try {
			return Bitmap.createBitmap(width, height, config);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			if (retryCount > 0) {
				System.gc();
				return createBitmapSafely(width, height, config, retryCount - 1);
			}
			return null;
		}
	}
	
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
	
	/**
     * ����״̬���߶ȸ߶�
     * getStatusBarHeight
     * @return
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
