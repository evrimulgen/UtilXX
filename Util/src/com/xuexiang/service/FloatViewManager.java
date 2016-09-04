package com.xuexiang.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xuexiang.util.app.BroadcastHelper;
import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.resource.RUtils;

/**
 * �����������
 * @author xx
 *
 */
public class FloatViewManager {
	private Context mContext;
	private ToastUtil mToastUtil;
	private static FloatViewManager instance;     
	//���帡�����ڲ���
    private LinearLayout mFloatLayout;
    private WindowManager.LayoutParams wmParams;
    //���������������ò��ֲ����Ķ���
    private WindowManager mWindowManager;
	
    private Button mFloatView;

	private FloatViewManager(Context context) {
		mContext = context;
		mToastUtil = ToastUtil.getInstance(mContext);
	}
	
    public static FloatViewManager getInstance(Context c) {  
        if (instance == null) {  
            instance = new FloatViewManager(c.getApplicationContext());  
        }  
        return instance;  
    }
    
    /**
   	 * ��ʼ����ӡ������
   	 */
   	public void initFloatView() {
		wmParams = new WindowManager.LayoutParams();
		//��ȡWindowManagerImpl.CompatModeWrapper
		mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		//����window type
		wmParams.type = LayoutParams.TYPE_PHONE; 
		//����ͼƬ��ʽ��Ч��Ϊ����͸��
        wmParams.format = PixelFormat.RGBA_8888; 
        //���ø������ڲ��ɾ۽���ʵ�ֲ���������������������ɼ����ڵĲ�����
        wmParams.flags = 
//          LayoutParams.FLAG_NOT_TOUCH_MODAL |
          LayoutParams.FLAG_NOT_FOCUSABLE
//          LayoutParams.FLAG_NOT_TOUCHABLE
          ;
        
        //������������ʾ��ͣ��λ��Ϊ����ö�
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;       
        // ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ
        wmParams.x = 0;
        wmParams.y = 0;
        
        //�����������ڳ�������  
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //��ȡ����������ͼ���ڲ���
        mFloatLayout = (LinearLayout) inflater.inflate(RUtils.getLayout(mContext, "service_floatview_layout"), null);       
        //�������ڰ�ť
        mFloatView = (Button)mFloatLayout.findViewById(RUtils.getId(mContext, "float_id"));
        
        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //���ü����������ڵĴ����ƶ�
        mFloatView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//getRawX�Ǵ���λ���������Ļ�����꣬getX������ڰ�ť������
				wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth()/2;
				//25Ϊ״̬���ĸ߶�
	            wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight()/2 - 25;
	             //ˢ��
	            mWindowManager.updateViewLayout(mFloatLayout, wmParams);
				return false;
			}
		});	
        
        mFloatView.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				mToastUtil.showToast("��������");
				BroadcastHelper.sendStartAppBroadCast(mContext);
			}
		});
   	}
   	
   	/**
   	 * ����������
   	 */
   	public void dismissFloatView() {
   		if(mFloatLayout != null) {
   			mWindowManager.removeView(mFloatLayout);
   		}
   	}
   	
   	/**
   	 * ��ʾ������
   	 */
   	public void showFloatView() {
   	    //���mFloatLayout
		if(mFloatLayout != null && wmParams != null) {
			 mWindowManager.addView(mFloatLayout, wmParams); 
		}
   	}

}
