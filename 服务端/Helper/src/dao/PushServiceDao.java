package dao;

import java.util.List;

import push.PushMessage;

public interface PushServiceDao {
	/**
	 * �����������
	 * @param usercode �û�Ψһ��ʶ
	 * @param pushtime ��Ҫ���͵�ʱ��
	 * @return
	 */
	public List<String> getPushMode(String usercode,String pushtime);//�����������
	
	/**
	 * ���������Ϣ
	 * @param createtime ������Ϣ������ʱ��
	 * @return
	 */
	public List<PushMessage> getPushMessages(String createtime);//���������Ϣ
	
}
