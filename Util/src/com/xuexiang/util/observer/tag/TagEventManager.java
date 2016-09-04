package com.xuexiang.util.observer.tag;

import java.util.HashMap;
import java.util.Map;


/**  
 * ����ʱ�䣺2016-6-5 ����4:16:29  
 * @author xuexiang
 **/
public class TagEventManager {
	private static Map<String, BaseTagSubject> mEventManager = new HashMap<String, BaseTagSubject>();  //��Ÿ��ֱ��۲��߶���
	
	public static BaseTagSubject getTagSubject(String subjectName) {
		BaseTagSubject baseSubject;
		if (mEventManager.containsKey(subjectName)) {
			baseSubject = mEventManager.get(subjectName);
		} else {
			baseSubject = new BaseTagSubject();
			mEventManager.put(subjectName, baseSubject);
		}
		return baseSubject;
	}
}
