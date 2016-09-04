/**
 * Copyright 2014 Zhenguo Jin (jingle1267@163.com)
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
package com.xuexiang.util.net;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xuexiang.util.log.LogUtils;

import android.text.TextUtils;
import android.util.Log;

/**
 * ���ݾ�γ�Ȳ�ѯ��ַ��Ϣ�͸��ݵ�ַ��Ϣ��ѯ��γ��
 *
 * @author jingle1267@163.com
 */
public final class LocationUtils {

    private final static boolean DEBUG = true;
    private final static String TAG = "LocationUtils";

    /**
     * Don't let anyone instantiate this class.
     */
    private LocationUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * ���ݵ�ַ��ȡ��Ӧ�ľ�γ��
     *
     * @param address ��ַ��Ϣ
     * @return ��γ������
     */
    public static double[] getLocationInfo(String address) {
        if (TextUtils.isEmpty(address)) {
            return null;
        }
        if (DEBUG) {
            LogUtils.d(TAG, "address : " + address);
        }
        // ����һ��HttpClient��������ָ����ַ��������
        HttpClient client = new DefaultHttpClient();
        // ��ָ����ַ����GET����
        HttpGet httpGet = new HttpGet("http://maps.google."
                + "com/maps/api/geocode/json?address=" + address
                + "ka&sensor=false");
        StringBuilder sb = new StringBuilder();
        try {
            // ��ȡ����������Ӧ
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // ��ȡ��������Ӧ��������
            InputStream stream = entity.getContent();
            int b;
            // ѭ����ȡ��������Ӧ
            while ((b = stream.read()) != -1) {
                sb.append((char) b);
            }
            // �����������ص��ַ���ת��ΪJSONObject����
            JSONObject jsonObject = new JSONObject(sb.toString());
            // ��JSONObject������ȡ������λ�õ�location����
            JSONObject location = jsonObject.getJSONArray("results")
                    .getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location");
            // ��ȡ������Ϣ
            double longitude = location.getDouble("lng");
            // ��ȡγ����Ϣ
            double latitude = location.getDouble("lat");
            if (DEBUG) {
                LogUtils.d(TAG, "location : (" + longitude + "," + latitude + ")");
            }
            // �����ȡ�γ����Ϣ���double[]����
            return new double[]{longitude, latitude};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���ݾ�γ�Ȼ�ȡ��Ӧ�ĵ�ַ
     *
     * @param longitude ����
     * @param latitude  γ��
     * @param lang      ���� ���λ����Ĭ��en
     * @return ��ַ��Ϣ
     * @throws Exception
     */
    public static String getAddress(double longitude, double latitude,
                                    String lang) throws Exception {
        if (DEBUG) {
            LogUtils.d(TAG, "location : (" + longitude + "," + latitude + ")");
        }
        if (lang == null) {
            lang = "en";
        }
        // �趨����ĳ�ʱʱ��
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        // ����һ��HttpClient��������ָ����ַ��������
        HttpClient client = new DefaultHttpClient(params);
        // ��ָ����ַ����GET����
        HttpGet httpGet = new HttpGet("https://maps.googleapis.com/maps/api/"
                + "geocode/json?latlng=" + latitude + "," + longitude
                + "&sensor=false&language=" + lang);
        if (DEBUG) {
            LogUtils.d(TAG,
                    "URL : " + httpGet.getURI());
        }
        StringBuilder sb = new StringBuilder();
        // ִ������
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        // ��ȡ��������Ӧ���ַ���
        InputStream stream = entity.getContent();
        int b;
        while ((b = stream.read()) != -1) {
            sb.append((char) b);
        }
        // �ѷ�������Ӧ���ַ���ת��ΪJSONObject
        JSONObject jsonObj = new JSONObject(sb.toString());
        Log.d("ConvertUtil", "getAddress:" + sb.toString());
        // ��������Ӧ����еĵ�ַ����
        JSONObject addressObject = jsonObj.getJSONArray("results")
                .getJSONObject(0);
        String address = decodeLocationName(addressObject);
        if (DEBUG) {
            LogUtils.d(TAG, "address : " + address);
        }
        return address;
    }

    /**
     * ����Google API ���������Һͳ�������
     * https://developers.google.com/maps/documentation/geocoding
     *
     * @param jsonObject ��ַJson����
     * @return ���ع��Һͳ���
     */
    public static String decodeLocationName(JSONObject jsonObject) {
        JSONArray jsonArray;
        String country = "", city = "";
        String TYPE_COUNTRY = "country";
        String TYPE_LOCALITY = "locality";
        String TYPE_POLITICAL = "political";
        boolean hasCountry = false;
        try {
            jsonArray = jsonObject.getJSONArray("address_components");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                JSONArray types = jo.getJSONArray("types");
                boolean hasLocality = false, hasPolicical = false;

                for (int j = 0; j < types.length(); j++) {
                    String type = types.getString(j);
                    if (type.equals(TYPE_COUNTRY) && !hasCountry) {
                        country = jo.getString("long_name");
                    } else {
                        if (type.equals(TYPE_POLITICAL)) {
                            hasPolicical = true;
                        }
                        if (type.equals(TYPE_LOCALITY)) {
                            hasLocality = true;
                        }
                        if (hasPolicical && hasLocality) {
                            city = jo.getString("long_name");
                        }
                    }
                }
            }
            return city + "," + country;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject.has("formatted_address")) {
            try {
                return jsonObject.getString("formatted_address");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
