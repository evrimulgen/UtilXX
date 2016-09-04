package dao;

import java.util.List;

import bean.BankCard;
import bean.HelperPay;

public interface HelperPayDao {

	/**
	 * ע���־ӱ��˻�
	 * @param bankCard
	 */
	public boolean Register(HelperPay helperPay);
	
	/**
	 * �־ӱ�������֤
	 * @param username �˻���
	 * @param password ����
	 */
	public boolean CheckPay(String username,String password);
	
	/**
	 * �־ӱ���Ǯ�䶯����
	 * @param username �˻���
	 * @param moneyChange ���䶯
	 */
	public boolean UpdateMoneyChange(String username,double moneyChange);
	
	/**
	 * �־ӱ���Ǯ����
	 * @param username �˻���
	 * @param money ���
	 */
	public boolean UpdateMoney(String username,double money);
	
	/**
	 * ��ȡ�־ӱ��ϵ���Ǯ���
	 * @param username  �˻���
	 */
	public double GetPayMoney(String username);
	
	/**
	 * �־ӱ������޸�
	 * @param username  �˻���
	 * @param newpassword ������
	 */
	public boolean UpdatePassword(String username,String newpassword);
	
	/**
	 * ��ȡ�־ӱ���Ϣ
	 * @param username  �˻���
	 */
	public HelperPay GetHelperPayInfoByUsername(String username);
	
	/**
	 * �������п���
	 * @param username  �˻���
	 * @param cardid  �����п���
	 */
	public boolean AddBankCardBind(String username,String newCardid);
	
	/**
	 * �������п�����Ϣ
	 * @param username �˻���
	 * @param newbankcardlist �����п�����
	 */
	public boolean UpdateBankcardlist(String username,String newbankcardlist);
	
	/**
	 * ��ȡ�־ӱ��ϵ����п�����Ϣ
	 * @param username  �˻���
	 */
	public String GetBankcardListStr(String username);
	
	/**
	 * ��ȡ�־ӱ��ϵ����п�����Ϣ
	 * @param username  �˻���
	 */
	public List<BankCard> GetBankcardList(String username);
	
}
