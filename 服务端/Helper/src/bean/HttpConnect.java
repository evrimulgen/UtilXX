package bean;

/**  
 * ����ʱ�䣺2016-2-28 ����7:26:12  
 * ��Ŀ���ƣ�helpertest  
 * @author xuexiang
 * �ļ����ƣ�HttpConnect.java  
 **/
public class HttpConnect {
	/** ����ҳ��*/
	private int pageNum;
	/** ��������*/
	private String sortCondition;
	/** ɸѡ����*/
	private String filtrate;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getSortCondition() {
		return sortCondition;
	}

	public void setSortCondition(String sortCondition) {
		this.sortCondition = sortCondition;
	}

	public String getFiltrate() {
		return filtrate;
	}

	public void setFiltrate(String filtrate) {
		this.filtrate = filtrate;
	}
}
