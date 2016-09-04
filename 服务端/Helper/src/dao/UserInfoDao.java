package dao;

import bean.UserInfo;

public interface UserInfoDao {	
	/**
	 * �û�ע��
	 * @param userbean
	 */
	public boolean Register(UserInfo userbean);

	/**
	 * ����û����Ƿ����
	 * @param loginname
	 * @return true : ���û��Ѵ���,false : ���û������ڣ�����ע��
	 */
	public boolean CheckUser(String loginname);
	/**
	 * ��½����
	 * @param userbean
	 */
	public boolean LoginCheck(String loginname,String password);
	
	/**
	 * ���ݵ�¼����ȡ�û����˻�����Ϣ
	 * @param loginname
	 */
	public UserInfo GetUserInfoByLoginname(String loginname);
	
	/**
	 * ͷ���ϴ�·���޸�
	 * @param loginname
	 * @param headphoto
	 */
	public boolean UpdateHeadPhoto(String loginname,String headphoto);
	
	/**
	 * �ǳ��޸�
	 * @param loginname
	 * @param nickname
	 */
	public boolean UpdateNickName(String loginname,String nickname);
	
	/**
	 * �Ա��޸�
	 * @param loginname
	 * @param sex
	 */
	public boolean UpdateSex(String loginname,String sex);
	
	/**
	 * ���ֻ��޸�
	 * @param loginname
	 * @param phone
	 */
	public boolean UpdatePhone(String loginname,String phone);
	
	/**
	 * �����޸�
	 * @param loginname
	 * @param password
	 */
	public boolean UpdatePassword(String loginname,String password);
	
	/**
	 * ����ǩ���޸�
	 * @param loginname
	 * @param signature
	 */
	public boolean UpdateSignature(String loginname,String signature);

	/**
	 * ʵ����֤
	 * @param loginname ��¼��
	 * @param realname ��ʵ����
	 * @param identitycard ���֤��
	 */
	public boolean UpdateIdentificationStatus(String loginname,String realname,String identitycard);
}
