
package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDaoImpl implements StudentDao {
	private Connection connection;
	private PreparedStatement studentsQuery;
	private ResultSet results;
	public List<Student> getstudents() {
		List<Student> studentsList = new ArrayList<Student>();
		// ��ȡ�鼮���ݼ�ResultSet results
		try {
			connection = DBcon.getConnction();
			studentsQuery = connection.prepareStatement("SELECT sno, sname, ssex,sage,"
					+ " dno, contact, homeaddr " + " FROM student ORDER BY sno");
			ResultSet results = studentsQuery.executeQuery();
			// ��ȡ������
			while (results.next()) {
				Student student = new Student(); // ��ÿ�е�ͼ�����ݴ���һ��ͼ���װ���ʵ��
				// ѭ����ͼ����е�ÿ����¼��װΪ����bean����ӵ���������
				student.setSno(results.getString("sno"));
				student.setSname(results.getString("sname"));
				student.setSsex(results.getString("ssex"));
				student.setSage(results.getInt("sage"));
				student.setDno(results.getString("dno"));
				student.setContact(results.getString("contact"));
				student.setHomeaddr(results.getString("homeaddr"));
				studentsList.add(student); // ���⽫����ӵ�������
			}
		}
		// �������ݿ��쳣
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return studentsList;
	}

	public int add(Student studentbean) { // ���÷�װ���ʵ�����student�в����¼
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "insert into student(sno, sname, ssex, ";
			sql += "sage, dno, contact, homeaddr) values(?,?,?,?,?,?,?)";
			studentsQuery = connection.prepareStatement(sql);
			studentsQuery.setString(1, studentbean.getSno());
			studentsQuery.setString(2, studentbean.getSname());
			studentsQuery.setString(3, studentbean.getSsex());
			studentsQuery.setInt(4, studentbean.getSage());
			studentsQuery.setString(5, studentbean.getDno());
			studentsQuery.setString(6, studentbean.getContact());
			studentsQuery.setString(7, studentbean.getHomeaddr());
			result = studentsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public int delete(String sno) { // ����ѧ��snoɾ��ѧ����¼
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "delete from student where sno='" + sno + "'";
			studentsQuery = connection.prepareStatement(sql);
			result = studentsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return result;
	}

	public int update(Student studentbean) { // ���÷�װ���ʵ�����±�student�м�¼
		int result = 0;
		try {
			connection = DBcon.getConnction();
			String sql = "update student set sname=?, ssex=?, ";
			sql += "sage=?, dno=?, contact=?, homeaddr=?  where sno=?";
			studentsQuery = connection.prepareStatement(sql);
			studentsQuery.setString(1, studentbean.getSname());
			studentsQuery.setString(2, studentbean.getSsex());
			studentsQuery.setInt(3, studentbean.getSage());
			studentsQuery.setString(4, studentbean.getDno());
			studentsQuery.setString(5, studentbean.getContact());
			studentsQuery.setString(6, studentbean.getHomeaddr());
			studentsQuery.setString(7, studentbean.getSno());
			result = studentsQuery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ͷ���Դ
		finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}
		return result;

	}

	public Student findBySno(String sno) { // ����ѧ��sno����ѧ����Ϣ
		Student student = null;
		try {
			connection = DBcon.getConnction();
			String sql = "SELECT * FROM student where sno='" + sno + "'";
			studentsQuery = connection.prepareStatement(sql);
			results = studentsQuery.executeQuery();
			if (results.next()) {
				student = new Student(); // ÿ�δ���һ����װ���ʵ��
				// �����ݱ��е�һ����¼������ӵ���װ����
				student.setSno(results.getString("sno"));
				student.setSname(results.getString("sname"));
				student.setSsex(results.getString("ssex"));
				student.setSage(results.getInt("sage"));
				student.setDno(results.getString("dno"));
				student.setContact(results.getString("contact"));
				student.setHomeaddr(results.getString("homeaddr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBcon.closeResultSet(results);
			DBcon.closeStatement(studentsQuery);
			DBcon.closeConnection(connection);
		}

		return student;
	}

}
