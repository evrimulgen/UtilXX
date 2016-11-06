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

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * ϵͳ��ʾ��ع�����
 *
 * @author jingle1267@163.com
 */
public final class DisplayUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private DisplayUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����)
     *
     * @param context ������
     * @param dpValue �ߴ�dip
     * @return ����ֵ
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp
     *
     * @param context ������
     * @param pxValue �ߴ�����
     * @return DIPֵ
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ sp
     *
     * @param context ������
     * @param pxValue �ߴ�����
     * @return SPֵ
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * �����ֻ��ķֱ��ʴ� sp �ĵ�λ ת��Ϊ px
     *
     * @param context ������
     * @param spValue SPֵ
     * @return ����ֵ
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * ��ȡdialog���
     *
     * @param activity Activity
     * @return Dialog���
     */
    public static int getDialogW(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
        return w;
    }

    /**
     * ��ȡ��Ļ���
     *
     * @param activity Activity
     * @return ��Ļ���
     */
    public static int getScreenW(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth();
        return w;
    }

    /**
     * ��ȡ��Ļ�߶�
     *
     * @param activity Activity
     * @return ��Ļ�߶�
     */
    public static int getScreenH(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = activity.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return h;
    }
    
    /**
     * ��ȡ��Ļ�߶�
     *
     * @param activity Activity
     * @return ��Ļ�߶�
     */
    public static int getScreenH(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return h;
    }
    
    /**
     * ��ȡ��Ļ�ֱ���
     *
     * @param Context context
     * @return ��Ļ�ֱ���Ļ�߶�
     */
    public static int getScreenDpi(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }
    
    /**
	 * �õ��豸��Ļ�Ŀ��
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * �õ��豸��Ļ�ĸ߶�
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * �õ��豸���ܶ�
	 */
	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

    /**
     * Toggle keyboard If the keyboard is visible,then hidden it,if it's
     * invisible,then show it
     *
     * @param context ������
     */
    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    
    public static int getRelativeTop(View myView) {
	    if(myView.getId() == android.R.id.content)
	        return myView.getTop();
	    else
	        return myView.getTop() + getRelativeTop((View) myView.getParent());
	}
	
	public static int getRelativeLeft(View myView) {
		if(myView.getId() == android.R.id.content)
			return myView.getLeft();
		else
			return myView.getLeft() + getRelativeLeft((View) myView.getParent());
	}

}
