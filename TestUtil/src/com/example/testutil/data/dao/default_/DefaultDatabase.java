package com.example.testutil.data.dao.default_;

import java.sql.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testutil.data.entity.Student;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xuexiang.util.data.db.ormlite.default_.IDataBase;

public class DefaultDatabase implements IDataBase {
	
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTableIfNotExists(connectionSource, Student.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//		Log.e("xx", "�������ݿ�");
//		String sql = "ALTER TABLE student add column score interger default 0";  //�����Ϣ�����ӱ��ر���״̬
//		database.execSQL(sql);		
		Log.i("xx", "���ݿ�ɰ汾=======" + oldVersion);
		Log.i("xx", "���ݿ��°汾=======" + newVersion);
	}

}
