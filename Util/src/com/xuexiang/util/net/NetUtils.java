package com.xuexiang.util.net;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;
import java.io.*;
import java.net.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/**
 * ���ʷ������Ĺ�����
 *
 */
public class NetUtils {

	public static BasicCookieStore cookieStore = new BasicCookieStore();

	public NetUtils() {
	}

	public static String getCookieString() {
		String cookiestring = "";
		Cookie cookie = null;
		if (!cookieStore.getCookies().isEmpty()) {
			StringBuilder str_buid = new StringBuilder();
			cookie = cookieStore.getCookies().get(0);
			str_buid.append(cookie.getName());
			str_buid.append("=");
			str_buid.append(cookie.getValue());
			str_buid.append("; Path=");
			str_buid.append(cookie.getPath());
			cookiestring = str_buid.toString();
		}
		return cookiestring;
	}
	
	public static String getResponseString(String url, String strRequest, int connectTimeout, int soTimeout) {
		DefaultHttpClient defaulthttpclient;
		HttpPost httppost;
		BasicHttpParams basichttpparams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(basichttpparams, connectTimeout);// 30000
		HttpConnectionParams.setSoTimeout(basichttpparams, soTimeout);// 15000

		defaulthttpclient = new DefaultHttpClient(basichttpparams);

		
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(strRequest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (OutOfMemoryError e) {
			return null;
		}

		if (!cookieStore.getCookies().isEmpty()) {
			BasicCookieStore basiccookiestore = cookieStore;
			defaulthttpclient.setCookieStore(basiccookiestore);
		}
		long begintime=0;
		try {
			httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "Application/json");
			httppost.setEntity(stringentity);
			BasicResponseHandler hBR = new BasicResponseHandler();
			begintime=System.currentTimeMillis();
			String s2 = (String) defaulthttpclient.execute(httppost, hBR);
			
			if (cookieStore.getCookies().isEmpty())
				cookieStore = (BasicCookieStore) defaulthttpclient
						.getCookieStore();
			httppost.abort();
			defaulthttpclient.getConnectionManager().shutdown();
			return s2;
		}
		catch (ConnectTimeoutException e) {
			e.printStackTrace();
			return Result.ERROR_ACCESS_TIMEOUT;
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			return Result.ERROR_RECEIVE_EXCEPTION;
		}catch (SocketTimeoutException e) {//�������ݳ�ʱ��
			e.printStackTrace();
			long endTime=System.currentTimeMillis()-begintime;
			return Result.ERROR_ACCESS_TIMEOUT;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return Result.ERROR_ADDRESS_FORMAT;
		} catch (IOException e) {
			e.printStackTrace();
			return Result.ERROR_RECEIVE_EXCEPTION;
		}catch (OutOfMemoryError e) {//�ڴ����
			e.printStackTrace();
			return Result.ERROR_RECEIVE_EXCEPTION;
		}
	}

	public static String getResponseString(String url, String strRequest) {
		return getResponseString(url, strRequest, 30000, 15000);
	}	
	
	
	 public static String post(String url, String content) {
         HttpURLConnection conn = null;
         try {
             URL mURL = new URL(url);
             conn = (HttpURLConnection) mURL.openConnection();

             conn.setRequestMethod("POST");
             conn.setReadTimeout(5000);
             conn.setConnectTimeout(10000);
             conn.setDoOutput(true);

             String data = content;
             OutputStream out = conn.getOutputStream();
             out.write(data.getBytes());
             out.flush();
             out.close();

             int responseCode = conn.getResponseCode();
             if (responseCode == 200) {

                 InputStream is = conn.getInputStream();
                 String response = getStringFromInputStream(is);
                 return response;
             } else {
                 throw new NetworkErrorException("response status is "+responseCode);
             }

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             if (conn != null) {
                 conn.disconnect();// �ر�����
             }
         }

         return null;
     }

