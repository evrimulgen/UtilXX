package bean;

/**  
 * ������
 * ����ʱ�䣺2016-2-27 ����5:33:40  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�ShopInfo.java  
 **/
public class ShopInfo extends HttpConnect{
	
	/** Key*/
	private int id;
	
	/** ������*/
	private String shopname;
	
	/** ����ͼƬ*/
	private String pic;
	
	/** �����*/
	private String promotion;
	
	/** ���̵�ַ*/
	private String address;
	
	/** �������ڳ���*/
	private String city;
	
	/** ��������*/
	private String type;
	
	/** ��Ӫҵ��*/
	private String mainbusiness;
	
	/** �̼���ϵ�绰*/
	private String contact;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMainbusiness() {
		return mainbusiness;
	}

	public void setMainbusiness(String mainbusiness) {
		this.mainbusiness = mainbusiness;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
