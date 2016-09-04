package dao;

import java.util.List;

import bean.VisitInfo;

public interface VisitDao {

	/**
	 * �������ŷ�����Ϣ
	 * @param visitInfo
	 * @return true :�������ŷ���ɹ� false:�������ŷ���ʧ��
	 */
	public boolean Add(VisitInfo visitInfo);
	

	/**
	 * ɾ�����ŷ���
	 * @param id Key
	 * @return true :ɾ�����ŷ���ɹ� false:ɾ�����ŷ���ʧ��
	 */
	public boolean Delete(int id);
		
	/**
	 * ��ȡָ���û��������������ŷ������Ϣ
	 * @param loginname
	 * @return ָ���û��������������ŷ������Ϣ
	 */
	public List<VisitInfo> GetOwnVisitInfo(String loginname);
	
	/**
	 * ��ȡָ���û��������������ŷ������Ϣ
	 * @param loginname
	 * @param page
	 * @return ָ���û��������������ŷ������Ϣ
	 */
	public List<VisitInfo> GetOwnVisitInfoByPage(String loginname,int pagenum);
	
	/**
	 * ����id��ȡָ�������ŷ������Ϣ
	 * @param id
	 * @return ָ�������ŷ������Ϣ
	 */
	public VisitInfo GetVisitInfoById(int id);
	
	
	/**
	 * ����id�����ȡָ�������ŷ�����Ϣ�ļ���
	 * @param idArray
	 * @return ���ŷ�����Ϣ�ļ���
	 */
	public List<VisitInfo> GetVisitInfoByIdArray(int[] idArray);
	
	
	/**
	 * ��ȡ�����û��ķ��������ŷ�����Ϣ�ļ���
	 * @return �����û����������ŷ�����Ϣ�ļ���
	 */
	public List<VisitInfo> GetAllVisitInfo();
	
	/**
	 * ��ȡ�����û����������ŷ�����Ϣ�ļ���
	 * @param pagenum
	 * @return �����û����������ŷ�����Ϣ�ļ���
	 */
	public List<VisitInfo> GetAllVisitInfoByPage(int pagenum);
	
	/**
	 * ��ȡָ�����͵����ŷ�����Ϣ�ļ���
	 * @param visittype
	 * @param pagenum
	 * @return ָ�����͵����ŷ�����Ϣ�ļ���
	 */
	public List<VisitInfo> GetTypeVisitInfoByPage(String visittype,int pagenum);
	
	/**
	 * �������ݸ��£�love����+1
	 * @param id
	 * @return 
	 */
	public boolean onClickLove(int id);
}
