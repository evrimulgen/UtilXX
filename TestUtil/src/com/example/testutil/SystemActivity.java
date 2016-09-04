package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.system.LogHelperActivity;
import com.xuexiang.app.BaseActivity;

/**  
 * ����ʱ�䣺2016-5-30 ����6:57:59  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�SystemActivity.java  
 **/
public class SystemActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_system);
	        
		 initActionBar("ϵͳ��������");
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_loghelper:
			mToastUtil.showToast("�����LogHelperActivity");
        	intent.setClass(this, LogHelperActivity.class);
            startActivity(intent);	
			break;

		default:
			break;
		}
		
	}

}
