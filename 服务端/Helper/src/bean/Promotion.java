package bean;

/**
 * �������
 * @author xx
 */
public class Promotion {
	/** Key*/
	private int id;
	
	/** ����ID*/
	private int shopid;
	
	/** �׵�����������*/     
	private String firstorder;
	
	/** �����Żݡ�����*/
	private String fullreduction;
	
	/** �ۿ��Żݡ�����*/
	private String discount;

	/** ��������ȯ������*/
	private String vouchers;
	
	/** ��ǰ�µ��Ż��Żݡ�����*/
	private String preorder;
	
	/** �����������*/
	private String fullofgifts;
	
	/** �������ͷѡ�����*/
	private String freedistribution;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public String getFirstorder() {
		return firstorder;
	}

	public void setFirstorder(String firstorder) {
		this.firstorder = firstorder;
	}

	public String getFullreduction() {
		return fullreduction;
	}

	public void setFullreduction(String fullreduction) {
		this.fullreduction = fullreduction;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getVouchers() {
		return vouchers;
	}

	public void setVouchers(String vouchers) {
		this.vouchers = vouchers;
	}

	public String getPreorder() {
		return preorder;
	}

	public void setPreorder(String preorder) {
		this.preorder = preorder;
	}

	public String getFullofgifts() {
		return fullofgifts;
	}

	public void setFullofgifts(String fullofgifts) {
		this.fullofgifts = fullofgifts;
	}

	public String getFreedistribution() {
		return freedistribution;
	}

	public void setFreedistribution(String freedistribution) {
		this.freedistribution = freedistribution;
	}
	
}
