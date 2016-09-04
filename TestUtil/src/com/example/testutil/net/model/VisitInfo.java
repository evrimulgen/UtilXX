package com.example.testutil.net.model;

import java.io.Serializable;

/**  
 * ���ŷ�����Ϣ��
 * ����ʱ�䣺2016-3-1 ����11:11:04  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�VisitInfo.java  
 **/
public class VisitInfo extends HttpConnect implements Serializable{

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
	/** �޵ĸ���*/
	private int love;
	/** ����ʱ��*/
	private String createtime;
	/** �����˵�ַ*/
	private String address;
	/** �������*/
	private double price;	
	/** ��������*/
	private String visittype;
		
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
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	public String getVisittype() {
		return visittype;
	}
	public void setVisittype(String visittype) {
		this.visittype = visittype;
	}

}
