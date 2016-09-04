package com.xuexiang.util.net.downloadfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;

import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.net.uploadfile.HttpClientUtil.ProgressListener;

/**
 * �ļ�������
 * 
 * @author xx
 */
public class FileDownloadThread extends Thread {

	/** ���ص��ļ��� */
	private String mFileName;
	/** �ļ�����·�� */
	private String mDownloadUrl;
	/** ������*/
	private long transferred = 0;
	/** ���ؽ���*/
	private int progress = 0;
	
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	
	private ProgressListener mProgressListener;	
	private DownLoadFinishedListener mDownLoadFinishedListener;
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgressListener.cumulative(transferred);
				mProgressListener.progress(progress);
				break;
			case DOWN_OVER:
				mDownLoadFinishedListener.onFinish(transferred);
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 
	 * @param downloadUrl:�ļ����ص�ַ
	 * @param fileName:�����ļ���
	 * @param listener:�������ݼ���
	 */
	public FileDownloadThread(String downloadUrl, String fileName, ProgressListener progressListener, DownLoadFinishedListener downLoadFinishedListener) {
		mDownloadUrl = downloadUrl;
		mFileName = LocalFileUtil.DOWNLOAD_PATH + fileName;
		mProgressListener = progressListener;
		mDownLoadFinishedListener = downLoadFinishedListener;
	}

	@Override
	public void run() {
		try {
			URL url = new URL(mDownloadUrl);
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.connect();
			int length = conn.getContentLength();
			InputStream is = conn.getInputStream();

			File file = new File(LocalFileUtil.DOWNLOAD_PATH);
			if (!file.exists()) {
				file.mkdir();
			}
			File downLoadFile = new File(mFileName);
			FileOutputStream fos = new FileOutputStream(downLoadFile);
			byte buf[] = new byte[8192];
			Timer mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					mHandler.sendEmptyMessage(DOWN_UPDATE);
				}
			}, 0, 100);

			do {
				int numread = is.read(buf);
				transferred += numread;
				progress = (int) (((float) transferred / length) * 100);
				// ���½���
				if (numread <= 0) {
					// �������֪ͨ��װ
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					mHandler.sendEmptyMessage(DOWN_OVER);
					mTimer.cancel();
					break;
				}
				fos.write(buf, 0, numread);
			} while (true);// ���ȡ����ֹͣ����.
			mTimer.cancel();
			fos.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ļ�������ϵ��¼�����
	 */
	public interface DownLoadFinishedListener {
		void onFinish(long totalSize);
	}
	

}
