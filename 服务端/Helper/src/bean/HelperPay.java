package bean;

import java.util.List;

/**  
 * �־ӱ���
 * ����ʱ�䣺2016-3-2 ����10:26:59  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�helperpay.java  
 **/
public class HelperPay {
	/** Key*/
	private int id;	
	/** �û���*/
	private String username;
	/** ֧������*/
	private String password;
	/** ��Ǯ*/
	private double money;
	/** �����п�*/
	private List<BankCard> bankcardlist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public List<BankCard> getBankcardlist() {
		return bankcardlist;
	}
	public void setBankcardlist(List<BankCard> bankcardlist) {
		this.bankcardlist = bankcardlist;
	}	
}
