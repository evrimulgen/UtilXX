package bean;

/**  
 * ���п���Ϣ��
 * ����ʱ�䣺2016-3-2 ����10:28:21  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�BankCard.java  
 **/
public class BankCard {
	/** Key*/
	private int id;	
	/** ���п���*/
	private String cardId;	
	/** ����*/
	private String name;	
	/** ���֤*/
	private String identitycard;
	/** ���п�����*/
	private String password;
	/** ���ڽ��*/
	private double money;
	/** ������*/
	private String banktype;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
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
	public String getBanktype() {
		return banktype;
	}
	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}
	
	

}
