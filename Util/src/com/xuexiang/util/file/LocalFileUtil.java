package com.xuexiang.util.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Environment;
import android.util.Base64;

/**
 * �����ļ���IO������
 *
 */
public class LocalFileUtil {

	//��Ŀ¼
	public static final String LOCAL_DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
			+ "/myutil/";
	
	//��־Ŀ¼
    public static final String LOG_DIR = LOCAL_DATA_PATH
				+ "/logs/";
    
	//���ݿ�Ŀ¼
    public static final String DATABASE_PATH = LOCAL_DATA_PATH
				+ "/database/";
		
	//ͷ��·��
	public static final String HEAD_PHOTO_DIR = LOCAL_DATA_PATH
			+ "headphoto/";
	
	//ͼƬ�ղر���·��
	public static final String PICTURE_COLLECT_DIR = LOCAL_DATA_PATH
			+ "collect/";
	
	//����ͼƬ�����ַ
	public static final String PICTURE_SCREENSHOT_DIR = LOCAL_DATA_PATH
			+ "screenshot/";
	
	/* ���ذ���װ·�� */
	public static final String APK_PATH = LOCAL_DATA_PATH + "apk/";
	
	/* �����ļ���·�� */
	public static final String DOWNLOAD_PATH = LOCAL_DATA_PATH + "download/";
	
	
	public static boolean existsSDCard() {
		if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {// ���SD������
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ȡ�����ļ�������
	 * 
	 * @param file
	 * @return
	 */
	public static String getLocalDataByFile(File fileName) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			byte buf[] = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			out.close();

		} catch (IOException e) {

		}

		return out.toString();
	}

	/**
	 * ��ȡ�ֽ��ļ�
	 * 
	 * @param pathName
	 * @return
	 */
	public static byte[] getByteForFile(File inFile) throws IOException{
		FileInputStream in= new FileInputStream(inFile);
		byte[] buf = new byte[in.available()];
		in.read(buf);

		in.close();

		return buf;
	}

	/**
	 * �����ļ�������·��file��
	 * 
	 * @param file
	 *            �����·��
	 * @param str
	 *            Ҫ������ַ���
	 * @return
	 */
	public static boolean downFile(File fileName, String str) {
		if (!fileName.getParentFile().exists()) {
			fileName.getParentFile().mkdirs();
		}

		boolean res = true;
		byte[] content = str.getBytes();
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			out.write(content);
			out.close();
		} catch (IOException e) {
			res = false;
			e.printStackTrace();
		}

		return res;
	}

	public static boolean exisFile(File file) {
		boolean exis = true;
		if (!file.exists()) {// ��������ļ������ڣ�������

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			exis = false;
		}

		return exis;
	}

	
	/**
	 * ��һ��Ŀ¼��ɾ��ĳ���ļ�
	 * @param file
	 * @param cmd ����
	 */
	public static boolean deleteFile(String path,String cmd){
		boolean res=false;
		
		File file=new File(path,cmd);
		if(file.exists()){
			res=file.delete();
		}
		
		return res;
	}
	
	/**
	 * ɾ������Ŀ¼
	 * @param file
	 * @return
	 */
	public static void deleteFile(File file){
		
		if(file.exists()){
			if(file.listFiles()!=null){
				for(File f:file.listFiles()){
					f.delete();
				}
			}
			file.delete();
		}
		
	}
	
	/**
	 * ɾ��Ŀ¼�е��ļ�
	 * @param path
	 */
	public static void deleteDirectory(String path){
		File dir=new File(path);
		if(dir.exists()){
			if(dir.listFiles()!=null){
				for(File f:dir.listFiles()){
					if(f.isDirectory()){
						deleteFile(f);
					}else if(f.isFile())
						f.delete();
				}
			}
			
			dir.delete();
		}
		
	}
	

	
	/**
	 * ��ȡ�����ļ���json�ļ�
	 * @param path �ļ�·��
	 * @return
	 */
	public static String getSingleFileData(String path){
		File fil=new File(path); 
		String json=getLocalDataByFile(fil);
		
		return json;
	}
	
	/**
	 * ��һ���ļ�����Base64����
	 * @param file
	 * @return
	 */
	public static String encodeToString(File file){
		StringBuffer sb=new StringBuffer();
		if(file.exists()){
			FileInputStream is=null;
			byte[] buffer=new byte[1024];
			try {
				is=new FileInputStream(file);
				int c=0;
				while((c=is.read(buffer))!=-1){
					sb.append(Base64.encodeToString(buffer, 0, c, Base64.DEFAULT));
				}
				
				is.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * ��ȡһ��Ŀ¼���ʱ��û�б��Ķ����ļ�
	 * @param file
	 */
	public static File findOldFile(File file){
		File oldFile = file.listFiles()[0];
		long modifiedTime = file.listFiles()[0].lastModified();
		for(File f:file.listFiles()){//�ҳ��޸�ʱ����С���ļ�
			if(f.lastModified() < modifiedTime){
				oldFile=f;
			}
			modifiedTime=f.lastModified();
		}
		
		return oldFile;
	}
	
	/**
	 * ɾ��һ��Ŀ¼�е������ļ�
	 * @param dir
	 */
	public static void deleteFileList(File dir){
		if(dir.exists()&&dir.listFiles().length>0){
			for(File f:dir.listFiles()){
				f.delete();
			}
		}
	}
	
	
	 /** �ƶ��ļ�
	 * @param src
	 * @param dest
	 */
	public static boolean moveFile(String src, String dest) {
		File srcFolder = new File(src);
		File destFolder = new File(dest);
		if(!destFolder.exists())
			destFolder.mkdirs();
		File newFile = new File(destFolder.getAbsoluteFile() + "/" + srcFolder.getName());
		boolean res = srcFolder.renameTo(newFile);
		return res;
	}
	
	public static boolean saveFile(String text, String path){
		File file = new File(path);
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		FileOutputStream out = null;
		boolean result = false;
		try {
			out = new FileOutputStream(file);
			out.write(text.getBytes());
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		}
		
		return result;
	}
	
	/**
	 * ��ȡһ���ı�
	 * @param inputStream
	 * @return
	 */
	public static String readTextFile(InputStream inputStream) {

		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {

			reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
			
			String s ="";
			while((s = reader.readLine()) != null){
				sb.append(s + "\r\n");
			}
			

			inputStream.close();
			reader.close();

		} catch (IOException e) {
		}

		return sb.toString();
	}
}
