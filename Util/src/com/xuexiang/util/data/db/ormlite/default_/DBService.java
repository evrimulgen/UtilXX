package com.xuexiang.util.data.db.ormlite.default_;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import android.content.Context;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.DatabaseConnection;

/**
 * ���ݱ������
 */
public class DBService<T> {
    private Context mContext;
	private Dao<T, Integer> mDao;
	private BaseDBHelper<T> mDatabaseHelper;
	private DatabaseConnection mConnection;
	private Savepoint savePoint;

	/**
	 * @param context
	 * @param clazz
	 * @param databaseName  ���ݿ���
	 * @param databaseVersion ���ݿ�汾��
	 * @param idatabase db�����ӿ�
	 * @throws SQLException
	 */
	public DBService(Context context, Class<T> clazz, String databaseName, int databaseVersion, IDataBase idatabase) throws SQLException  {
		mContext = context;
		mDatabaseHelper = new BaseDBHelper<T>(mContext, databaseName, databaseVersion, idatabase);
		if (mDao == null) {
			mDao = mDatabaseHelper.getDao(clazz);
		}
	}

	/*************************************************����**********************************************/
	/**
	 * ���뵥������
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public int insert(T object) throws SQLException{
		return mDao.create(object);
	}
	
	/**
	 * ���뵥������(���ر�����Ķ���
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	public T insertData(T object) throws SQLException{
		return mDao.createIfNotExists(object);
	}
	
	/**
	 * �������
	 * @param collection
	 * @return
	 * @throws SQLException
	 */
	public int insertDatas(Collection<T> collection) throws SQLException {
		return mDao.create(collection);

	}

/*************************************************��ѯ**********************************************/
	/**
	 * ʹ�õ�������ѯ����
	 * @return
	 * @throws IOException
	 */
	public List<T> queryAllData() throws IOException {
		List<T> datalist = new ArrayList<T>();
		CloseableIterator<T> iterator = mDao.closeableIterator();
		try {
			while (iterator.hasNext()) {
				T data = iterator.next();
				datalist.add(data);
			}

		} finally {
			iterator.close();
		}
		return datalist;
	}
	
	/**
	 * ��ѯ���е�����
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public List<T> queryAll() throws SQLException {
		return mDao.queryForAll();
	}
	
	/**
	 * �����������ѯ
	 * ����һ�����󼯺�
	 * @param columnName ���������
	 * @param ascending true:����false������
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryAllOrderBy(String columnName, boolean ascending) throws SQLException {
		return mDao.queryBuilder().orderBy(columnName, ascending).query();
	}
	
	/**
	 * �����������ѯ
	 * ����һ�����󼯺�
	 * @param columnName ���������
	 * @param ascending true:����false������
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryAllOrderBy(String columnName1, boolean ascending1, String columnName2, boolean ascending2) throws SQLException {
		return mDao.queryBuilder().orderBy(columnName1, ascending1).orderBy(columnName2, ascending2).query();
	}
	
	/**
	 * ����id��ѯ��һ������
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T queryById(Integer id) throws SQLException{
		return mDao.queryForId(id);
	}
	
	/**
	 * ����id��ѯ��һ������
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T queryById(int id) throws SQLException{
		return mDao.queryForId(id);
	}
	
	/**
	 * ����������ѯ(һ������)
	 * ����һ�����󼯺�
	 */
	public List<T> queryByField(String fieldName,Object value) throws SQLException{
		return mDao.queryForEq(fieldName, value);
	}
	
