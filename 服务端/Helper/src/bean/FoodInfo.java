package bean;

/**
 * ��ʳ��Ʒ��
 * @author xx
 */
public class FoodInfo {

	/** Key */
	private int id;
	
	/** �̼�ID */
	private int shopid;
	
	/** ��Ʒ�� */
	private String name;
	
	/** ͼƬ���� */
	private String pic;
	
	/** ��Ʒ�۸� */
	private double price;
	
	/** �������� */
	private int salednum;
	
	/** �������� */
	private int love;
	
	/** ��Ʒ���� */
	private String description;
	
	/** ��Ʒ��ǩ */
	private String tag;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSalednum() {
		return salednum;
	}

	public void setSalednum(int salednum) {
		this.salednum = salednum;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
