package com.xuexiang.util.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** 
 * @author  XX 
 * @date ����ʱ�䣺2016-5-25 ����11:15:02 
 */

public class DataAdapter<T> extends DataObserver {
	//������ݵļ���
	private List<T> mItems = new ArrayList<T>();
	
	public void setItems(List<T> items) {
		if (items != null) {
			mItems = items;
		} else {
			mItems = new ArrayList<T>();
		}
		notifyObservers();
		
	}
	/**
	 * ��ȡlist���ϵĶ���
	 */
	public List<T> getItems() {
		return mItems;
	}
	
	public void add(int position, T item) {
		mItems.add(position, item);
	}
	
	public void add(T item) {
		mItems.add(item);
	}
	
	public void remove(int position) {
		mItems.remove(position);
	}
	
	public void remove(T item) {
		mItems.remove(item);
	}
	
	/**
	 * �����ķ�ʽɾ��list�е�����
	 */
	public void removeFromList(T item) {
		if (item != null) {
			Iterator<T> it = mItems.iterator();
			while (it.hasNext()) {
			   T e = it.next();
			   if (item.equals(e)) {
				   it.remove();
			   }
		   }
		}
	}
	
	/**
	 * ��ȡlist���ϵĵ�����
	 */
	public Iterator<T> getIterator() {
		return mItems.iterator();
	}
	
	/**
	 * ���list����
	 */
	public void clear() {
		if (hasData()) {
			mItems.clear();
		}
	}
	
	public T get(int i) {
		return mItems.get(i);
	}

	/**
	 * ��ȡlist��������Ĵ�С
	 */
	public int size() {
		return mItems.size();
	}

	/**
	 * list�������Ƿ�������
	 */
	public boolean hasData() {
		return mItems != null && size() > 0;
	}

	
}
