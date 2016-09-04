package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserinfoDaoImpl implements UserinfoDao {
	private Connection connection;
	private PreparedStatement usersQuery;
	private ResultSet results;
	
	public Userinfo Checkuser(Userinfo userbean) {
		Userinfo userinfo = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM userinfo where loginname=? and password=? and userkind=?";
			usersQuery = connection.prepareStatement(sql);
			usersQuery.setString(1, userbean.getLoginname());
			usersQuery.setString(2, userbean.getPassword());
			usersQuery.setString(3, userbean.getUserkind());	
			results = usersQuery.executeQuery();
			if (results.next()) {
				userinfo = new Userinfo(); // ÿ�δ���һ����װ���ʵ��
				// �����ݱ��е�һ����¼������ӵ���װ����
				userinfo.setLoginname(results.getString("loginname"));
				userinfo.setPassword(results.getString("password"));
				userinfo.setUserkind(results.getString("userkind"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(usersQuery);
			DBcon.closeConnection(connection);
		}

		return userinfo;
	}

	public int add(Userinfo userbean) {
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into userinfo(loginname, password, userkind) values(?,?,?)";
			usersQuery = connection.prepareStatement(sql);
			usersQuery.setString(1, userbean.getLoginname());
			usersQuery.setString(2, userbean.getPassword());
			usersQuery.setString(3, userbean.getUserkind());		
			result = usersQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(usersQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public List<Userinfo> getUsers() {
		List<Userinfo> usersList = new ArrayList<Userinfo>();
		try {
			connection = DBcon.getConnction();
			usersQuery = connection.prepareStatement("SELECT loginname, password, userkind"
					+ " FROM userinfo ORDER BY loginname");
			ResultSet results = usersQuery.executeQuery();
			// ��ȡ������
			while (results.next()) {
				Userinfo userinfo = new Userinfo(); 
				userinfo.setLoginname(results.getString("loginname"));
				userinfo.setPassword(results.getString("password"));
				userinfo.setUserkind(results.getString("userkind"));
				usersList.add(userinfo); // ���⽫����ӵ�������
			}
		}
		// �������ݿ��쳣
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(usersQuery);
			DBcon.closeConnection(connection);
		}
		return usersList;
	}
	public int update(Userinfo userbean) {
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "update userinfo set password=? where loginname=?";
			usersQuery = connection.prepareStatement(sql);
			usersQuery.setString(1, userbean.getPassword());
			usersQuery.setString(2, userbean.getLoginname());
			result = usersQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(usersQuery);
			DBcon.closeConnection(connection);
		}
		return result;

	}

	public Userinfo LoginCheck(Userinfo userbean) {
		Userinfo userinfo = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM userinfo where loginname=? and password=?";
			usersQuery = connection.prepareStatement(sql);
			usersQuery.setString(1, userbean.getLoginname());
			usersQuery.setString(2, userbean.getPassword());
			results = usersQuery.executeQuery();
			if (results.next()) {
				userinfo = new Userinfo(); // ÿ�δ���һ����װ���ʵ��
				// �����ݱ��е�һ����¼������ӵ���װ����
				userinfo.setLoginname(results.getString("loginname"));
				userinfo.setPassword(results.getString("password"));
				userinfo.setUserkind(results.getString("userkind"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(usersQuery);
			DBcon.closeConnection(connection);
		}

		return userinfo;
	}

}
