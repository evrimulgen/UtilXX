package push;

/**  
 * ��ѯ����������
 * ����ʱ�䣺2016-2-3 ����11:20:03  
 * ��Ŀ���ƣ�pollingService  
 * @author xuexiang
 * �ļ����ƣ�PushServiceReq.java  
 **/
public class PushServiceReq {
	/** �������ݵ�ʱ��*/
	private String RepCreateDate;
	/** ��������*/
	private String RequestType;
	/** �û��豸Ψһ��*/
	private String UserCode;
	public String getRepCreateDate() {
		return RepCreateDate;
	}
	public void setRepCreateDate(String repCreateDate) {
		RepCreateDate = repCreateDate;
	}
	public String getRequestType() {
		return RequestType;
	}
	public void setRequestType(String requestType) {
		RequestType = requestType;
	}
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
}
