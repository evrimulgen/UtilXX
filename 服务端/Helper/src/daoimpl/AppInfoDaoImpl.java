package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.AppInfo;
import bean.DBcon;
import dao.AppInfoDao;

public class AppInfoDaoImpl implements AppInfoDao {
	
	private Connection connection;
	private PreparedStatement appinfoQuery;
	private ResultSet results;

	public AppInfo getNewAppInfo() {
		AppInfo appinfo = null;
		try {
			connection = DBcon.getConnction();
			appinfoQuery = connection.prepareStatement("SELECT id, versionname, versiondescribe FROM appinfo ORDER BY id DESC");
		    results = appinfoQuery.executeQuery();
			// ��ȡ������
			if (results.next()) {
				appinfo = new AppInfo(); 
				appinfo.setId(results.getInt("id"));
				appinfo.setVersionname(results.getString("versionname"));
				appinfo.setVersiondescribe(results.getString("versiondescribe"));
			}
		}
		// �������ݿ��쳣
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(appinfoQuery);
			DBcon.closeConnection(connection);
		}
		return appinfo;
	}

	public boolean isNeedUpdate(String versionname) {
		// TODO Auto-generated method stub
		boolean result = false;
		AppInfo appinfo = null;
		try {
			connection = DBcon.getConnction();
			appinfoQuery = connection.prepareStatement("SELECT id, versionname, versiondescribe FROM appinfo ORDER BY id DESC");
			results = appinfoQuery.executeQuery();
			// ��ȡ������
			if (results.next()) {
				appinfo = new AppInfo(); 
				appinfo.setId(results.getInt("id"));
				appinfo.setVersionname(results.getString("versionname"));
				appinfo.setVersiondescribe(results.getString("versiondescribe"));
				if(!appinfo.getVersionname().equals(versionname)){
					result = true;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(appinfoQuery);
			DBcon.closeConnection(connection);
	    }
		return result;
	}

}
