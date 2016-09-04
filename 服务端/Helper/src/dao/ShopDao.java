package dao;

import java.util.List;

import bean.ShopInfo;

public interface ShopDao {
     
	 /**
	 * ���ݳ��������������ͻ�ȡ������Ϣ
	 * @param city  ������
	 * @param type  ��������
	 * @return
	 */
	public List<ShopInfo> GetShopByCityAndType(String city,String type);
	
	 /**
	 * ���ݳ����������������Լ�ҳ����ȡ������Ϣ
	 * @param city  ������
	 * @param type  ��������
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<ShopInfo> GetShopByCityAndTypeAndPage(String city,String type,int pagenum);
	
	/**
	 * �����������ͻ�ȡ������Ϣ
	 * @param type  ��������
	 * @return
	 */
	public List<ShopInfo> GetShopByType(String type);
	
	/**
	 * �������������Լ�ҳ����ȡ������Ϣ
	 * @param type  ��������
	 * @param pagenum  ҳ��
	 * @return
	 */
	public List<ShopInfo> GetShopByTypeAndPage(String type,int pagenum);
	
	/**
	 * ����ID��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	public ShopInfo GetShopById(int id);
	
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
	public boolean CheckShopName(String shopname);
	
	/**
	 * ע��������Ϣ
	 * @param shopinfo
	 * @return true��ע��ɹ� ��false��ע��ʧ��
	 */
	public boolean Register(ShopInfo shopinfo);
	
}
