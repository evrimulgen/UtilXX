package dao;

import java.util.List;

import bean.QiangYu;

public interface QiangYuDao {
	
	/**
	 * ����ǽ��
	 * @param qiangyu
	 * @return true :����ǽ��ɹ� false:����ǽ��ʧ��
	 */
	public boolean Add(QiangYu qiangyu);
	

	/**
	 * ɾ��ǽ��
	 * @param id Key
	 * @return true :ɾ��ǽ��ɹ� false:ɾ��ǽ��ʧ��
	 */
	public boolean Delete(int id);
		
	/**
	 * ��ȡָ���û��ķ���������ǽ��
	 * @param loginname
	 * @return ָ���û�������ǽ��
	 */
	public List<QiangYu> GetOwnQiangYu(String loginname);
	
	/**
	 * ��ȡָ���û��ķ���������ǽ��
	 * @param loginname
	 * @param page
	 * @return ָ���û�������ǽ��
	 */
	public List<QiangYu> GetOwnQiangYuByPage(String loginname,int pagenum);
	
	/**
	 * ����id��ȡָ����ǽ��
	 * @param id
	 * @return ָ����ǽ��
	 */
	public QiangYu GetQiangYuById(int id);
	
	
	/**
	 * ����id�����ȡָ����ǽ�Ｏ��
	 * @param idArray
	 * @return ǽ�Ｏ��
	 */
	public List<QiangYu> GetQiangYuByIdArray(int[] idArray);
	
	
	/**
	 * ��ȡ�����û��ķ�����ǽ��
	 * @return �����û��ķ�����ǽ��
	 */
	public List<QiangYu> GetAllQiangYu();
	
	/**
	 * ��ȡ�����û��ķ�����ǽ��
	 * @param pagenum
	 * @return �����û��ķ�����ǽ��
	 */
	public List<QiangYu> GetAllQiangYuByPage(int pagenum);
	
	/**
	 * �������ݸ��£�love����+1
	 * @param id
	 * @return 
	 */
	public boolean onClickLove(int id);
	

}
