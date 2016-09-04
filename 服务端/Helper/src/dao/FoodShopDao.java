package dao;

import java.util.List;

import bean.FoodShop;

public interface FoodShopDao {
	/**
	 * ���ݳ��������������ͻ�ȡ������Ϣ
	 * @param city  ������
	 * @param type  ��������
	 * @return
	 */
	public List<FoodShop> GetFoodShopByCityAndType(String city,String type);
	
	/**
	 * ���Ӳ�ѯ������Ϣ
	 * @param city  ������
	 * @param type  ��������
	 * @param sortcondition  ��������
	 * @param filtrate  ɸѡ����
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<FoodShop> GetTypeFoodShopByComplexCondition(String city,String type,String sortcondition,String filtrate,int pagenum);
	
	
	 /**
	 * ���ݳ����������������Լ�ҳ����ȡ������Ϣ
	 * @param city  ������
	 * @param type  ��������
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<FoodShop> GetFoodShopByCityAndTypeAndPage(String city,String type,int pagenum);
	
	 /**
	 * ���ݳ������Լ�ҳ����ȡ������Ϣ
	 * @param city  ������
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<FoodShop> GetFoodShopByCityAndPage(String city,int pagenum);
	
	/**
	 * �����������ͻ�ȡ������Ϣ
	 * @param type  ��������
	 * @return
	 */
	public List<FoodShop> GetFoodShopByType(String type);
	
	/**
	 * �����������ͻ�ȡ������Ϣ
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<FoodShop> GetFoodShopByPage(int pagenum);
	
	/**
	 * �������������Լ�ҳ����ȡ������Ϣ
	 * @param type  ��������
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<FoodShop> GetFoodShopByTypeAndPage(String type,int pagenum);
	
	/**
	 * ����ID��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	public FoodShop GetFoodShopById(int id);
	
	/**
	 * ����IDɾ��������Ϣ
	 * @param id
	 * @return
	 */
	public boolean Delete(int id);
	
	/**
	 * ����������Ƿ����
	 * @param shopname
	 * @return true : ���������Ѵ��� , false : �������������ڣ�����ע��
	 */
	public boolean CheckFoodShopName(String shopname);
	
	/**
	 * ע��������Ϣ
	 * @param foodshop
	 * @return true��ע��ɹ� ��false��ע��ʧ��
	 */
	public boolean Register(FoodShop foodshop);
}
