package com.xuexiang.util.net.volleyhttp;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.xuexiang.app.BaseApplication;


/**  
 * ����ʱ�䣺2016-5-16 ����9:13:59  
 * ��Ŀ���ƣ�VolleyDemo  
 * @author xuexiang
 * �ļ����ƣ�HttpGsonRequest.java  
 **/
public class VolleyHttpManager<T> {
	
	private HashMap<String, String> mParams;
	
	private Class<T> mClass;
	
	public VolleyHttpManager(Class<T> cls) {
		mClass = cls;
	}
	
	public VolleyHttpManager(String mode, String Json, Class<T> cls) {
		mClass = cls;
		setParams(mode, Json);
	}
	
	public void setParams(String mode, String Json) {
		mParams = new HashMap<String, String>();
		mParams.put("mode", mode);
		mParams.put("Json", Json);
	}
	
	public HashMap<String, String> getParams() {
		return mParams;
	}
	
	
/****************************************************���ص���һ�������************************************************************************************************************************/	
	/**
	 * ��ȡ����Ϊʵ��������󷽷�
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void httpClassRequest(int requestMode, String url, Listener<T> listener,ErrorListener errorlistener) {
		BaseApplication.getVolleyRequestQueue().add(getHttpRequest(requestMode, url, listener, errorlistener));
	}
	
	
	/**
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public GsonRequest<T> getHttpRequest(int requestMode, String url, Listener<T> listener,ErrorListener errorlistener) {
        return getTagHttpRequest(requestMode, url, listener, errorlistener, mClass.getName());
	}
	
	/**
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @param Tag �����־
	 * @return
	 */
	public GsonRequest<T> getTagHttpRequest(int requestMode, String url, Listener<T> listener, ErrorListener errorlistener, Object Tag) {
		GsonRequest<T> httpRequest;
		if(requestMode == Method.POST) {
			httpRequest = new GsonRequest<T>(requestMode, url, mClass, listener, errorlistener){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					return mParams;
				}
			};
		} else {
			httpRequest = new GsonRequest<T>(requestMode, url, mClass, listener, errorlistener);
		}
		httpRequest.setTag(Tag);
        return httpRequest;
	}
	
/****************************************************���ص���һ���ַ���************************************************************************************************************************/		
	
	
	/**
	 * ��ȡ����Ϊʵ��������󷽷�
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void httpStringRequest(int requestMode, String url, Listener<String> listener,ErrorListener errorlistener) {
		BaseApplication.getVolleyRequestQueue().add(getStringHttpRequest(requestMode, url, listener, errorlistener));
	}
	
	
	/**
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public StringRequest getStringHttpRequest(int requestMode, String url, Listener<String> listener,ErrorListener errorlistener) {
        return getTagStringHttpRequest(requestMode, url, listener, errorlistener, mClass.getName());
	}
		
	/**
	 * ��ȡ����ΪString�ַ��������󷽷�
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public StringRequest getTagStringHttpRequest(int requestMode, String url, Listener<String> listener, ErrorListener errorlistener, Object Tag) {
		StringRequest httpRequest;
		if(requestMode == Method.POST) {
			httpRequest = new StringRequest(requestMode, url, listener, errorlistener){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {
					return mParams;
				}
			};
		} else {
			httpRequest = new StringRequest(requestMode, url, listener, errorlistener);
		}
		httpRequest.setTag(Tag);
		return httpRequest;
	}
	
	
/****************************************************���ص���һ��JSONObject************************************************************************************************************************/		
	
	/**
	 * ��ȡ����Ϊʵ��������󷽷�
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void httpJsonObjectRequest(int requestMode, String url, Listener<JSONObject> listener, ErrorListener errorlistener) {
		BaseApplication.getVolleyRequestQueue().add(getJsonObjectHttpRequest(requestMode, url, listener, errorlistener));
	}
	
	
	/**
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectHttpRequest(int requestMode, String url, Listener<JSONObject> listener, ErrorListener errorlistener) {
        return getTagJsonObjectHttpRequest(requestMode, url, listener, errorlistener, mClass.getName());
	}
	
	/**
	 * ��ȡ����ΪString�ַ��������󷽷�
	 * @param requestMode ����ʽ
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyJsonObjectRequest getTagJsonObjectHttpRequest(int requestMode, String url, Listener<JSONObject> listener, ErrorListener errorlistener, Object Tag) {
		MyJsonObjectRequest httpRequest;
		if(requestMode == Method.POST) {
			String params = appendParameter(url, mParams);  		
			httpRequest = new MyJsonObjectRequest(requestMode, url, params, listener, errorlistener);
		} else {
			httpRequest = new MyJsonObjectRequest(requestMode, url, null, listener, errorlistener);
		}
		httpRequest.setTag(Tag);
		return httpRequest;
	}
	
	private String appendParameter(String url,Map<String,String> params){  
        Uri uri = Uri.parse(url);  
        Uri.Builder builder = uri.buildUpon();  
        for(Map.Entry<String,String> entry:params.entrySet()){  
            builder.appendQueryParameter(entry.getKey(),entry.getValue());  
        }  
        return builder.build().getQuery();  
    }  
	
}
