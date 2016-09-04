package com.xuexiang.util.app;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import com.xuexiang.util.common.ToastUtil;

import android.app.Activity;
import android.content.Context;

/**  
 * ����ʱ�䣺2016-2-3 ����9:43:00  
 * @author xuexiang
 * �ļ����ƣ�ActivityManager.java  
 **/
public class ActivityManager {
	 // Activityջ  
    private static Stack<Activity> activityStack;  
    // ����ģʽ  
    private static ActivityManager instance;  
   
    private ActivityManager() {  
    }  
   
    /** 
     * ��һʵ�� 
     */ 
    public static ActivityManager getInstance() {  
        if (instance == null) {  
            instance = new ActivityManager();  
        }  
        return instance;  
    }  
   
    /** 
     * ���Activity����ջ 
     */ 
    public void addActivity(Activity activity) {  
        if (activityStack == null) {  
            activityStack = new Stack<Activity>();  
        }  
        activityStack.add(activity);  
    }  
   
    /** 
     * ��ȡ��ǰActivity����ջ�����һ��ѹ��ģ� 
     */ 
    public Activity currentActivity() {  
        Activity activity = activityStack.lastElement();  
        return activity;  
    }  
   
    /** 
     * ������ǰActivity����ջ�����һ��ѹ��ģ� 
     */ 
    public void finishActivity() {  
        Activity activity = activityStack.lastElement();  
        finishActivity(activity);  
    }  
   
    /** 
     * ����ָ����Activity 
     */ 
    public void finishActivity(Activity activity) {  
        if (activity != null) {  
            activityStack.remove(activity);  
            activity.finish();  
            activity = null;  
        }  
    }  
   
    /** 
     * ����ָ��������Activity 
     */ 
    public void finishActivity(Class<?> cls) {  
        for (Activity activity : activityStack) {  
            if (activity.getClass().equals(cls)) {  
                finishActivity(activity);  
            }  
        }  
    }  
   
    /** 
     * ��������Activity 
     */ 
    public void finishAllActivity() {  
        for (int i = 0; i < activityStack.size(); i++) {  
            if (null != activityStack.get(i)) {  
                activityStack.get(i).finish();  
            }  
        }  
        activityStack.clear();  
    }  
   
    /** 
     * �˳�Ӧ�ó��� 
     */ 
    public void AppExit() {  
        try {  
            finishAllActivity();  
/*            ActivityManager activityMgr = (ActivityManager) context  
                    .getSystemService(Context.ACTIVITY_SERVICE);  
            activityMgr.killBackgroundProcesses(context.getPackageName());  */
            System.exit(0);  
        } catch (Exception e) {  
        }  
    }  
        
    /** 
     * ˫���˳�����  */
	private static Boolean isExit = false;  
	
	public void exitBy2Click(Context context) {  		
	   Timer tExit = null;  
	   if (isExit == false) {  
		   isExit = true; // ׼���˳�  
		   ToastUtil.getInstance(context).showToast("�ٰ�һ���˳�����"); 
		   tExit = new Timer(); 
		   tExit.schedule(new TimerTask() {  
			   @Override 
			   public void run() {   
				   isExit = false; // ȡ���˳� 
				   }  }, 2000); // ���2������û�а��·��ؼ�����������ʱ��ȡ�����ղ�ִ�е�����   
		   } else {  
			   AppExit();			   			  
			   android.os.Process.killProcess(android.os.Process.myPid());
		} 
	} 
}
