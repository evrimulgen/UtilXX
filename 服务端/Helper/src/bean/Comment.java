package bean;


/**  
 * ����
 * ����ʱ�䣺2016-2-19 ����5:52:04  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�Comment.java  
 **/
public class Comment {
	/** Key*/
	private int id;	
	/** ������*/
	private UserInfo user;
	/** ǽ���key*/
	private int qyid;	
	/** ��������*/
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public int getQyid() {
		return qyid;
	}
	public void setQyid(int qyid) {
		this.qyid = qyid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
