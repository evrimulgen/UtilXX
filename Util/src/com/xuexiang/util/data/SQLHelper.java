package com.xuexiang.util.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import com.xuexiang.util.app.AppUtils;

/**
 * Created by linlongxin on 2016/1/5.
 * ���ݿ⸨����
 */
public class SQLHelper extends SQLiteOpenHelper {

    private final String TAG = "SQLHelper";
    private static String DATABASE_NAME;
    private static Context mContext;
    private static SQLiteDatabase database;

    //key�Ǳ��name,value�Ǳ��sql�������
    private Map<String, String> tables = new HashMap<String, String>();

    public SQLHelper(Context context, String dataBaseName) {
        super(context, dataBaseName, null, AppUtils.getAppVersion(context));
        DATABASE_NAME = dataBaseName;
        mContext = context;
    }

    /**
     * ��ӱ�Ĵ���
     *
     * @param tableName
     * @param sql
     */
    public void addTable(String tableName, String sql) {
        tables.put(tableName, sql);
        database = getWritableDatabase();
        Log.i(TAG,"db == null : " + (database == null));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //�������еı�
        for (Map.Entry<String, String> table : tables.entrySet()) {
            db.execSQL(table.getValue());
        }
    }

    public SQLiteDatabase getDataBase() {
        return database;
    }

    public void execSQL(String sql){
        database.execSQL(sql);
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        return database.insert(table, nullColumnHack, values);
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        return database.delete(table, whereClause, whereArgs);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return database.update(table, values, whereClause, whereArgs);
    }

    public Cursor query(boolean distinct, String table, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit) {
        return database.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public Cursor query(boolean distinct, String table, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit, CancellationSignal cancellationSignal) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return database.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
        } else {
            return null;
        }
    }

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit) {
        return database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * �������ݿ�ͨ��APP�汾
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String sql;
            for (Map.Entry<String, String> entry : tables.entrySet()) {
                sql = "DROP TABLE IF EXISTS " + entry.getKey();
                db.execSQL(sql);
            }
            onCreate(db);
        }

    }

    /**
     * ɾ�����ݿ�
     */
    public boolean deleteDataBase() {
        return mContext.deleteDatabase(DATABASE_NAME);
    }

}
