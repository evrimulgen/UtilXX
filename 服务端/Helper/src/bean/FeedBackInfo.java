package bean;

/**
 *������Ϣ��
 */
public class FeedBackInfo extends HttpConnect{
	/** Key*/
	private int id;	
	/** �������*/
	private String content;
	/** �������û�*/
	private UserInfo author;	
	/** ��ϵ��ʽ*/
	private String contact;
	/** ����ʱ��*/
	private String submittime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserInfo getAuthor() {
		return author;
	}
	public void setAuthor(UserInfo author) {
		this.author = author;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSubmittime() {
		return submittime;
	}
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	
}
