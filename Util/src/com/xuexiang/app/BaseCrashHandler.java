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
package com.xuexiang.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;

import com.xuexiang.util.data.DateUtils;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.log.LogHelper;
import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.view.DialogUtil;

/**
 * ��Application��ͳһ�����쳣�����浽�ļ����´��ٴ�ʱ�ϴ�
 *
 * @author jingle1267@163.com
 */
public class BaseCrashHandler implements UncaughtExceptionHandler {

    /**
     * �Ƿ�����־���,��Debug״̬�¿���, ��Release״̬�¹ر�����ʾ��������
     */
    public static final boolean DEBUG = true;

    /**
     * ϵͳĬ�ϵ�UncaughtException������
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandlerʵ��
     */
    private static BaseCrashHandler INSTANCE;

    private Context mContext;
    
    /**
     * �Դ������ʱ��
     */
    private int mHandlerTime = 60;  //Ĭ��һ����

    /**
     * ��ֻ֤��һ��CrashHandlerʵ��
     */
    private BaseCrashHandler() {
    }

	public void setHandlerTime(int handlerTime) {
		mHandlerTime = handlerTime;
	}

	/**
     * ��ȡCrashHandlerʵ�� ,����ģʽ
     */
    public static BaseCrashHandler getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new BaseCrashHandler();
        }
        return INSTANCE;
    }

    /**
     * ��ʼ��,ע��Context����, ��ȡϵͳĬ�ϵ�UncaughtException������, ���ø�CrashHandlerΪ�����Ĭ�ϴ�����
     *
     * @param context ������
     */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * ��UncaughtException����ʱ��ת��ú���������
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
    	LogUtils.e("BaseCrashHandler���ڴ���");
        if (!handleException(ex) && mDefaultHandler != null) {
            // ����û�û�д�������ϵͳĬ�ϵ��쳣������������
            mDefaultHandler.uncaughtException(thread, ex);
        } else { // ����Լ��������쳣���򲻻ᵯ������Ի�������Ҫ�ֶ��˳�app
            try {
                Thread.sleep(mHandlerTime * 1000);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }

    }
    
    

	/**
	 * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.
	 * 
	 * @param ex
	 * @return true:��������˸��쳣��Ϣ;���򷵻�false.
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null || mContext == null)
			return false;
		final String crashReport = getCrashReport(mContext, ex);
		// ʹ��Toast����ʾ�쳣��Ϣ
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				File file = saveCrashLogToFile(ex);
				sendAppCrashReport(mContext, crashReport, file);
				Looper.loop();
			}
		}.start();
		return true;
	}
	
	/**
	 * ��ȡAPP�����쳣����
	 * 
	 * @param ex
	 * @return
	 */
	private String getCrashReport(Context context, Throwable ex) {
		PackageInfo pinfo = getPackageInfo(context);
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version: " + pinfo.versionName + "("
				+ pinfo.versionCode + ")\n");
		exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE
				+ "(" + android.os.Build.MODEL + ")\n");
		exceptionStr.append("Exception: " + ex.getMessage() + "\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString() + "\n");
		}
		return exceptionStr.toString();
	}
	
	/**
	 * ��ȡApp��װ����Ϣ
	 * 
	 * @return
	 */
	private PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			 e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}
    
    /**
	 * ����������ʱ����־
	 * 
	 * @param paramThrowable
	 */
	private File saveCrashLogToFile(Throwable paramThrowable) {
		String CRASH_LOG_PATH = LocalFileUtil.LOG_DIR
				+ "crash_log/";
		File file = new File(CRASH_LOG_PATH + "crash_log_"
				+ DateUtils.formatDate(new Date(), DateUtils.yyyyMMddHHmmss) + ".txt");
		if (file.getParentFile().exists() == false) {
			file.getParentFile().mkdirs();
		}
		
		PrintStream ps = null;
		try {
			ps = new PrintStream(file);
			ps.write(LogHelper.getDevicesInfo(mContext).getBytes());
			paramThrowable.printStackTrace(ps);
			ps.flush();
			return file;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			LogHelper.saveExceptionStackInfo(e1);
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.saveExceptionStackInfo(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
		return null;
	}
	
	/**
	 * ��ʾ������ϸ��Ϣ
	 * @param crashFile
	 */
	private void showCrashDetail(Context context, File crashFile) {
		AlertDialog dialog = DialogUtil.getConfirmDialog(context, "��������", FileUtils.readFile(crashFile.getPath(), "UTF-8").toString(), new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// �˳�
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
			}});
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
	}
	
	private void sendAppCrashReport(final Context context,
			final String crashReport, final File file) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setTitle("Ӧ�ó������")
		.setMessage("�ܱ�Ǹ��Ӧ�ó�����ִ��󣬼����˳���\n��ѡ���ʼ��ķ�ʽ���˴��󱨸��ύ���ң��һᾡ���޸�������⣬лл��")
		.setPositiveButton("�ύ����",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						try {

							//�����µ����ݣ�ֻ���ο�����Ϊû�з�����
							Intent intent = new Intent(Intent.ACTION_SEND);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							String[] tos = { "xuexiangandroid@163.com"};
							String[] ccs = { "xuexiangjys2012@gmail.com"};
							intent.putExtra(Intent.EXTRA_EMAIL, tos); //�ռ���
							intent.putExtra(Intent.EXTRA_CC, ccs);  //������
							intent.putExtra(Intent.EXTRA_BCC, ccs);  //������

							intent.putExtra(Intent.EXTRA_SUBJECT,
									"Android�ͻ��� - ���󱨸�");
							if (file != null) {
								intent.putExtra(Intent.EXTRA_STREAM,
										Uri.fromFile(file));
								intent.putExtra(Intent.EXTRA_TEXT,
										"�뽫�˴��󱨸淢�͸��ң��Ա��Ҿ����޸������⣬лл������\n");
							} else {
								intent.putExtra(Intent.EXTRA_TEXT,
										"�뽫�˴��󱨸淢�͸��ң��Ա��Ҿ����޸������⣬лл������\n"
												+ crashReport);
							}
							intent.setType("text/plain");
							intent.setType("message/rfc882");
							Intent.createChooser(intent, "Choose Email Client");
							context.startActivity(intent);
							
						} catch (Exception e) {
							Log.i("Show","error:" + e.getMessage());
						} finally {
							dialog.dismiss();
							// �˳�
							android.os.Process.killProcess(android.os.Process.myPid());
							System.exit(1);
						}
					}
				})
		.setNeutralButton("�鿴����", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showCrashDetail(context, file);
			}
		})
		.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// �˳�
						android.os.Process.killProcess(android.os.Process.myPid());
						System.exit(1);
					}
				});
		
		AlertDialog dialog = builder.create();
		//��Ҫ�Ĵ��ھ����ʽ��û�����ᱨ���
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
	}


    /**
     * ��ȡ�ֻ���Ϣ
     *
     * @return
     */
    public String getPhoneInfo() {
        String phoneInfo = "Product: " + android.os.Build.PRODUCT;
        phoneInfo += ", CPU_ABI: " + android.os.Build.CPU_ABI;
        phoneInfo += ", TAGS: " + android.os.Build.TAGS;
        phoneInfo += ", VERSION_CODES.BASE: "
                + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += ", MODEL: " + android.os.Build.MODEL;
        phoneInfo += ", SDK: " + android.os.Build.VERSION.SDK_INT;
        phoneInfo += ", VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += ", DEVICE: " + android.os.Build.DEVICE;
        phoneInfo += ", DISPLAY: " + android.os.Build.DISPLAY;
        phoneInfo += ", BRAND: " + android.os.Build.BRAND;
        phoneInfo += ", BOARD: " + android.os.Build.BOARD;
        phoneInfo += ", FINGERPRINT: " + android.os.Build.FINGERPRINT;
        phoneInfo += ", ID: " + android.os.Build.ID;
        phoneInfo += ", MANUFACTURER: " + android.os.Build.MANUFACTURER;
        phoneInfo += ", USER: " + android.os.Build.USER;
        return phoneInfo;
    }
}