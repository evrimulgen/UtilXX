/**
 * Copyright 2014 Zhenguo Jin
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
package com.xuexiang.util.common;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

/**
 * ͨ�ù�����
 *
 * @author jingle1267@163.com
 */
public class CommonUtil {

    /**
     * �Ƿ���SDCard
     *
     * @return �Ƿ���SDCard
     */
    public static boolean hasSDCard() {

        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * ��ȡӦ�����е�����ڴ�
     *
     * @return ����ڴ�
     */
    public static long getMaxMemory() {

        return Runtime.getRuntime().maxMemory() / 1024;
    }
    
    /**
	 * ����Ӧ������ȥӦ���г���ѯ��Ӧ�� 
	 * @param context
	 * @param appName market://search?q=pub:��������
	 */
	public static void searchTingting(Context context,String appName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://search?q=pub:"+ appName +""));
		context.startActivity(intent);
	}
	/**
	 * ����Ӧ�õİ�����ȥӦ���г�������Ӧ��
	 * com.google.android.voicesearch google����
	 * com.snda.tts.service ��������
	 * @param context
	 * @param appPckName
	 */
	public static void searchAppByPkgName(Context context, String appPckName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + appPckName));
		context.startActivity(intent);
	}
	
	public static void call(Context context, String phoneNumber) {
		Intent myIntentDial = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNumber));  
		context.startActivity(myIntentDial); 
	}
	
	
	public static void shareImage(final Context context, File file) {
		if (file != null) {
			Intent intent=new Intent(Intent.ACTION_SEND);  
			intent.setType("image/*");
			Uri u = Uri.fromFile(file);
			intent.putExtra(Intent.EXTRA_STREAM, u);
			context.startActivity(Intent.createChooser(intent, "����"));
		}
	}
	/**
	 *��������ҳ����
	 */
	public static void openSettingNet(Context context) {
        Intent intent = null;
        //�ж��ֻ�ϵͳ�İ汾  ��API����10 ����3.0�����ϰ汾
        if(android.os.Build.VERSION.SDK_INT>10){
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }
	
	public static void openBroswer(Context context, String url) {
		Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));  
        context.startActivity(it); 
	}
	
	public static void openImage(Context context, String path) {
		if (path != null && path.length() > 0 && context != null) {
			Intent intent = new Intent(Intent.ACTION_VIEW);     
			intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
			context.startActivity(intent);
		}
	}
	public static void showInfoDialog(Context context, String message) {
		showInfoDialog(context, message, "��ʾ", "ȷ��", null);
	}

	public static void showInfoDialog(Context context, String message,
			String titleStr, String positiveStr,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}

	public static Bitmap getImageFromAssetsFile(Context ct, String fileName) {
		Bitmap image = null;
		AssetManager am = ct.getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;

	}

	/**
	 * ����ˢ��ʱ��
	 * @param created
	 * @return
	 */
	public static String getUploadtime(long created) {
		StringBuffer when = new StringBuffer();
		int difference_seconds;
		int difference_minutes;
		int difference_hours;
		int difference_days;
		int difference_months;
		long curTime = System.currentTimeMillis();
		difference_months = (int) (((curTime / 2592000) % 12) - ((created / 2592000) % 12));
		if (difference_months > 0) {
			when.append(difference_months + "��");
		}

		difference_days = (int) (((curTime / 86400) % 30) - ((created / 86400) % 30));
		if (difference_days > 0) {
			when.append(difference_days + "��");
		}

		difference_hours = (int) (((curTime / 3600) % 24) - ((created / 3600) % 24));
		if (difference_hours > 0) {
			when.append(difference_hours + "Сʱ");
		}

		difference_minutes = (int) (((curTime / 60) % 60) - ((created / 60) % 60));
		if (difference_minutes > 0) {
			when.append(difference_minutes + "����");
		}

		difference_seconds = (int) ((curTime % 60) - (created % 60));
		if (difference_seconds > 0) {
			when.append(difference_seconds + "��");
		}

		return when.append("ǰ").toString();
	}

}
