package bean;

/**
 * ��ַ������
 * @author xx
 */
public class AddressInfo {
	
	/** Key*/
	private int id;	
	/** �û���*/
	private String loginname;
	/** �ջ���*/
	private String consignee;
	/** ��ϵ��ʽ*/
	private String contact;
	/** ���ڵ���*/
	private String area;
	/** ��ϸ��ַ*/
	private String address;
	/** ��������*/
	private String postcode;
	/** �Ƿ�ΪĬ�ϵ�ַ*/
	private String isdefault;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

}
