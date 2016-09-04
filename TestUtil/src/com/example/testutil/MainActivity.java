package com.example.testutil;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.xuexiang.app.BaseActivity;

/**
 * ���Դ���
 */
public class MainActivity extends BaseActivity {
	
	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_main);
	        
	     initActionBar("������");
	}

	public void onClick(View v) {
	    Intent intent = new Intent();
		switch (v.getId()) {
        case R.id.btn_common:
        	mToastUtil.showToast("�����CommonActivity");
        	intent.setClass(this, CommonActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_data:
        	mToastUtil.showToast("�����DataActivity");
        	intent.setClass(this, DataActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_dapter:
        	mToastUtil.showToast("�����BaseAdapterListviewActivity");
        	intent.setClass(this, AdapterListviewActivity.class);
            startActivity(intent);
            break;           
        case R.id.btn_service:
        	mToastUtil.showToast("�����ServiceActivity");
        	intent.setClass(this, ServiceActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_file:
        	mToastUtil.showToast("�����FileActivity");
        	intent.setClass(this, FileActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_net:
        	mToastUtil.showToast("�����NetActivity");
        	intent.setClass(this, NetActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_resource:
        	mToastUtil.showToast("�����ResourceActivity");
        	intent.setClass(this, ResourceActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_system:
        	mToastUtil.showToast("�����SystemActivity");
        	intent.setClass(this, SystemActivity.class);
            startActivity(intent);
            break;
        case R.id.btn_view:
        	mToastUtil.showToast("�����ViewActivity");
        	intent.setClass(this, ViewActivity.class);
            startActivity(intent);
            break;
       }
	}

	/**  * �˵������ؼ���Ӧ  */
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if(keyCode == KeyEvent.KEYCODE_BACK) {    
			exitBy2Click(); 
		}  
		return false; 
	} 
}
