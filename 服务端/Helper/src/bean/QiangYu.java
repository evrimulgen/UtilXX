package bean;

/**  
 * ǽ��
 * ����ʱ�䣺2016-2-19 ����5:34:48  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�QiangYu.java  
 **/
public class QiangYu extends HttpConnect{
	/** Key*/
	private int id;
	/** ����*/
	private UserInfo author;
	/** ǽ������*/
	private String content;
	/** ǽ��ͼƬ*/
	private String pic;
	/** �޵ĸ���*/
	private int love;
	/** ����ʱ��*/
	private String createtime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getAuthor() {
		return author;
	}
	public void setAuthor(UserInfo author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
