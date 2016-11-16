package com.xuexiang.util.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

/**
 * 基类适配器
 * @author xx
 * @param <T>
 * TODO
 */

public abstract class BaseContentAdapter<T> extends BaseAdapter{

	protected Context mContext;
	protected List<T> dataList ;
	protected LayoutInflater mInflater;
	
	
	
	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public BaseContentAdapter(Context context,List<T> list){
		mContext = context;
		dataList = list;
		mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		return getConvertView(position,convertView,parent);
	}
	
	public abstract View getConvertView(int position, View convertView, ViewGroup parent);

	
	/**
	 * 根据分隔符将String转换为List
	 * @param str
	 * @param separator 分隔符
	 * @return
	 */
	public List<String> StringToList(String str, String separator){
		List <String> list = new ArrayList <String> (); 
		list = Arrays.asList(str.split(separator)); 
		return list;
	}
	
	/**
	 * 根据分隔符将List转换为String
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

	
	public void Toast(CharSequence hint){
	    Toast.makeText(mContext, hint , Toast.LENGTH_SHORT).show();
	}
}
