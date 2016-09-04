package dao;

import bean.Promotion;

public interface PromotionDao {
	
	/**
	 * ע�������Ϣ
	 * @param promotion
	 * @return true��ע��ɹ� ��false��ע��ʧ��
	 */
	public boolean Add(Promotion promotion);
	
	/**
	 * ɾ��������Ϣ��ȡ���������
	 * @param id
	 * @return true��ɾ���ɹ� ��false��ɾ��ʧ��
	 */
	public boolean delete(int id);
	
	/**
	 * ���´�����Ϣ���޸Ĵ�����Ϣ��ȡ��ĳһ�������������Ϊ""��
	 * @param promotion
	 * @return true�����³ɹ� ��false������ʧ��
	 */
	public boolean updatePromotionInfo(Promotion promotion);

	/**
	 * ��ȡ������Ϣ
	 * @param shopid
	 * @return true����ȡ�ɹ� ��false����ȡʧ��
	 */
	public Promotion getPromotionByShopId(int shopid);

	

}
