package bean;

/**
 * ��ʳ������
 * @author xx
 */
public class FoodShop extends ShopInfo {
	
	/** �������� */
	private float starnum;
	
	/** �������� */
	private int salednum;

	/** ���ͼ� */
	private int startingprice;
	
	/** ����� */
	private int deliverprice;
	
	/** ƽ���Ͳ�ʱ�� */
	private int delivertime;
	
	/** ����� */
	private Promotion foodpromotion;

	/** ����ʱ�� */
	private String dispatchtime;
	
	/** ���ͷ����� */
	private String serviceprovider;

	public float getStarnum() {
		return starnum;
	}

	public void setStarnum(float starnum) {
		this.starnum = starnum;
	}

	public int getSalednum() {
		return salednum;
	}

	public void setSalednum(int salednum) {
		this.salednum = salednum;
	}

	public int getStartingprice() {
		return startingprice;
	}

	public void setStartingprice(int startingprice) {
		this.startingprice = startingprice;
	}

	public int getDeliverprice() {
		return deliverprice;
	}

	public void setDeliverprice(int deliverprice) {
		this.deliverprice = deliverprice;
	}

	public int getDelivertime() {
		return delivertime;
	}

	public void setDelivertime(int delivertime) {
		this.delivertime = delivertime;
	}
	
	public Promotion getFoodpromotion() {
		return foodpromotion;
	}

	public void setFoodpromotion(Promotion foodpromotion) {
		this.foodpromotion = foodpromotion;
	}

	public String getDispatchtime() {
		return dispatchtime;
	}

	public void setDispatchtime(String dispatchtime) {
		this.dispatchtime = dispatchtime;
	}

	public String getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
	}
	
}
