package com.example.testutil.net;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.net.model.HttpConsts;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.app.CacheTools;
import com.xuexiang.util.app.UpdateManager;
import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.log.LogUtils;
import com.xuexiang.util.net.downloadfile.FileDownloadThread;
import com.xuexiang.util.net.downloadfile.FileDownloadThread.DownLoadFinishedListener;
import com.xuexiang.util.net.uploadfile.FileUpload;
import com.xuexiang.util.net.uploadfile.HttpClientUtil.ProgressListener;
import com.xuexiang.util.view.DialogUtil;
import com.xuexiang.view.dialog.HoriztalProgressBarDialog;

public class FileUpLoadAndDownLoadActivity extends BaseActivity implements OnClickListener{
	private EditText path;						//�ļ�·��
	private ProgressBar uploadProgress;			//������
	private TextView progress;					//����
	private Button upload;			
	
	private final int SELECT_FILE = 1;
	
	private HoriztalProgressBarDialog mHoriztalProgressBarDialog;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_upload_download_file);
		initTitleBar(TAG);
		initview();
	}

	private void initview() {
		path = (EditText) findViewById(R.id.filePath);
		upload = (Button) findViewById(R.id.uploadfile);
		uploadProgress = (ProgressBar) findViewById(R.id.uploadProgress);
		progress = (TextView) findViewById(R.id.progress);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.selectfile:
			selectFile();
			break;
        case R.id.uploadfile:
        	upload();
			break;
        case R.id.apkupdate:
        	showNewAppInfo();
			break;
        case R.id.downloadfile:
        	downLoadFile();
			break;
		
		default:
			break;
		}
	}
	
	private void selectFile(){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
	    intent.setType("*/*"); 
	    intent.addCategory(Intent.CATEGORY_OPENABLE);
	    try {
	        startActivityForResult( Intent.createChooser(intent, "ѡ���ϴ��ļ�"), SELECT_FILE);
	    } catch (android.content.ActivityNotFoundException ex) {
	       
	    }
	}
	
	
	/**
	 * Activity�ص�����
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == SELECT_FILE && resultCode == RESULT_OK){
			handlerActivityResult(data);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void upload(){
		String filePath = path.getText().toString().trim();
		if(filePath.length() == 0 ){
			Toast("Please select a file");
			return;
		}
				
		List<File> list = new ArrayList<File>();
		String[] paths = path.getText().toString().split(",");		//���ݶ��Ų���ļ���·��
		for(int i = 0; i < paths.length; i++){
			File file = FileUtils.createNewFile(paths[i]);
			if (file != null) {
				list.add(new File(paths[i]));		//����File
			}								
		}
//		new UpLoadAsyncTask().execute(list);							//�޲����ϴ��ļ�
		
	/**************************************�в����ϴ��ļ�******************************************/
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			params.put("file" + i, list.get(i));
		}
		params.put("content", "�����ϴ��Ĳ���:����HttpClient�ϴ����ļ�");
		new MyUpLoadAsyncTask().execute(params);	
		
	}
	
	
	/**
	 * �����ص��ļ�
	 * @param data
	 */
	private void handlerActivityResult(Intent intent){
		String imagePath = getAbsolutePath(intent.getData());
		path.append(imagePath + ",");
	}
	
	// ȡ������·��
	protected String getAbsolutePath(Uri uri) {
		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, proj, null, null, null); 
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
	
	
	/**
	 * �޲����ļ��ϴ�
	 * @author Tercel
	 *
	 */
	private class UpLoadAsyncTask extends AsyncTask<List<File>, Long, String>{		
        @Override  
        protected void onPreExecute() {  
        	upload.setClickable(false);
        	Toast("��ʼ�ϴ�");
        }
        
        
		@Override
		protected String doInBackground(List<File>... params) {
			ProgressListener listener = new ProgressListener() {	//�ϴ����ȼ�����			
				@Override
				public void cumulative(long num) {
					ShowLog("�ϴ���" + String.valueOf(num));		//�ϴ���
				}
				
				@Override
				public void progress(int progress) {
					ShowLog("�ϴ�����" + String.valueOf(progress));
					publishProgress((long)progress);			//����
				}
			};
			
			try {				
				return FileUpload.post(HttpConsts.UPLPAD_URL, params[0], listener);
			} catch (Exception e) {				
				e.printStackTrace();
				//�쳣�Լ�����
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Long... values) {
			uploadProgress.setProgress((int)(long)values[0]);
			progress.setText(values[0] + "%");
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			upload.setClickable(true);
			Toast(result);
		}
	}
	
	
	/**
	 * �в����ļ��ϴ�
	 * @author Tercel
	 *
	 */
	private class MyUpLoadAsyncTask extends AsyncTask<Map<String, Object>, Long, String>{		
        @Override  
        protected void onPreExecute() {  
        	upload.setClickable(false);
        	Toast("��ʼ�ϴ�");
        }
        
        
		@Override
		protected String doInBackground(Map<String, Object>... params) {
			ProgressListener listener = new ProgressListener() {	//�ϴ����ȼ�����			
				@Override
				public void cumulative(long num) {
					ShowLog("�ϴ���" + String.valueOf(num));		//�ϴ���
				}
				
				@Override
				public void progress(int progress) {
					ShowLog("�ϴ�����" + String.valueOf(progress));
					publishProgress((long)progress);			//����
				}
			};
			
			try {				
				return FileUpload.post(HttpConsts.UPLPAD_URL, params[0], listener);
			} catch (Exception e) {				
				e.printStackTrace();
				//�쳣�Լ�����
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Long... values) {
			uploadProgress.setProgress((int)(long)values[0]);
			progress.setText(values[0] + "%");
		}
		
		
		@Override
		protected void onPostExecute(String result) {
			upload.setClickable(true);
			Toast(result);
		}
	}
	
	
	/**
	 * �汾����
	 */
	private void showNewAppInfo() {
		final String updatemsg = "���°汾Ϊ1.3, �Ƿ���Ҫ���£�\n�����������£�������ά��ģ�顢����ģ�顢���ŷ���ģ�顣";
		DialogUtil.showDialog(mContext,"�����°汾��",
				updatemsg,
				getString(R.string.system_sure),
				getString(R.string.system_cancel) , 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						UpdateManager mUpdateManager = new UpdateManager(mContext, "helper.apk", updatemsg, HttpConsts.APK_UPDATE_URL);
						mUpdateManager.showDownloadDialog();
					}
				}, null, true);	
	}	
	
    /**
     * �����ļ�
     */
    private void downLoadFile() {
    	mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this, "���������ļ���helper.apk");
		mHoriztalProgressBarDialog.show();
		FileDownloadThread download = new FileDownloadThread (HttpConsts.APK_UPDATE_URL, "helper.apk", new ProgressListener(){
			@Override
			public void cumulative(long arg0) {
				LogUtils.d("�������ļ���С��" + arg0);
			}

			@Override
			public void progress(int progress) {
				mHoriztalProgressBarDialog.setProgress(progress);
			}}, new DownLoadFinishedListener(){

			@Override
			public void onFinish(long fileS) {
				Toast("��������ϣ��ļ���С��" + CacheTools.formatFileSize(fileS));
				mHoriztalProgressBarDialog.dismiss();
			}});		
		download.start();
	}


}
