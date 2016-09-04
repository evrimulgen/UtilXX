package com.xuexiang.util.net.volleyhttp;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;



 /**
  * �Զ��� ���󷽷�
  * @author yuan
  * http://blog.csdn.net/guolin_blog/article/details/17612763
  * @param <T>
  */
public class GsonRequest<T> extends Request<T> {

	private Listener<T> mListener;
	
	private Gson mGson;
	
	private Class<T> mClass;
	
	/**
	 * ���캯�� �������ж����ͬ�����ģ�����Ͳ�����ˣ�
	 * @param method
	 * @param url
	 * @param listener
	 */
	public GsonRequest(int method, String url,Class<T> clazz,Listener<T> listener,ErrorListener errorlistener) {
		super(method, url, errorlistener);
		//��ʼ�� ����
		mGson = new Gson();
		mClass = clazz;
		mListener = listener;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			//���ַ���ת���ַ������������� �ַ����� ��������Ӧ��Ϣ�ı��Ķ�����Ϣ
			String jsonString=new String(response.data,HttpHeaderParser.parseCharset(response.headers));
		
			//������Ϣ ʹ�� gson ֱ��ת ���󣬵ڶ������� ���ñ���
			return Response.success(mGson.fromJson(jsonString, mClass),HttpHeaderParser.parseCacheHeaders(response));
		
		} catch (UnsupportedEncodingException e) {
			// �����ʱ�򣬽�������Ϣ���µ���
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		// TODO Auto-generated method stub
       mListener.onResponse(response);
	}

}
