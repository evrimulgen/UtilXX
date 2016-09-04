package com.xuexiang.util.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;

/** 
 * SharedPreferences�����߻���
 * @author  XX 
 */

public class BaseSharedPreferencesUtil {
	public SharedPreferences.Editor mEditor;
	public SharedPreferences mSharedPreferences;
	public Context mContext;
	
	/**
	 * ��ȡ�Զ����SharedPreferences
	 * @param context
	 * @param spName �Զ���SharedPreferences��
	 */
	public BaseSharedPreferencesUtil(Context context, String spName) {
		mContext = context;
		mSharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
	    mEditor = mSharedPreferences.edit();
	}
	
	/**
	 * ��ȡϵͳĬ�ϵ�SharedPreferences
	 * @param context
	 */
	public BaseSharedPreferencesUtil(Context context) {
		mContext = context;
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	    mEditor = mSharedPreferences.edit();
	}
	
	
	/**
	 * �Զ���sharedpreferences����booleanֵ
	 * @param key
	 * @param value
	 */
	public void putBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * �Զ���sharedpreferences����floatֵ
	 * @param key
	 * @param value
	 */
	public void putFloat(String key, float value) {
		mEditor.putFloat(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * �Զ���sharedpreferences����longֵ
	 * @param key
	 * @param value
	 */
	public void putLong(String key, long value) {
		mEditor.putLong(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * �Զ���sharedpreferences����Stringֵ
	 * @param key
	 * @param value
	 */
	public void putString(String key, String value) {
		mEditor.putString(key, value);
	    mEditor.commit();  
	}
	
	/**
	 * �Զ���sharedpreferences����intֵ
	 * @param key
	 * @param value
	 */
	public void putInt(String key, int value) {
		mEditor.putInt(key, value);
	    mEditor.commit();  
	}
	

	public void remove(String key) {
		mEditor.remove(key);
	    mEditor.commit();  
	}	
	
	/**
	 * �������
	 */
	public void clear() {
		mEditor.clear();
    	mEditor.commit();
	}

/*****************************************************************************************************************************************************************/
	
	/**
	 * ����key��ȡbooleanֵ
	 * @param key
	 * @param defValue
	 * @return
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSharedPreferences.getBoolean(key, defValue);
	}
	
	/**
	 * ����key��ȡlongֵ
	 * @param key
	 * @param defValue
	 * @return
	 */
	public long getLong(String key, long defValue) {
		return mSharedPreferences.getLong(key, defValue);
	}
	
	/**
	 * ����key��ȡfloatֵ
	 * @param key
	 * @param defValue
	 * @return
	 */
	public float getFloat(String key, float defValue) {
		return mSharedPreferences.getFloat(key, defValue);
	}
	
	/**
	 * ����key��ȡStringֵ
	 * @param key
	 * @param defValue
	 * @return
	 */
	public String getString(String key, String defValue) {
		return mSharedPreferences.getString(key, defValue);
	}
	
	/**
	 * ����key��ȡintֵ
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getInt(String key, int defValue) {
		return mSharedPreferences.getInt(key, defValue);
	}
	
	
	/**
	 * ������Դid��ȡStringֵ
	 * @param resourceid ��Դid
	 * @return
	 */
	public String getResourceString(int resourceId) {
		return mContext.getResources().getString(resourceId);
	}
	
	/**
	 * ��ȡListPreference������ľ����ֵ
	 * @param listpref
	 * @param matchvalue
	 * @return
	 */
	public String getListEntryFromValue(ListPreference listpref, String matchvalue) {
		CharSequence[] entries = listpref.getEntries();
		CharSequence[] entryValues = listpref.getEntryValues();
		for (int i = 0; i < entryValues.length; i++) {
			if (entryValues[i].equals((String) matchvalue))
				return (String) entries[i];
		}
		return "";
	}
	
}
