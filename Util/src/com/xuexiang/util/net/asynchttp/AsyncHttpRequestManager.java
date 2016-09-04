package com.xuexiang.util.net.asynchttp;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.xuexiang.util.net.asynchttp.toolbox.CustomLog;
import com.xuexiang.util.net.asynchttp.toolbox.HttpError;
import com.xuexiang.util.net.asynchttp.toolbox.HttpRequest;
import com.xuexiang.util.net.asynchttp.toolbox.HttpRequestQueque;
import com.xuexiang.util.net.asynchttp.toolbox.HttpSuccess;

/**  
 * ����ʱ�䣺2016-5-16 ����4:32:10  
 * ��Ŀ���ƣ�HttpForJson  
 * @author xuexiang
 * �ļ����ƣ�HttpManager.java  
 **/
public class AsyncHttpRequestManager {
	
	/**�����������*/
	private HttpRequestQueque mHttpRequestQueque;
	private static Context sContext;
	
    private AsyncHttpRequestManager() {
	   mHttpRequestQueque = getRequestQueue();
    }

    public static AsyncHttpRequestManager getInstance(Context context) {
        sContext = context.getApplicationContext();
        return ClientHolder.CLIENT_REQUEST;
    }
    private static class ClientHolder {
        private static final AsyncHttpRequestManager CLIENT_REQUEST = new AsyncHttpRequestManager();
    }

    public HttpRequestQueque getRequestQueue() {
        if (mHttpRequestQueque == null) {
        	mHttpRequestQueque = new HttpRequestQueque(sContext.getApplicationContext());
        }
        return mHttpRequestQueque;
    }
    
    public void doPost(HttpRequest request) {
        getRequestQueue().addPost(request);
    }

    public void doGet(HttpRequest request) {
        getRequestQueue().add(request);
    }
    
