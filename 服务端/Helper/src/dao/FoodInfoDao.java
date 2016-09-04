package dao;

import java.util.List;

import bean.FoodInfo;
import bean.FoodType;

public interface FoodInfoDao {

	/**
	 * �����̼�ID��ȡʳƷ��Ϣ����
	 * @param shopid  �̼�ID
	 * @return ʳƷ��Ϣ����
	 */
	public List<FoodInfo> GetFoodInfoByShopId(int shopid);
	
	/**
	 * �����̼�ID��ʳƷ���ͻ�ȡʳƷ��Ϣ����
	 * @param shopid  �̼�ID
	 * @param tag  ʳƷ����
	 * @return ʳƷ��Ϣ����
	 */
	public List<FoodInfo> GetFoodInfoByShopIdAndType(int shopid,String tag);
	
	/**
	 * �����̼�ID��ȡ�̼�����ʳƷ��Ϣ����
	 * @param shopid  �̼�ID
	 * @return �̼�����ʳƷ��Ϣ����
	 */
	public List<FoodType> GetFoodTypeByShopId(int shopid);
	
	/**
	 * ����ID��ȡʳƷ��Ϣ
	 * @param id
	 * @return
	 */
	public FoodInfo GetFoodInfoById(int id);
	
	/**
	 * ����IDɾ��ʳƷ��Ϣ
	 * @param id
	 * @return
	 */
	public boolean Delete(int id);
	
	/**
	 * ���ʳƷ��Ϣ
	 * @param foodInfo
	 * @return true����ӳɹ� ��false�����ʧ��
	 */
	public boolean Add(FoodInfo foodInfo);
	
}
