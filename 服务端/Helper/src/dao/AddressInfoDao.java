package dao;

import java.util.List;

import bean.AddressInfo;


public interface AddressInfoDao {
	
	/**
	 * ��ӵ�ַ
	 * @return true :���ӵ�ַ�ɹ� false:���ӵ�ַʧ��
	 */
	public boolean Add(AddressInfo addressinfo);
	

	/**
	 * ɾ����ַ
	 * @param id Key
	 * @return true :ɾ����ַ�ɹ� false:ɾ����ַʧ��
	 */
	public boolean Delete(int id);
	
	/**
	 * �޸ĵ�ַ
	 * @param addressinfo
	 * @return true :�޸ĵ�ַ�ɹ� false:�޸ĵ�ַʧ��
	 */
	public boolean Update(AddressInfo addressinfo);
	
	/**
	 * ��ȡָ���û������е�ַ
	 * @param loginname
	 * @return ָ���û������е�ַ
	 */
	public List<AddressInfo> GetAllAddress(String loginname);
	
	/**
	 * �޸�Ĭ�ϵ�ַ
	 * @param id
	 * @param loginname
	 * @return true :�޸ĵ�ַ�ɹ� false:�޸ĵ�ַʧ��
	 */
	public boolean UpdateIsdefault(int id,String loginname);
	
	/**
	 * �жϸ��û���ǰ�Ƿ�����Ĭ�ϵ�ַ
	 * @param loginname
	 * @return true :�� false:��
	 */
	public boolean isHasdefaultAddress(String loginname);

}
