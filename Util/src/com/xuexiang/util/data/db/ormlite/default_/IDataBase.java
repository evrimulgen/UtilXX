package com.xuexiang.util.data.db.ormlite.default_;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;

/**
 * ���ݿ�����ӿ�
 * @author xx
 */
public interface IDataBase {

	/**
	 * ���ݿⴴ��
	 * @param database
	 * @param connectionSource
	 */
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource);
	
	/**
	 * ���ݿ������ͽ�������
	 * @param database
	 * @param connectionSource 
	 * @param oldVersion �ɰ汾
	 * @param newVersion �°汾
	 */
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion);
}
