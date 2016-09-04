package dao;

import java.util.List;
import bean.Comment;

public interface CommentDao {
	
	/**
	 * ����ǽ��id��ȡĳһǽ���ȫ������
	 * @param qyid ǽ���id
	 * @return ���۵ļ���
	 */
	public List<Comment> GetAllComment(int qyid);
	
	/**
	 * ɾ������
	 * @param id Key
	 * @return true :ɾ���ɹ� false:ɾ��ʧ��
	 */
	public boolean Delete(int id);
	
	
	/**
	 * �������
	 * @param commentinfo
	 * @return true :������۳ɹ� false:�������ʧ��
	 */
	public boolean Add(Comment commentinfo);

}
