package com.xuexiang.util.data.db.ormlite.custom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * ���ݿ��ܻ���DBHelper��
 * @author xx
 */
public class CustomBaseDBHelper extends OrmLiteSqliteOpenHelper {
   
	/** ���ݿ�·��*/
	private String mDatabasePath;	
	/** ����ĳ��Ӧ�ó������ݿ��*/
	private ICustomDataBase mIDataBase;
	
    /**
     * @param context
     * @param databasePath  ���ݿ�����·��
     * @param databaseVersion  ���ݿ�汾��
     * @param idatabase  db�����ӿ���
     */
    public CustomBaseDBHelper(Context context, String databasePath, int databaseVersion, ICustomDataBase idatabase) {
    	super(context, null, null, databaseVersion);
    	mDatabasePath = databasePath ;
    	mIDataBase = idatabase;
    	
    	mIDataBase.createOrOpenDB(connectionSource);
	}

    @Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource){
    	mIDataBase.onCreate(database, connectionSource);
    }

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion){
		mIDataBase.onUpgrade(database, connectionSource, oldVersion, newVersion);
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		return SQLiteDatabase.openDatabase(mDatabasePath, null,
                SQLiteDatabase.OPEN_READWRITE);
	}
	
	@Override
	public SQLiteDatabase getReadableDatabase() {
		return SQLiteDatabase.openDatabase(mDatabasePath, null,
                SQLiteDatabase.OPEN_READWRITE);
	}
	
}
