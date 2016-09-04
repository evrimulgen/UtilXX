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
package com.xuexiang.util.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xuexiang.util.file.FileUtils;
import com.xuexiang.util.file.LocalFileUtil;
import com.xuexiang.util.view.BitmapTools;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * ResourceUtils
 *
 * @author jingle1267@163.com
 */
public final class ResourceUtils {

	 public static final String APK = "apk";
    /**
     * Don't let anyone instantiate this class.
     */
    private ResourceUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * get an asset using ACCESS_STREAMING mode. This provides access to files that have been bundled with an
     * application as assets -- that is, files placed in to the "assets" directory.
     *
     * @param context
     * @param fileName The name of the asset to open. This name can be hierarchical.
     * @return
     */
    public static String geFileFromAssets(Context context, String fileName, boolean isNeedAddLine) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            if(isNeedAddLine) {
              while ((line = br.readLine()) != null) {
            	  s.append(line + "\n");
  	          }
            } else {
	    	  while ((line = br.readLine()) != null) {
	              s.append(line);
	          }
            }
          
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get content from a raw resource. This can only be used with resources whose value is the name of an asset files
     * -- that is, it can be used to open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     *
     * @param context
     * @param resId   The resource identifier to open, as generated by the appt tool.
     * @return
     */
    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * same to {@link ResourceUtils#geFileFromAssets(Context, String)}, but return type is List<String>
     *
     * @param context
     * @param fileName
     * @return
     */
    public static List<String> geFileToListFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            br.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * same to {@link ResourceUtils#geFileFromRaw(Context, int)}, but return type is List<String>
     *
     * @param context
     * @param resId
     * @return
     */
    public static List<String> geFileToListFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
    * ��Assets�ж�ȡͼƬ  
    */  
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {  
       Bitmap image = null;  
       AssetManager am = context.getResources().getAssets();  
       try  {  
           InputStream is = am.open(fileName);  
           image = BitmapFactory.decodeStream(is);  
           is.close();  
       }  catch (IOException e) {  
           e.printStackTrace();  
       }    
       return image;    
   }  
    
    
    /**
     * ��Assets�ж�ȡͼƬ  
     */  
     public static Bitmap getImageFromAssets(Context context, String fileName) {  
        Bitmap image = null;  
        AssetManager am = context.getResources().getAssets();  
        try  {  
            InputStream is = am.open(RUtils.DRAWABLE + "/" + fileName);  
            image = BitmapFactory.decodeStream(is);  
            is.close();  
        }  catch (IOException e) {  
            e.printStackTrace();  
        }    
        return image;    
    }  
     
    /**
     * ��Assets�ж�ȡͼƬ  
     */  
    public static Drawable getImageDrawableFromAssets(Context context, String fileName) {  
        Drawable drawable = null;
        AssetManager am = context.getResources().getAssets();  
        try  {  
            InputStream is = am.open(RUtils.DRAWABLE + "/" + fileName);  
            drawable = BitmapTools.bitmapTodrawable(BitmapFactory.decodeStream(is));
            is.close();  
        }  catch (IOException e) {  
            e.printStackTrace();  
        }    
        return drawable;    
     }  
    
    /** 
     *  ��assetsĿ¼�и��������ļ������� 
     *  @param  context  Context ʹ��CopyFiles���Activity
     *  @param  oldPath  String  ԭ�ļ�·��  �磺/aa 
     *  @param  newPath  String  ���ƺ�·��  �磺xx:/bb/cc 
     */
    public static void copyFilesFromAssets(Context context,String oldPath,String newPath) {                    
           try {
            String fileNames[] = context.getAssets().list(oldPath);//��ȡassetsĿ¼�µ������ļ���Ŀ¼��
            if (fileNames.length > 0) {//�����Ŀ¼
                File file = new File(newPath);
                file.mkdirs();//����ļ��в����ڣ���ݹ�
                for (String fileName : fileNames) {
                   copyFilesFromAssets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {//������ļ�
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount=0;               
                while((byteCount=is.read(buffer))!=-1) {//ѭ������������ȡ buffer�ֽ�        
                    fos.write(buffer, 0, byteCount);//����ȡ��������д�뵽�����
                }
                fos.flush();//ˢ�»�����
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * ��Assets�ж�ȡapk�ļ�  
     */  
     public static boolean copyAPKFromAssets(Context context, String fileName) {  
    	String apkfilepath =  APK + "/" + fileName;
    	if(!FileUtils.isFolderExist(LocalFileUtil.APK_PATH)) {
    		File file = new File(LocalFileUtil.APK_PATH);
            file.mkdirs();
    	}
    	copyFilesFromAssets(context, apkfilepath, LocalFileUtil.LOCAL_DATA_PATH + apkfilepath);
    	if(FileUtils.isFileExist(LocalFileUtil.LOCAL_DATA_PATH + apkfilepath)) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }  
}