package dao;

import java.util.List;

import bean.FeedBackInfo;

public interface FeedBackDao {
    /**
     * �����û�������Ϣ
     * @param feedbackinfo
     * @return
     */
    public boolean Add(FeedBackInfo feedbackinfo);
    
    /**
     * ��ȡ�����û���������Ϣ����
     * @param pagenum ҳ��
     * @return
     */
    public List<FeedBackInfo> GetAllFeedBackInfoByPage(int pagenum);
    
    /**
     * ��ȡָ���û�������������Ϣ�ļ���
     * @param loginname �û���
     * @param pagenum ҳ��
     * @return
     */
    public List<FeedBackInfo> GetOwnFeedBackInfoByPage(String loginname,int pagenum);
    
    /**
	 * ɾ��������Ϣ
	 * @param id Key
	 * @return true :ɾ���ɹ� false:ɾ��ʧ��
	 */
	public boolean Delete(int id);
}
