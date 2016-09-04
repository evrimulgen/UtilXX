package bean;

import java.io.Serializable;

/**  
 * ����ʱ�䣺2016-3-26 ����11:31:29  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�RepairInfo.java  
 **/
public class RepairInfo extends HttpConnect implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Key*/
	private int id;
	/** ����*/
	private UserInfo author;
	/** �������*/
	private String title;
	/** ������������*/
	private String description;
	/** ����ͼƬ*/
	private String piclist;		
	/** ����ʱ��*/
	private String createtime;
	/** �����˵�ַ*/
	private String address;
	/** �������*/
	private double price;	
	/** ��������*/
	private String repairtype;
	/** ���ͣ��Ƿ����ṩ�ߡ���provider�����Ƿ��������ߡ���customer*/
	private String kind;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPiclist() {
		return piclist;
	}
	public void setPiclist(String piclist) {
		this.piclist = piclist;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRepairtype() {
		return repairtype;
	}
	public void setRepairtype(String repairtype) {
		this.repairtype = repairtype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

}
