package com.example.testutil.view;

import android.util.Log;
import android.widget.FrameLayout;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.GestureContentView;
import com.xuexiang.view.Gesture.GestureDrawline.GestureCallBack;

/**  
 * ����ʱ�䣺2016-5-25 ����11:42:27  
 * ��Ŀ���ƣ�GesturePassword  
 * @author xuexiang
 * �ļ����ƣ�ChangePasswordActivity.java  
 **/
public class ChangePasswordActivity extends BaseActivity {
	private FrameLayout mContainer;
	private GestureContentView mContentView;
	private boolean isFirstInput = true;
	private String mFirstPassword;
	private int viewWidth;

	@Override
	public void onCreateActivity() {
        setContentView(R.layout.activity_changepassword);
		
		initView();
	}	

	private void initView() {
		
		viewWidth = DisplayUtils.getScreenW(this) * 9 / 10;
		mContainer = (FrameLayout) findViewById(R.id.container);
		
		refreshGestureContentView();
	}
	
	private void refreshGestureContentView() {
		mContentView = new GestureContentView(this, viewWidth, mSettingManager.isNeedVerify() , mSettingManager.getPassword(),
				new GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {
						Log.e("password:", inputCode);
						if (isFirstInput) {
							mFirstPassword = inputCode;
							mContentView.clearDrawlineState(0L);
							isFirstInput = false;
							Toast("��������һ��ȷ�ϣ�");
						} else {
							if (inputCode.equals(mFirstPassword)) {
								mSettingManager.setPassword(mFirstPassword);
								Toast("������������ɹ�");
								mContentView.clearDrawlineState(0L);
								finish();
							} else {
								mContentView.clearDrawlineState(500L);
								Toast("��֤ʧ�ܣ������������������룡");
								isFirstInput = true;
							}
						}
						
					}

					@Override
					public void checkSuccess() {
						Toast("��֤�ɹ�,�����������룡");			
						mSettingManager.setPassword("");
						mContentView.clearDrawlineState(0L);
						refreshGestureContentView();
					}

					@Override
					public void checkFail() {
						Toast("��֤ʧ�ܣ�");
						mContentView.clearDrawlineState(500L);
					}
				});
		mContentView.setParentView(mContainer);
	}
	
	

}
