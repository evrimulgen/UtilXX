package bean;

import java.util.List;

public interface ShopDao {
	public List<Shop> getshops();//�����Ʒ�б�
	public int add(Shop shopbean); //�����Ʒ
	public int delete(int id); //ɾ����Ʒ
	public int update(Shop shopbean); //�޸���Ʒ��Ϣ
	public Shop findById(int id);//������Ų�ѯ��Ʒ��Ϣ
}
