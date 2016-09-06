package com.xuexiang.util.observer.normal;

import java.util.HashMap;
import java.util.Map;


/**  
 * ����ʱ�䣺2016-6-5 ����1:12:48  
 * @author xuexiang
 **/
public class EventManager {

	/**
	 * ������б��۲��߶���ļ���
	 */
	private static Map<String, BaseSubject> mEventManager = new HashMap<String, BaseSubject>();  //��Ÿ��ֱ��۲��߶���
	
	/**
	 * ��ȡ���۲��߶���
	 * @param subjectName ���۲��߶���ı��
	 */
	public static BaseSubject getSubject(String subjectName) {
		BaseSubject baseSubject;
		if (mEventManager.containsKey(subjectName)) {
			baseSubject = mEventManager.get(subjectName);
		} else {
			baseSubject = new BaseSubject();
			mEventManager.put(subjectName, baseSubject);
		}
		return baseSubject;
	}

}
