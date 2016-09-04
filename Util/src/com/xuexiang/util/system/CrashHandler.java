package com.xuexiang.util.system;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;

import com.xuexiang.util.data.DateUtils;
import com.xuexiang.util.file.LocalFileUtil;

/**
 * UncaughtException������,��������Uncaught�쳣��ʱ��,�ɸ������ӹܳ���,����¼���ʹ��󱨸�.
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {
	// ϵͳĬ�ϵ�UncaughtException������
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	// CrashHandlerʵ��
	private static CrashHandler INSTANCE;
	// �����Context����
	private Context mContext;

	//��ֻ֤��һ��CrashHandlerʵ�� 
	private CrashHandler() {

	}

	//��ȡCrashHandlerʵ�� ,����ģʽ
	public static CrashHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashHandler();
		return INSTANCE;
	}

	/**
	 * ��ʼ��
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		// ��ȡϵͳĬ�ϵ�UncaughtException������
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// ���ø�CrashHandlerΪ�����Ĭ�ϴ�����
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * ��UncaughtException����ʱ��ת�����д�ķ���������
	 */
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// ����Զ����û�д�������ϵͳĬ�ϵ��쳣������������
			mDefaultHandler.uncaughtException(thread, ex);
		}
	}

	/**
	 * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.
	 * 
	 * @param ex
	 *            �쳣��Ϣ
	 * @return true ��������˸��쳣��Ϣ;���򷵻�false.
	 */
	public boolean handleException(Throwable ex) {
		if (ex == null || mContext == null)
			return false;
		final String crashReport = getCrashReport(mContext, ex);
		new Thread() {
			public void run() {
				Looper.prepare();
				File file = save2File(crashReport);
				sendAppCrashReport(mContext, crashReport, file);
				Looper.loop();
			}

		}.start();
		return true;
	}

	@SuppressLint("SimpleDateFormat")
	private File save2File(String crashReport) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				String CRASH_LOG_PATH = LocalFileUtil.LOG_DIR
						+ "crash_log/";
				File file = new File(CRASH_LOG_PATH + "crash_log_"
						+ DateUtils.formatDate(new Date(), DateUtils.yyyyMMddHHmmss) + ".txt");
				if (file.getParentFile().exists() == false) {
					file.getParentFile().mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(crashReport.toString().getBytes());
				fos.close();
				return file;
			} catch (Exception e) {
				//sd���洢���ǵü���Ȩ�ޣ���Ȼ������׳��쳣
				Log.i("Show","save2File error:" + e.getMessage());
			}
		}
		return null;
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
							String[] tos = { "way.ping.li@gmail.com" };
							intent.putExtra(Intent.EXTRA_EMAIL, tos);

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

}