package bean;
import java.util.List;

public interface UserinfoDao {
	public List<Userinfo> getUsers();//����û��б�
	public int add(Userinfo userbean); //����û�
	public int update(Userinfo userbean); //�޸��û���Ϣ
	public Userinfo Checkuser(Userinfo userbean);//����û��Ƿ����
	public Userinfo LoginCheck(Userinfo userbean);//�û���¼����

}
