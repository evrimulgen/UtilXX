package com.xuexiang.util.net.volleyhttp;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.OkHttpStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

/**
 * DemoApp
 * Created by acer_april
 * on 2015/7/20
 * Description: httpRequest
 */
public class HttpClientRequest {

    private static Context sContext;
    public RequestQueue mRequestQueue;

    private HttpClientRequest() {
        mRequestQueue = getRequestQueue();
    }

    public static HttpClientRequest getInstance(Context context) {
        sContext = context.getApplicationContext();
        return ClientHolder.CLIENT_REQUEST;
    }
    private static class ClientHolder {
        private static final HttpClientRequest CLIENT_REQUEST = new HttpClientRequest();
    }
    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param tag associated with the Volley requests to be cancelled
     */
    public void cancelAllRequests(String tag) {
        if (getRequestQueue() != null) {
            getRequestQueue().cancelAll(tag);
        }
    }

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link RequestQueue}
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext(),
                    new OkHttpStack(new OkHttpClient()));
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queue
     */
    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queuest
     * @param tag     is the tag identifying the request
     */
    public <T> void addRequest(Request<T> request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    
    /****************************************************���ص���һ�������************************************************************************************************************************/		
	/**
	 * �Զ�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> CustomRequest<T> getClassRequest(Class<T> clazz, 
                                   String url, 
                                   String mode, 
                                   String Json,  
                                   Response.Listener<T> listener, 
                                   Response.ErrorListener errorListener) {
	    CustomRequest<T> request = new CustomRequest.RequestBuilder<T>()
		                .post()//�����õĻ�Ĭ��GET ���������˲����Ͳ���Ҫ�ˡ�����
		                .url(url)//url��ͳһ���õ�requestUrl���� ����
		                .addParams("mode", mode)//��Ӳ���1
		                .addParams("Json", Json)//��Ӳ���2
		                .clazz(clazz) //��������˷������ͣ����Զ���������model(Gson����) ��������û�ֱ�ӷ���json����;
		                .successListener(listener)//��ȡ���ݳɹ���listener
		                .errorListener(errorListener)//��ȡ�����쳣��listener
		                .build();       
		return request;
	}
	
	/**
	 * �Զ�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, String mode, String Json,  Response.Listener<T> listener, Response.ErrorListener errorListener) {
		addRequest(getClassRequest(clazz, url, mode, Json, listener, errorListener), clazz.getSimpleName());	
	}
	
	/**
	 * �޲�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> CustomRequest<T> getClassRequest(Class<T> clazz, 
						            String url, 
						            Response.Listener<T> listener, 
						            Response.ErrorListener errorListener) {
		CustomRequest<T> request = new CustomRequest.RequestBuilder<T>()
						.url(url)//url��ͳһ���õ�requestUrl���� ����
						.clazz(clazz) //��������˷������ͣ����Զ���������model(Gson����) ��������û�ֱ�ӷ���json����;
						.successListener(listener)//��ȡ���ݳɹ���listener
						.errorListener(errorListener)//��ȡ�����쳣��listener
						.build();       
		return request;
    }
	
	/**
	 * �޲�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
		addRequest(getClassRequest(clazz, url, listener, errorListener), clazz.getSimpleName());	
	}
	
	/**
	 * map��������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> CustomRequest<T> getClassRequest(Class<T> clazz, 
						            String url, 
						            HashMap<String, String> params,
						            Response.Listener<T> listener, 
						            Response.ErrorListener errorListener) {
		CustomRequest<T> request = new CustomRequest.RequestBuilder<T>()
						.url(url)//url��ͳһ���õ�requestUrl���� ����
						.clazz(clazz) //��������˷������ͣ����Զ���������model(Gson����) ��������û�ֱ�ӷ���json����;
						.params(params) //��Ӳ�������, ���ò����Ƚ϶�������
						.successListener(listener)//��ȡ���ݳɹ���listener
						.errorListener(errorListener)//��ȡ�����쳣��listener
						.build();   
		return request;
    }
	
	/**
	 * map��������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassRequest(Class<T> clazz, String url, HashMap<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
		addRequest(getClassRequest(clazz, url, params, listener, errorListener), clazz.getSimpleName());	
	}
	
	/****************************************************���ص���һ�������ļ���************************************************************************************************************************/			
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> ClassListRequest<T> getClassListRequest(String url, 
                                   String mode, 
                                   String Json,  
                                   Response.Listener<List<T>> listener, 
                                   Response.ErrorListener errorListener) {
		ClassListRequest<T> request = new ClassListRequest.RequestBuilder<T>()
		                .post()//�����õĻ�Ĭ��GET ���������˲����Ͳ���Ҫ�ˡ�����
		                .url(url)//url��ͳһ���õ�requestUrl���� ����
		                .addParams("mode", mode)//��Ӳ���1
		                .addParams("Json", Json)//��Ӳ���2
		                .successListener(listener)//��ȡ���ݳɹ���listener
		                .errorListener(errorListener)//��ȡ�����쳣��listener
		                .build();       
		return request;
	}
	
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassListRequest(String url, String mode, String Json,  Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
		addRequest(getClassListRequest(url, mode, Json, listener, errorListener));	
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> ClassListRequest<T> getClassListRequest(String url, 
						            Response.Listener<List<T>> listener, 
						            Response.ErrorListener errorListener) {
		ClassListRequest<T> request = new ClassListRequest.RequestBuilder<T>()
						.url(url)//url��ͳһ���õ�requestUrl���� ����
						.successListener(listener)//��ȡ���ݳɹ���listener
						.errorListener(errorListener)//��ȡ�����쳣��listener
						.build();       
		return request;
    }
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassListRequest(String url, Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
		addRequest(getClassListRequest(url, listener, errorListener));	
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> ClassListRequest<T> getClassListRequest(String url, 
						            HashMap<String, String> params,
						            Response.Listener<List<T>> listener, 
						            Response.ErrorListener errorListener) {
		ClassListRequest<T> request = new ClassListRequest.RequestBuilder<T>()
						.url(url)//url��ͳһ���õ�requestUrl���� ����
						.params(params) //��Ӳ�������, ���ò����Ƚ϶�������
						.successListener(listener)//��ȡ���ݳɹ���listener
						.errorListener(errorListener)//��ȡ�����쳣��listener
						.build();   
		return request;
    }
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public <T> void executeClassListRequest(String url, HashMap<String, String> params, Response.Listener<List<T>> listener, Response.ErrorListener errorListener) {
		addRequest(getClassListRequest(url, params, listener, errorListener));	
	}
		
	/****************************************************���ص���һ���ַ���************************************************************************************************************************/			
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyStringRequest getStringRequest(String url, 
			                              String mode, String Json, 
			                              Response.Listener<String> listener,
		                                  Response.ErrorListener errorListener) {
		MyStringRequest request = new MyStringRequest.RequestBuilder()
					        .url(url)
					        .addParams("mode", mode)//��Ӳ���1
					        .addParams("Json", Json)//��Ӳ���2
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();            
		return request;		
	}
	
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeStringRequest(String url, String mode, String Json, Response.Listener<String> listener, Response.ErrorListener errorListener) {       
		addRequest(getStringRequest(url, mode, Json, listener, errorListener));	
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyStringRequest getStringRequest(String url, 
			                              Response.Listener<String> listener,
                                          Response.ErrorListener errorListener) {
		MyStringRequest request = new MyStringRequest.RequestBuilder()
					        .url(url)
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();            
        return request;			
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {       
		addRequest(getStringRequest(url, listener, errorListener));		
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyStringRequest getStringRequest(String url, 
			                              HashMap<String, String> params, 
			                              Response.Listener<String> listener,
                                          Response.ErrorListener errorListener) {
		MyStringRequest request = new MyStringRequest.RequestBuilder()
					        .url(url)
					        .params(params)
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();            
        return request;		
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param httpSuccess ����ɹ�����Ӧ
	 * @param httpError ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeStringRequest(String url, HashMap<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
		addRequest(getStringRequest(url, params, listener, errorListener));		
	}
	
	/****************************************************���ص���һ��JSONObject************************************************************************************************************************/			
	/**
	 * �Զ�����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectRequest(String url, 
			                         String mode, 
			                         String Json, 
			                         Response.Listener<JSONObject> listener,
                                     Response.ErrorListener errorListener) {
		MyJsonObjectRequest request = new MyJsonObjectRequest.RequestBuilder()
					        .url(url)
					        .addParams("mode", mode)//��Ӳ���1
		                    .addParams("Json", Json)//��Ӳ���2
		                    .toJSONString() //������ת��ΪString
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();       
		return request;		
	}
	
	/**
	 * �Զ�����������
	 * @param clazz ����������
	 * @param url ��������ַ
	 * @param mode ���󷽷�
	 * @param Json json�ַ���
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeJsonObjectRequest(String url, String mode, String Json, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		addRequest(getJsonObjectRequest(url, mode, Json, listener, errorListener));	
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectRequest(String url, 
			                                        Response.Listener<JSONObject> listener, 
			                                        Response.ErrorListener errorListener) {
		MyJsonObjectRequest request = new MyJsonObjectRequest.RequestBuilder()
					        .url(url)
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();
        return request;			
	}
	
	/**
	 * �޲�����������
	 * @param url ��������ַ
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeJsonObjectRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		addRequest(getJsonObjectRequest(url, listener, errorListener));	
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public MyJsonObjectRequest getJsonObjectRequest(String url, 
			                                        HashMap<String, String> params, 
			                                        Response.Listener<JSONObject> listener, 
			                                        Response.ErrorListener errorListener) {
		MyJsonObjectRequest request = new MyJsonObjectRequest.RequestBuilder()
					        .url(url)
					        .params(params)
					        .toJSONString()
					        .successListener(listener)
					        .errorListener(errorListener)
					        .build();
        return request;		
	}
	
	/**
	 * map��������
	 * @param url ��������ַ
	 * @param params �����������
	 * @param listener ����ɹ�����Ӧ
	 * @param errorListener ����ʧ�ܵ���Ӧ
	 * @return
	 */
	public void executeJsonObjectRequest(String url, HashMap<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		addRequest(getJsonObjectRequest(url, params, listener, errorListener));	
	}
	
}
