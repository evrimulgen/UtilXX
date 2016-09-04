package dao;

import bean.BankCard;


public interface BankCardDao {

	/**
	 * ���п�����
	 * @param bankCard
	 */
	public boolean Register(BankCard bankCard);
	
	/**
	 * ���п�������֤
	 * @param cardid  ���п�����
	 * @param password ����
	 */
	public boolean CheckCard(String cardid,String password);
	
	/**
	 * ���п����䶯����
	 * @param cardid  ���п�����
	 * @param moneyChange ���䶯
	 */
	public boolean UpdateMoneyChange(String cardid,double moneyChange);
	
	/**
	 * ���п�������
	 * @param cardid  ���п�����
	 * @param money ���
	 */
	public boolean UpdateMoney(String cardid,double money);
	
	/**
	 * ��ȡĳ�����п��Ľ��
	 * @param cardid  ���п�����
	 */
	public double GetCardMoney(String cardid);
	
	
	/**
	 * ���п������޸�
	 * @param cardid  ���п�����
	 * @param newpassword ������
	 */
	public boolean UpdatePassword(String cardid,String newpassword);
	
	/**
	 * ��ȡ���п���Ϣ
	 * @param cardid  ���п�����
	 */
	public BankCard GetCardInfoByCardId(String cardid);
	
	
	
}
