package com.example.testutil.net.model;

import java.io.Serializable;

/**
 * ������Ϣ��
 * @author xx
 */
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** �û���*/
	private String loginname;	
	/** ���ֻ���*/
	private String phone;
	/** ����*/
	private String password;
	/** ͷ��*/
	private String headphoto;
	/** �ǳ�*/
	private String nickname;
	/** �Ա�*/
	private String sex;
	/** ����ǩ��*/
	private String signature;
	/** ��ʵ����*/
	private String realname;
	/** ���֤*/
	private String identitycard;
	/** �Ƿ�ʵ����֤*/
	private String ischeck;
	
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadphoto() {
		return headphoto;
	}
	public void setHeadphoto(String headphoto) {
		this.headphoto = headphoto;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	public String getIscheck() {
		return ischeck;
	}
	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}
	
	
}
