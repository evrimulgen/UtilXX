package bean;

public class Student {
		private String sno;  //ѧ��
		private String sname;  //����
		private String ssex;  //�Ա�
		private int sage;  //����
		private String dno;  //����ѧԺ
		private String contact; //��ϵ�绰
		private String homeaddr; //��ͥסַ
		public String getSno() {
			return sno;
		}
		public void setSno(String sno) {
			this.sno = sno;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public String getSsex() {
			return ssex;
		}
		public void setSsex(String ssex) {
			this.ssex = ssex;
		}
		public int getSage() {
			return sage;
		}
		public void setSage(int sage) {
			this.sage = sage;
		}
		public String getDno() {
			return dno;
		}
		public void setDno(String dno) {
			this.dno = dno;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getHomeaddr() {
			return homeaddr;
		}
		public void setHomeaddr(String homeaddr) {
			this.homeaddr = homeaddr;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "ѧ��:"+sno+"������:"+sage+"���Ա�:"+ssex+"������ѧԺ:"+dno+"����ϵ�绰:"+contact+"����ͥסַ:"+homeaddr;
		}
    }


