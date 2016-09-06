package com.xuexiang.util.observer.normal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**  
 * ���۲���
 * ����ʱ�䣺2016-6-5 ����1:03:27   
 * @author xuexiang
 **/
public class BaseSubject implements ISubject {
	
    /**
     * ������ݹ۲��߼���
     */
    private ArrayList<WeakReference<IObserver>> mObservers = new ArrayList<WeakReference<IObserver>>();

	@Override
	public void register(IObserver observer) {
		WeakReference<IObserver> obs = new WeakReference<IObserver>(observer);
		mObservers.add(obs);
	}

	@Override
	public void unregister(IObserver observer) {
		WeakReference<IObserver> obs = new WeakReference<IObserver>(observer);
		mObservers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		ArrayList<WeakReference<IObserver>> observers = mObservers;
	    int count = observers.size();
		for (int i = count - 1; i >= 0; i--) {
			WeakReference<IObserver> weak = observers.get(i);
			IObserver obs = weak.get();
			if (obs != null) {
				obs.onChanged();
			} else {
				observers.remove(i);
			}
		}

	}


}
