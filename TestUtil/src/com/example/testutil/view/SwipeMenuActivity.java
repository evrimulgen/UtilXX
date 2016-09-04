package com.example.testutil.view;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.example.testutil.view.swipemenu.DifferentMenuActivity;
import com.example.testutil.view.swipemenu.SimpleActivity;
import com.xuexiang.app.BaseActivity;

/**  
 * ����ʱ�䣺2016-6-14 ����3:35:26  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�SwipeMenuActivity.java  
 **/
public class SwipeMenuActivity extends BaseActivity implements OnClickListener{

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_swipemenu);
		
		initTitleBar(TAG);
		
		
	}

	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
         case R.id.btn_simple:
             startActivity(new Intent(this, SimpleActivity.class));
             break;
         case R.id.btn_different:
             startActivity(new Intent(this, DifferentMenuActivity.class));
             break;
     }
	}

}
