package com.xuexiang.util.data.db.ormlite.default_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class BaseDBHelper<T> extends OrmLiteSqliteOpenHelper {
	
	/** ����ĳ��Ӧ�ó������ݿ��*/
	private IDataBase mIDataBase;

	/**
	 * @param context
	 * @param databaseName ���ݿ���
	 * @param databaseVersion  ���ݿ�汾��
	 * @param idatabase db�����ӿ�
	 */
	public BaseDBHelper(Context context, String databaseName, int databaseVersion, IDataBase idatabase) {
		super(context, databaseName, null, databaseVersion);
		mIDataBase = idatabase;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		mIDataBase.onCreate(db, connectionSource);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		mIDataBase.onUpgrade(db, connectionSource, oldVersion, newVersion);
	}

	// �ͷ���Դ
	@Override
	public void close() {		
		super.close();
	}

}
