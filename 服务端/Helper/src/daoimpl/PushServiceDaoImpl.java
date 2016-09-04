package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.DBcon;

import push.PushMessage;
import dao.PushServiceDao;

public class PushServiceDaoImpl implements PushServiceDao {
	
	private Connection connection;
	private PreparedStatement pushmessageQuery;
	private ResultSet results;

	public List<PushMessage> getPushMessages(String createtime) {
		// TODO Auto-generated method stub
		List<PushMessage> PushMessageList = new ArrayList<PushMessage>();
		try {
			connection = DBcon.getConnction();
			pushmessageQuery = connection.prepareStatement("SELECT title, message, url, pushmode FROM pushmessage where createtime='" + createtime + "' ORDER BY id");
			ResultSet results = pushmessageQuery.executeQuery();
			// ��ȡ������
			while (results.next()) {
				PushMessage pushmessage = new PushMessage();
				pushmessage.setTitle(results.getString("title"));
				pushmessage.setMessage(results.getString("message"));
				pushmessage.setUrl(results.getString("url"));
				pushmessage.setPushmode(results.getString("pushmode"));
				PushMessageList.add(pushmessage);
			}
		}
		// �������ݿ��쳣
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(pushmessageQuery);
			DBcon.closeConnection(connection);
		}
		return PushMessageList;
	}

	public List<String> getPushMode(String usercode, String pushtime) {
		// TODO Auto-generated method stub
		List<String> PushModeList = new ArrayList<String>();
		try {
			connection = DBcon.getConnction();
			pushmessageQuery = connection.prepareStatement("SELECT pushtype FROM pollingservice where usercode='" + usercode + "' and pushtime='" + pushtime + "' ORDER BY id");
			ResultSet results = pushmessageQuery.executeQuery();
			// ��ȡ������
			while (results.next()) {
				PushModeList.add(results.getString("pushtype"));
			}
		}
		// �������ݿ��쳣
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(pushmessageQuery);
			DBcon.closeConnection(connection);
		}
		return PushModeList;
	}

}
