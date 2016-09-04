package com.xuexiang.util.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

/**
 * ���湤��
 * 
 * @author ving
 *
 */
public class CacheTools {
	public final static int MAX_FAILCOUNT = 3; // ���ʧ�ܴ�������������������ץȡ
	public final static String TAG = "CacheTools";

	/**
	 * ����HTTP����
	 * 
	 * @param cacheDir
	 * @param cacheKey
	 * @param cacheValue
	 * @return
	 */
	public static boolean saveHttpCache(String cacheDir, String cacheKey,
			Object cacheValue) {
		boolean flag = false;
		int cnt = 0;
		do {
			try {
				File destDir = new File(cacheDir);
				if (!destDir.exists()) {
					destDir.mkdirs();
				}
				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream(cacheDir + cacheKey));
				os.writeObject(cacheValue);
				os.close();
				flag = true;
				break;
			} catch (Exception e) {
				cnt++;
			}
		} while (cnt < MAX_FAILCOUNT);
		return flag;
	}

	/**
	 * ��ȡ HTTP ����
	 * 
	 * @param cacheDir
	 * @param cacheKey
	 * @return
	 */

	public static Object readHttpCache(String cacheDir, String cacheKey) {
		File file = new File(cacheDir + cacheKey);
		if (!file.exists()) {
			return null;
		}
		Object cacheObj = null;
		int cnt = 0;
		do {
			try {
				ObjectInputStream is = new ObjectInputStream(
						new FileInputStream(file));
				cacheObj = is.readObject();
				is.close();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				cnt++;
			}
		} while (cnt < MAX_FAILCOUNT);
		return cacheObj;
	}

	public static void clearAppCache(Context context) {
		context.deleteDatabase("webview.db");
		context.deleteDatabase("webview.db-shm");
		context.deleteDatabase("webview.db-wal");
		context.deleteDatabase("webviewCache.db");
		context.deleteDatabase("webviewCache.db-shm");
		context.deleteDatabase("webviewCache.db-wal");
		// ������ݻ���
		clearFolder(context.getFilesDir(), System.currentTimeMillis());
		clearFolder(context.getCacheDir(), System.currentTimeMillis());
	}

	/**
	 * ����ļ���Ŀ¼
	 * 
	 * @param dir
	 *            Ŀ¼
	 * @param numDays
	 *            ��ǰϵͳʱ��
	 * @return
	 */
	public static int clearFolder(File dir, long curTime) {
		int cnt = 0;
		int deletedFiles = 0;
		do {
			if (dir != null && dir.isDirectory()) {
				try {
					for (File child : dir.listFiles()) {
						if (child.isDirectory()) {
							deletedFiles += clearFolder(child, curTime);
						}
						if (child.lastModified() < curTime) {
							if (child.delete()) {
								deletedFiles++;
							}
						}
					}
				} catch (Exception e) {
					cnt++;
				}
			}
			return deletedFiles;
		} while (cnt < MAX_FAILCOUNT);

	}

	/**
	 * ���㻺���С
	 * 
	 * @param context
	 * @return
	 */
	public static String getHttpCacheSize(Context context) {
		// ���㻺���С
		long fileSize = 0;
		String cacheSize = "0KB";
		File filesDir = context.getFilesDir();
		File cacheDir = context.getCacheDir();
		fileSize += getDirSize(filesDir);
		fileSize += getDirSize(cacheDir);
//		fileSize += getDirSize(ImageLoader.getInstance().getDiscCache()
//				.getDirectory());

		if (fileSize > 0)
			cacheSize = formatFileSize(fileSize);
		return cacheSize;
	}

	/**
	 * ��ȡĿ¼�ļ���С
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // �ݹ���ü���ͳ��
			}
		}
		return dirSize;
	}

	/**
	 * ת���ļ���С
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
}