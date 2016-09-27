package com.example.testutil.view;

import android.os.Parcelable;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.snack.SnackBar;
import com.xuexiang.view.snack.SnackBar.OnMessageClickListener;

/** 
 * @author xx
 * @Date 2016-9-28 ����12:44:13 
 * @Copyright (c) 2016, xuexiangjys@163.com All Rights Reserved. 
 */
public class SnakbarActivity extends BaseActivity{

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_snakbar);
		initTitleBar(TAG);
		initView();
	}

	private void initView() {
	    SnackBar snackBar = new SnackBar.Builder(this)
        .withMessage("��ʾ��Ϣ")
        .withDuration(SnackBar.LONG_SNACK)
        .withActionMessage("ȷ��")
        .withOnClickListener(new OnMessageClickListener(){
			@Override
			public void onMessageClick(Parcelable token) {
				Toast("�����snackBar");
			}})
        .show();
	}

}
