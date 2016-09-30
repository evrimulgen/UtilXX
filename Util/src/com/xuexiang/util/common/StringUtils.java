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
package com.xuexiang.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.text.TextUtils;

/**
 * �ַ����������߰� ���android.text.TextUtilsʹ��
 *
 * @author jingle1267@163.com
 */
public final class StringUtils{

	public static String EMPTY = "";
    /**
     * Don't let anyone instantiate this class.
     */
    private StringUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * �ַ���ת����
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * byte[]����ת��Ϊ16���Ƶ��ַ���
     *
     * @param data Ҫת�����ֽ�����
     * @return ת����Ľ��
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16���Ʊ�ʾ���ַ���ת��Ϊ�ֽ�����
     *
     * @param s 16���Ʊ�ʾ���ַ���
     * @return byte[] �ֽ�����
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ�������ֽ�
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    /**
     * ���������ַ��������и����Ĺؼ��ֱ��
     *
     * @param sourceString �������ַ���
     * @param keyword      �����Ĺؼ���
     * @return ���ص��Ǵ�Html��ǩ���ַ�������ʹ��ʱҪͨ��Html.fromHtml()ת��ΪSpanned�����ٴ��ݸ�TextView����
     */
    public static String keywordMadeRed(String sourceString, String keyword) {
        String result = "";
        if (sourceString != null && !"".equals(sourceString.trim())) {
            if (keyword != null && !"".equals(keyword.trim())) {
                result = sourceString.replaceAll(keyword,
                        "<font color=\"red\">" + keyword + "</font>");
            } else {
                result = sourceString;
            }
        }
        return result;
    }

    /**
     * Ϊ�������ַ������HTML��ɫ��ǣ���ʹ��Html.fromHtml()��ʽ��ʾ��TextView ��ʱ���佫�Ǻ�ɫ��
     *
     * @param string �������ַ���
     * @return
     */
    public static String addHtmlRedFlag(String string) {
        return "<font color=\"red\">" + string + "</font>";
    }
    
    /**
	 * ���ݷָ�����Stringת��ΪList
	 * @param str
	 * @param separator �ָ���
	 * @return
	 */
	public List<String> StringToList(String str, String separator){
		List <String> list = new ArrayList <String> (); 
		list = Arrays.asList(str.split(separator)); 
		return list;
	}
	
	/**
	 * ���ݷָ�����Listת��ΪString
	 * @param list
	 * @param separator
	 * @return
	 */
	public String ListToString(List<String> list, String separator) {    
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {        
			sb.append(list.get(i)).append(separator);    
			}    
		return sb.toString().substring(0,sb.toString().length()-1);
	}
	
	
	/** 
	 * Java�ļ����� ��ȡ�ļ���չ�� 
	 * 
	 *  Created on: 2011-8-2 
	 *      Author: blueeagle 
	 */  
	 public static String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
	 } 


}
