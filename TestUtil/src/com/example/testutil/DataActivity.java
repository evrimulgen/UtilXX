package com.example.testutil;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.data.AhibernateActivity;
import com.example.testutil.data.OrmliteActivity;
import com.xuexiang.app.BaseActivity;

public class DataActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_data);
		initActionBar("���ݲ���������");
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_ahibernate:
			mToastUtil.showToast("�����AhibernateActivity");
        	intent.setClass(this, AhibernateActivity.class);
            startActivity(intent);
			break;
			
		case R.id.btn_ormlite:
			mToastUtil.showToast("�����OrmliteActivity");
        	intent.setClass(this, OrmliteActivity.class);
            startActivity(intent);
			break;

		default:
			break;
		}
	}
}
