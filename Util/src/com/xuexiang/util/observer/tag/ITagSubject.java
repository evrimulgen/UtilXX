package com.xuexiang.util.observer.tag;

import java.util.List;


/**  
 * ����ʱ�䣺2016-6-5 ����2:17:04  
 * @author xuexiang
 **/
public interface ITagSubject {

	/**
	 * ���ӹ۲���
	 * @param observer ʵ��ITagObserver�ӿڵĶ���
	 * @param eventTagList ע���¼���־�ļ���
	 */
	public void register(ITagObserver observer, List<String> eventTagList);
	
	/**
	 * ɾ���۲���
	 * @param observer ʵ��ITagObserver�ӿڵĶ���
	 */
	public void unregister(ITagObserver observer);
	
	/**
	 * ֪ͨ����Ҫ��Ĺ۲���
	 * @param event ͬʱ�ľ����¼�
	 */
	public void notify(Event event);
}