	/**
	 * ����������ѯ(һ������)
	 * ����һ�����󼯺�
	 */
	public List<T> queryByField(Map<String, Object> clause) throws SQLException {
		return mDao.queryForFieldValuesArgs(clause);
	}
	/**
	 * ����������ѯ(һ������)
	 * ����һ�����󼯺�
	 */
	public List<T> queryByColumn(String columnName, Object value) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName, value).query();
	}
	
	/**
	 * ����������ѯ(һ������)
	 * ����һ������
	 */
	public T queryForColumn(String columnName, Object value) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName, value).queryForFirst();
	}
	
	/**
	 * ����������ѯ(��������)
	 * ����һ�����󼯺�
	 */
	public List<T> queryByColumn(String columnName1, Object value1, String columnName2, Object value2) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName1, value1).and().eq(columnName2, value2).query();
	}
	
	/**
	 * ����������ѯ(��������)
	 * ����һ������
	 */
	public T queryForColumn(String columnName1, Object value1, String columnName2, Object value2) throws SQLException {
		return mDao.queryBuilder().where().eq(columnName1, value1).and().eq(columnName2, value2).queryForFirst();
	}

	/**
	 * ����sql����ѯ
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List<String[]> queryDataBySql(String sql) throws SQLException {
		GenericRawResults<String[]> rawResults = mDao.queryRaw(sql);
		List<String[]> results = rawResults.getResults();
		return results;
	}
	
	/**
	 * ����sql����ѯ
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List<T> queryDataBySql(String sql, RawRowMapper<T> rawRowMapper) throws SQLException {
		GenericRawResults<T> rawResults = mDao.queryRaw(sql, rawRowMapper);
		List<T> results = rawResults.getResults();
		return results;
	}


/*************************************************����**********************************************/
	/**
	 * ʹ�ö������һ����¼
	 */
	public int updateData(T object) throws SQLException {
		return mDao.update(object);
	}
	
	/**
	 * ����������updateʱֱ��ʹ��sql�����и���
	 * @Description statement ���µ�SQL����������ؼ���INSERT��DELETE�� UPDATE
	 */
	public int updateDatabySQL(String sql) throws SQLException {
		return mDao.updateRaw(sql);
		
	}
	
	/**
	 * ����ĳһ�������¶���Ķ�������
	 * @param clause ���������͸���ֵ�ļ���
	 * @param columnName  ������������
	 * @param value ��������ֵ
	 * @return
	 * @throws SQLException
	 */
	public int updateDataByColumn(Map<String, Object> clause, String columnName, Object value) throws SQLException {
	   UpdateBuilder<T, Integer>  updates = mDao.updateBuilder();
	   for (String key : clause.keySet()) {
		   updates.updateColumnValue(key, clause.get(key));
	   }
	   String sql = updates.where().eq(columnName, value).prepare().getStatement();
	   return updateDatabySQL(sql);
	}
	
	/**
	 * ����ĳһ�������¶���
	 * @param updatecolumnName ������������
	 * @param updatevalue  ��������ֵ
	 * @param columnName  ��������
	 * @param value ����ֵ
	 * @return
	 * @throws SQLException
	 */
	public int updateDataByColumn(String updatecolumnName, Object updatevalue, String columnName, Object value) throws SQLException {
	   String sql = mDao.updateBuilder().updateColumnValue(updatecolumnName, updatevalue).where().eq(columnName, value).prepare().getStatement();
	   return updateDatabySQL(sql);
	}

/*************************************************ɾ��**********************************************/
	/**
	 * ����idɾ��һ����¼
	 * @param id
	 * @throws SQLException
	 */
	public int deleteById(Integer id) throws SQLException {
		return mDao.deleteById(id);
	}
	
	/**
	 * ����idɾ��һ����¼
	 * @param id
	 * @throws SQLException
	 */
	public int deleteById(int id) throws SQLException {
		return mDao.deleteById(id);
	}
	
	/**
	 * ���ݶ���ɾ��һ����¼
	 * @param object
	 * @throws SQLException
	 */
	public int deleteData(T object) throws SQLException {
		return mDao.delete(object);
	}

	/**
	 * ����ɾ��
	 * @param datas
	 * @return
	 * @throws SQLException
	 */
	public int deleteDataList(Collection<T> datas) throws SQLException {
		return mDao.delete(datas);
	}
	
	/**
	 * ɾ������
	 * @throws SQLException
	 */
	public int deleteAll() throws SQLException {
		List<T> datas = mDao.queryForAll();
		return mDao.delete(datas);
		
	}
	
    /** 
     * �ɽ���������������Ҫ������������ʱֱ�ӽ�����ŵ�callable��call()�м���
     */ 
    public <A> void doBatchTasks(Callable<A> callable) throws Exception{
    	mDao.callBatchTasks(callable);
    }

/*************************************************ִ��SQL���**********************************************/
	/**
	 * ֱ��ִ�����е�sql��䣬Ӧ�������ⳡ��
	 */
	public int executeSql(String sql) throws SQLException {
		int result = mDao.executeRaw(sql);
		return result;
	}
	
/*************************************************�������**********************************************/
	/** 
	 * �������ݿ��������
	 */ 
	public void beginTransaction(String savepoint) throws SQLException{
		mConnection= mDao.startThreadConnection();
		savePoint = mConnection.setSavePoint(savepoint);
		
	}
		
	/** 
	 * �ύ���� 
	 */ 
	public void commit() throws SQLException{
       mConnection.commit(savePoint);
       mDao.endThreadConnection(mConnection);
	}
	
	/** 
	 * ����ع�
	 */ 
	public void rollBack(Savepoint savepoint) throws SQLException{
	  mConnection.rollback(savepoint);
	  mDao.endThreadConnection(mConnection);
	}
	
//	public void close() {    
//       if (mDatabaseHelper != null) {
//    	   mDatabaseHelper.close();
//       }
//       if (mDao != null) {
//    	   mDao = null;
//       } 
//    }
}
