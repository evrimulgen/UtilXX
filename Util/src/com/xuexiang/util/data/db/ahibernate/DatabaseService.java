
package com.xuexiang.util.data.db.ahibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hrw.framework.ahibernate.dao.AhibernateDao;

public class DatabaseService<T> {
    private Context mContext;

    private DatabaseHelper<T> mDatabaseHelper;

    private SQLiteDatabase mSQLiteDatabase;

    private AhibernateDao<T> mDao;   

    private Class<T> clazz;

	public DatabaseService (Context context, Class<T> classOfT) {		
		clazz = classOfT;
        this.mContext = context;
        this.mDatabaseHelper = new DatabaseHelper<T>(mContext,clazz);
        this.mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();                
        
        this.mDao = new AhibernateDao<T>(this.mSQLiteDatabase);
            
    }

    // ===================object begin===========================
    /**
     * ���Ӳ�ѯ���󼯺�
     * @param where ��ѯ����
     * @return
     */
    public List<T> getObjectsByWhere(Map<String, String> where) {
        List<T> objectList = mDao.queryList(clazz, where);
        return objectList;
    }
    
    /**
     * ���Ӳ�ѯ����
     * @param where ��ѯ����
     * @return ����
     */
    public T getObjectByWhere(Map<String, String> where) {
        List<T> objectList = mDao.queryList(clazz, where);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }
    
    /**
     * ����id��ѯ����
     * @param id
     * @return ����
     */
    public T queryById(long id) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put("id", id+"");
        List<T> objectList = mDao.queryList(clazz, map);       
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }
    
    /**
     * ����������ѯ����
     * @param Column ����
     * @param value ֵ
     * @return ����
     */
    public List<T> getObjectsByColumn(String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        List<T> objectList = mDao.queryList(clazz, map);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList;
        }
    }
    
    /**
     * ����������ѯ����
     * @param Column ����
     * @param value ֵ
     * @return ����
     */
    public T queryByColumn(String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        List<T> objectList = mDao.queryList(clazz, map);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }
    
    /**
     * ����������ѯ����
     * @param Column ����
     * @param value ֵ
     * @return ����
     */
    public T queryByColumn2(String Columnname1,String value1,String Columnname2,String value2) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname1, value1);
		map.put(Columnname2, value2);
        List<T> objectList = mDao.queryList(clazz, map);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }
    }

    /**
     * ���ݶ��󣨲������ԣ���ѯ���󼯺�
     * @param t
     * @return ���󼯺�
     */
    public List<T> getObjects(T t) {
        List<T> objectList = mDao.queryList(t);
        return objectList;
    }
    
    /**
     * ���ݶ��󣨲������ԣ���ѯ����
     * @param t
     * @return ����
     */
    public T getObject(T t) {
        List<T> objectList = mDao.queryList(t);
        if(objectList.isEmpty()){
        	return null;
        }else{
            return objectList.get(0);
        }

    }
    

    /**
     * ��Ӷ������ݿ�
     * @param t
     * @return
     */
    public int add(T t) {
        return mDao.insert(t);
    }

    /**
     * ���Ӷ������
     * @param t  ����
     * @param where ��������
     */
    public void update(T t, Map<String, String> where) {
        mDao.update(t, where);
    }
    
    /**
     * ���Ӷ������
     * @param t  ����
     * @param where ��������
     */
    public void updateByColumn(T t, String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        mDao.update(t, map);
    }
    
    /**
     * �������
     * @param t
     */
    public void update(T t) {
        mDao.update(t, null);
    }
   
    /**
     * ��������ɾ��
     * @param where ɾ������
     */
    public void delete(Map<String, String> where) {
        mDao.delete(clazz, where);
    }
    
    /**
     * ��������ɾ��
     * @param where ɾ������
     */
    public void deleteByColumn(String Columnname,String value) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put(Columnname, value);
        mDao.delete(clazz, map);
    }
    
    /**
     * ����idɾ��
     * @param id
     */
    public void deleteById(long id) {
    	Map<String,String> map=new HashMap<String, String>();
		map.put("id", id+"");
        mDao.delete(clazz, map);
    }
    /**
     * ɾ��ָ������
     * @param t
     */
    public void delete(T t) {
        mDao.delete(t);
    }
    // ===================object end===============================

    
    public void close() {
       if(mSQLiteDatabase!=null){
    	   mSQLiteDatabase.close();
    	   mSQLiteDatabase =null;
       }
       
       if(mDatabaseHelper!=null){
    	   mDatabaseHelper.close();
    	   mDatabaseHelper =null;
       }
       
       if(mDao!=null){
    	   mDao =null;
       }
       
    }
    
}
