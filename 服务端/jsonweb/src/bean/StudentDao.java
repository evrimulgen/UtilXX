package bean;
import java.util.List;
public interface StudentDao {
			public List<Student> getstudents();//���ѧ���б�
			public int add(Student studentbean); //���ѧ��
			public int delete(String sno); //ɾ��ѧ��
			public int update(Student studentbean); //�޸�ѧ����Ϣ
			public Student findBySno(String sno);//����ѧ�Ų�ѯѧ����Ϣ
}