     public static String get(String url) {
         HttpURLConnection conn = null;
         try {
             // ����string url����URL����
             URL mURL = new URL(url);
             conn = (HttpURLConnection) mURL.openConnection();

             conn.setRequestMethod("GET");
             conn.setReadTimeout(5000);
             conn.setConnectTimeout(10000);

             int responseCode = conn.getResponseCode();
             if (responseCode == 200) {

                 InputStream is = conn.getInputStream();
                 String response = getStringFromInputStream(is);
                 return response;
             } else {
                 throw new NetworkErrorException("response status is "+responseCode);
             }

         } catch (Exception e) {
             e.printStackTrace();
         } finally {

             if (conn != null) {
                 conn.disconnect();
             }
         }

         return null;
     }

     private static String getStringFromInputStream(InputStream is)
             throws IOException {
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];
         int len = -1;
         while ((len = is.read(buffer)) != -1) {
             os.write(buffer, 0, len);
         }
         is.close();
         String state = os.toString();// �����е�����ת�����ַ���,���õı�����utf-8(ģ����Ĭ�ϱ���)
         os.close();
         return state;
     }
	
	
	/**
	 * �жϹ���ģ���Ƿ�����
	 * @param context
	 * @return
	 */
	public static boolean IsHaveInternet(Context context){
		try {

			ConnectivityManager manger = (ConnectivityManager)

			context.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo info = manger.getActiveNetworkInfo();

			return (info != null && info.isConnected());

		} catch (Exception e) {

			return false;

		}
	}

	/**
	 * �ж�3G�����wifi�����Ƿ�����
	 * 
	 * @param context
	 */
	public static boolean checkNetworkInfo(Context context) {
		ConnectivityManager conMan = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// mobile 3G Data Network
		State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();

//		// wifi
//		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
//				.getState();

		// ���3G�����wifi���綼δ���ӣ��Ҳ��Ǵ�����������״̬ �����Network Setting���� ���û�������������
		if (mobile!=null && mobile == State.CONNECTED)// ||mobile==State.CONNECTING
			return true;
//		if (wifi == State.CONNECTED)// ||wifi==State.CONNECTING
//			return true;

		return false;
		// context.startActivity(new Intent());startActivity(new
		// Intent(Settings.ACTION_WIRELESS_SETTINGS));//���������������ý���
		// startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
		// //�����ֻ��е�wifi�������ý���
	}

	/**
	 * �Ƿ����WiFi����
	 * 
	 * @param context
	 */
	public static boolean IsHaveWiFi(Context context) {
		ConnectivityManager conMan = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// wifi
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();

		if (wifi!=null&&wifi == State.CONNECTED) {
			return true;
		}

		return false;
	}

	public static boolean pingServer() {
		int timeOut = 3000; // ���峬ʱ��������ʱ���������ϼ��϶�Ϊ���ɴ��ʱֵ����̫С��
		try {// ping����
			boolean status = InetAddress.getByName("www.baidu.com")
					.isReachable(timeOut);
			Log.d("UDP", "Status = " + status);
			return status;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	
	public static final class Result{
		public static final String ERROR_ACCESS_SERVER="com.xuexiang.util.consts.Error.ERROR_ACCESS_SERVER";
		public static final String ERROR_READ_DATA="com.xuexiang.util.consts.Error.ERROR_READ_DATA";
		public static final String ERROR_ACCESS_TIMEOUT="com.xuexiang.util.consts.Error.ERROR_ACCESS_TIMEOUT";
		public static final String ERROR_ACCESS_OUT_OF_MEMORY="com.xuexiang.util.consts.Error.ERROR_OUT_OF_MEMORY";
		public static final String ERROR_REQUEST_SERVER_FAIL="ERROR_REQUEST_SERVER_FAIL";//����ʧ��
		public static final String ERROR_ADDRESS_FORMAT="ERROR_ADDRESS_FORMAT";
		public static final String ERROR_RECEIVE_EXCEPTION="com.xuexiang.util.consts.Error.ERROR_RECEIVE_EXCEPTION";
		
		public static final String REQUEST_SERVER_SUCCESS="REQUEST_SERVER_SUCCESS";
		public static final String FILE_NOT_FOUND_ERROR="FILE_NOT_FOUND_ERROR";
		public static final String UPLOAD_SUCCESS="UPLOAD_SUCCESS";
		public static final String UPLOAD_FAILED="UPLOAD_FAILED";
		public static final String FILE_NOT_FOUND="FILE_NOT_FOUND";
		
	}
	

}
