package dao;

import java.util.List;
import bean.RepairInfo;

public interface RepairDao {
	/**
	 * ����ά����Ϣ
	 * @param visitInfo
	 * @return true :����ά����Ϣ�ɹ� false:����ά����Ϣʧ��
	 */
	public boolean Add(RepairInfo repairInfo);
	

	/**
	 * ɾ��ά����Ϣ
	 * @param id Key
	 * @return true :ɾ��ά����Ϣ�ɹ� false:ɾ��ά����Ϣʧ��
	 */
	public boolean Delete(int id);
		
	/**
	 * ��ȡָ���û�����������ά����Ϣ
	 * @param loginname
	 * @param kind
	 * @return ָ���û�����������ά����Ϣ
	 */
	public List<RepairInfo> GetOwnRepairInfo(String loginname,String kind);
	
	
	/**
	 * ����id��ȡָ����ά����Ϣ
	 * @param id
	 * @return ָ����ά����Ϣ
	 */
	public RepairInfo GetRepairInfoById(int id);
	
	
	/**
	 * ����id�����ȡָ����ά����Ϣ
	 * @param idArray
	 * @return ά����Ϣ�ļ���
	 */
	public List<RepairInfo> GetRepairInfoByIdArray(int[] idArray);
	
	
	/**
	 * ��ȡ�����û��ķ�����ά����Ϣ�ļ���
	 * @param kind
	 * @return �����û�������ά����Ϣ�ļ���
	 */
	public List<RepairInfo> GetAllRepairInfo(String kind);
	
	/**
	 * ��ȡָ���û�����������ά����Ϣ
	 * @param loginname
	 * @param page
	 * @param kind
	 * @return ָ���û�����������ά����Ϣ
	 */
	public List<RepairInfo> GetOwnRepairInfoByPage(String loginname,int pagenum,String kind);
	
	/**
	 * ��ȡ�����û�������ά����Ϣ�ļ���
	 * @param pagenum
	 * @param kind
	 * @return �����û�������ά����Ϣ�ļ���
	 */
	public List<RepairInfo> GetAllRepairInfoByPage(int pagenum,String kind);
	
	/**
	 * ��ȡָ�����͵�ά����Ϣ�ļ���
	 * @param repairtype
	 * @param pagenum
	 * @param kind
	 * @return ָ�����͵�ά����Ϣ�ļ���
	 */
	public List<RepairInfo> GetTypeRepairInfoByPage(String repairtype,int pagenum);
}
