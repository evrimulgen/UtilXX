package bean;

/**  
 * �����֤��������
 * ����ʱ�䣺2016-3-6 ����12:12:02  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�IdentificationReq.java  
 **/
public class IdentificationReq {

	/** �û���*/
	private String loginname;
	/** ��ʵ����*/
	private String realname;
	/** ���֤*/
	private String identitycard;
	/** ���п���*/
	private String bankCardId;	
	/** ���п�����*/
	private String bankCardPassword;
	/** ������*/
	private String banktype;
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getBankCardPassword() {
		return bankCardPassword;
	}
	public void setBankCardPassword(String bankCardPassword) {
		this.bankCardPassword = bankCardPassword;
	}
	public String getBanktype() {
		return banktype;
	}
	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}
	
}