    public static void isDebugMode(boolean isDebug) {
    	CustomLog.debug = isDebug;
    }
/****************************************************���ص���һ�������************************************************************************************************************************/		
	/**
	 * �Զ�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> HttpClassRequest<T> getClassRequest(Class<T> clazz, String url, String mode, String Json, HttpSuccess<T> httpSuccess, HttpError httpError) {
		HashMap<String, String> params = new HashMap<String, String>();
        params.put("mode", mode);
        params.put("Json", Json);
        
        return new HttpClassRequest<T>(clazz, url, params, httpSuccess, httpError);		
	}
	
	/**
	 * �Զ�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, String mode, String Json, HttpSuccess<T> httpSuccess, HttpError httpError) {
		doPost(getClassRequest(clazz, url, mode, Json, httpSuccess, httpError));	
	}
	
	/**
	 * �޲�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> HttpClassRequest<T> getClassRequest(Class<T> clazz, String url, HttpSuccess<T> httpSuccess, HttpError httpError) {     
		return new HttpClassRequest<T>(clazz, url, null, httpSuccess, httpError);		
	}
	
	/**
	 * �޲�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, HttpSuccess<T> httpSuccess, HttpError httpError) {     
		doGet(getClassRequest(clazz, url, httpSuccess, httpError));
	}
	
	/**
	 * map��������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> HttpClassRequest<T> getClassRequest(Class<T> clazz, String url, HashMap<String, String> params, HttpSuccess<T> httpSuccess, HttpError httpError) {     
		return new HttpClassRequest<T>(clazz, url, params, httpSuccess, httpError);		
	}
	
	/**
	 * map��������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, HashMap<String, String> params, HttpSuccess<T> httpSuccess, HttpError httpError) {     
		doPost(getClassRequest(clazz, url, params, httpSuccess, httpError));	
	}
	
	/****************************************************���ص���һ���ַ���************************************************************************************************************************/			
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpStringRequest getStringRequest(String url, String mode, String Json, HttpSuccess<String> httpSuccess, HttpError httpError) {
		HashMap<String, String> params = new HashMap<String, String>();
        params.put("mode", mode);
        params.put("Json", Json);
        
		return new HttpStringRequest(url, params, httpSuccess, httpError);		
	}
	
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeStringRequest(String url, String mode, String Json, HttpSuccess<String> httpSuccess, HttpError httpError) {      
		doPost(getStringRequest(url, mode, Json, httpSuccess, httpError));	
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpStringRequest getStringRequest(String url, HttpSuccess<String> httpSuccess, HttpError httpError) {
		return new HttpStringRequest(url, null, httpSuccess, httpError);		
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeStringRequest(String url, HttpSuccess<String> httpSuccess, HttpError httpError) {
		doGet(getStringRequest(url, httpSuccess, httpError));	
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpStringRequest getStringRequest(String url, HashMap<String, String> params, HttpSuccess<String> httpSuccess, HttpError httpError) {
		return new HttpStringRequest(url, params, httpSuccess, httpError);		
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeStringRequest(String url, HashMap<String, String> params, HttpSuccess<String> httpSuccess, HttpError httpError) {
		doPost(getStringRequest(url, params, httpSuccess, httpError));	
	}
	
	/****************************************************���ص���һ��JSONObject************************************************************************************************************************/			
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpJSONObjectRequest getJsonObjectRequest(String url, String mode, String Json, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {
		HashMap<String, String> params = new HashMap<String, String>();
        params.put("mode", mode);
        params.put("Json", Json);
        
		return new HttpJSONObjectRequest(url, params, httpSuccess, httpError);		
	}
	
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeJsonObjectRequest(String url, String mode, String Json, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {       
		doPost(getJsonObjectRequest(url, mode, Json, httpSuccess, httpError));	
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpJSONObjectRequest getJsonObjectRequest(String url, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {
		return new HttpJSONObjectRequest(url, null, httpSuccess, httpError);		
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeJsonObjectRequest(String url, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {
		doGet(getJsonObjectRequest(url, httpSuccess, httpError));	
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpJSONObjectRequest getJsonObjectRequest(String url, HashMap<String, String> params, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {
		return new HttpJSONObjectRequest(url, params, httpSuccess, httpError);		
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeJsonObjectRequest(String url, HashMap<String, String> params, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {
		doPost(getJsonObjectRequest(url, params, httpSuccess, httpError));	
	}
	
	/****************************************************�ļ��ϴ�************************************************************************************************************************/			
	/**
	 * �޲����ļ��ϴ�
	 * @param url ��������ַ
	 * @param files �ϴ��ļ�����
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpFileRequest getFileReques(String url, List<File> files, HttpSuccess<String> httpSuccess, HttpError httpError) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < files.size(); i++) {
			params.put("file" + i, files.get(i));
		}        
		return new HttpFileRequest(url, params, httpSuccess, httpError);		
	}
	
	/**
	 * �޲����ļ��ϴ�
	 * @param url ��������ַ
	 * @param files �ϴ��ļ�����
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeFileReques(String url, List<File> files, HttpSuccess<String> httpSuccess, HttpError httpError) {
		doPost(getFileReques(url, files, httpSuccess, httpError));	
	}
	
	/**
	 * �в����ļ��ϴ�
	 * @param url ��������ַ
	 * @param files �ϴ��ļ�����
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public HttpFileRequest getFileReques(String url, List<File> files, HashMap<String, String> param, HttpSuccess<String> httpSuccess, HttpError httpError) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < files.size(); i++) {
			params.put("file" + i, files.get(i));
		}
		
		Iterator<Map.Entry<String, String>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String k = entry.getKey();
			String p = entry.getValue();		
			if (p != null) {
				params.put(k, p);
			}
		}
		return new HttpFileRequest(url, params, httpSuccess, httpError);		
	}
	
	/**
	 * �в����ļ��ϴ�
	 * @param url ��������ַ
	 * @param files �ϴ��ļ�����
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeFileReques(String url, List<File> files, HashMap<String, String> param, HttpSuccess<String> httpSuccess, HttpError httpError) {
		doPost(getFileReques(url, files, param, httpSuccess, httpError));	
	}
}
