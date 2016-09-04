package com.xuexiang.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

public class AssetsManager {

	private AssetManager mAssetManager;
	public static AssetsManager sInstance;
	//public static final String PRINT_DATA = "printdata";
	
	private AssetsManager(Context context) {
		mAssetManager = context.getAssets();
	}
	
	public static AssetsManager getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new AssetsManager(c.getApplicationContext());
		}
		return sInstance;
	}
	 
	/**
	 * @param mContext
	 * @param assetpath  asset�µ�·��
	 * @param sdpath     SDpath�±���·��
	 */
	public void copyFileFromAssetToSD(String assetpath,String sdpath ) {
		
		//ѭ���Ķ�ȡasset�µ��ļ�������д�뵽SD��
		String[] filenames = null;
		FileOutputStream out = null;  
		InputStream in = null;
		try {
			filenames = mAssetManager.list(assetpath);
			if (filenames.length > 0) {//˵����Ŀ¼
				//����Ŀ¼
				getDirectory(assetpath);
				
				for(String fileName:filenames){
					copyFileFromAssetToSD(assetpath + "/" + fileName, sdpath + "/" + fileName);
				}
			} else {//˵�����ļ���ֱ�Ӹ��Ƶ�SD��
				File sdFlie = new File(sdpath);
				String  path = assetpath.substring(0, assetpath.lastIndexOf("/"));
				getDirectory(path);
				
				if (!sdFlie.exists()) {
					sdFlie.createNewFile();
				}
				//������д�뵽�ļ���
				in = mAssetManager.open(assetpath);
				out = new FileOutputStream(sdFlie);  
				byte[] buffer = new byte[1024];
				int byteCount=0;
				while ((byteCount = in.read(buffer)) != -1) {
					out.write(buffer, 0, byteCount);
				}
				out.flush();
				out.close();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//�ּ������ļ���
	public void getDirectory(String path){
		//��SDpath���д����ֲ㼶�����ļ���
	    String[] s = path.split("/");
	    String str = Environment.getExternalStorageDirectory().toString();
	      for (int i = 0; i < s.length; i++) {
	        str = str + "/" + s[i];
	        File file=new File(str);
			if(!file.exists()){
				file.mkdir();
			}
		}
		
	}
	

	public static boolean isDirectoryExist(String path){
		File file = new File(Environment.getExternalStorageDirectory() + "/" + path);
	    if (file.exists()) {
	    	return true;
	    } else {
	    	return false;
	    }
		 
	}
	
	public String getTextFormSrc(String name){
		String str = null;
		
		try {
			InputStream is = mAssetManager.open(name);
			str = streamToString(is);
		} catch (IOException e) {
			Log.d("xuexiang", "��ȡ�ı��쳣��"+e.getMessage());
		}
		return str;
	}

	
	/**
	 * ��������ת����string
	 * @param is
	 * @return
	 */
	public String streamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is),
				40 * 1024);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

}
