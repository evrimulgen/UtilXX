package com.xuexiang.util.data.db.ormlite.custom;

import com.j256.ormlite.support.ConnectionSource;
import com.xuexiang.util.data.db.ormlite.default_.IDataBase;

/**
 * �Զ������ݿ�����ӿ�
 * @author xx
 */
public interface ICustomDataBase extends IDataBase {
	/**
	 * �������ߴ����ݿ�
	 * @param connectionSource
	 */
	public void createOrOpenDB(ConnectionSource connectionSource);
	
}

