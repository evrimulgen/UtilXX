package dao;

import bean.AppInfo;

public interface AppInfoDao {
	

   /**
    * �жϵ�ǰ�汾�Ƿ���Ҫ����
    * @param version �汾��
    * @return true����Ҫ���� false������Ҫ����
    */
    public boolean isNeedUpdate(String versionname);
    
    /**
     * ��ȡ����app�汾����Ҫ��Ϣ
     * @return
     */
    public AppInfo getNewAppInfo();
    
    
}
