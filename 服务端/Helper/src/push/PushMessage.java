package push;

/**  
 * ��Ϣ������
 * ����ʱ�䣺2016-2-3 ����11:03:25  
 * ��Ŀ���ƣ�pollingService  
 * @author xuexiang
 * �ļ����ƣ�PushMessage.java  
 **/
public class PushMessage {

	    private String Title;
	    private String message;
	    private String url;
	    private String pushmode;
	    
	    private String createtime;
		
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		public String getPushmode() {
			return pushmode;
		}
		public void setPushmode(String pushmode) {
			this.pushmode = pushmode;
		}
}
