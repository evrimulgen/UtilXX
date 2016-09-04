package com.xuexiang.util.observer.tag;

/**  
 * ����ʱ�䣺2016-6-5 ����2:18:06  
 * @author xuexiang
 **/
public class Event {
    private String mTag;
    private String mMessage;

	/**
	 * @param tag ��Ϣ�¼���־
	 * @param msg ��Ϣ�¼�����
	 */
	public Event (String tag, String msg) {
		mTag = tag;
		mMessage = msg;
	}
	
	public String getTag() {
		return mTag;
	}
	
	public String getMessage() {
		return mMessage;
	}
}
