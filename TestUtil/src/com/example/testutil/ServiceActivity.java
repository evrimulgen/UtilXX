package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.service.FloatServiceActivity;
import com.xuexiang.app.BaseActivity;

public class ServiceActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_service);
	        
		 initActionBar("�Զ���������");
			
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_floatviewservice:
			mToastUtil.showToast("�����FloatServiceActivity");
        	intent.setClass(this, FloatServiceActivity.class);
            startActivity(intent);			
			break;

		default:
			break;
		}
	}

	

}
