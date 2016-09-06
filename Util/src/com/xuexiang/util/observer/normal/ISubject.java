package com.xuexiang.util.observer.normal;


/**  
 * ���۲��߽ӿ�
 * ����ʱ�䣺2016-6-5 ����1:00:25  
 * @author xuexiang
 **/
public interface ISubject {
	/**
	 * ���ӹ۲���
	 * @param observer ʵ��IObserver�ӿڵĶ���
	 */
	public void register(IObserver observer);
	
	/**
	 * ɾ���۲���
	 * @param observer ʵ��IObserver�ӿڵĶ���
	 */
	public void unregister(IObserver observer);

	/**
	 * ֪ͨ���еĹ۲���
	 */
	public void notifyObservers();
	
}
