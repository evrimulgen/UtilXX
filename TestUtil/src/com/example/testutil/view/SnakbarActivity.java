package com.example.testutil.view;

import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.app.BaseApplication;
import com.xuexiang.util.view.FlexibleToast;
import com.xuexiang.view.snack.SnackBar;
import com.xuexiang.view.snack.SnackBar.OnMessageClickListener;
import com.xuexiang.view.snack.SnackBar.OnVisibilityChangeListener;

/**
 * @author xx
 * @Date 2016-9-28 ����12:44:13
 * @Copyright (c) 2016, xuexiangjys@163.com All Rights Reserved.
 */
public class SnakbarActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_snakbar);
		initTitleBar(TAG);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sample_message:
			new SnackBar.Builder(this)
			.withMessage("��ʾ��Ϣ")
			.withDuration(SnackBar.MED_SNACK)
			.show();
			break;
		case R.id.message_click:
			new SnackBar.Builder(this)
			.withMessage("�����߳�����ʾToast")
			.withDuration(SnackBar.LONG_SNACK)
			.withActionMessage("ȷ��")
			.withOnClickListener(new OnMessageClickListener() {
				@Override
				public void onMessageClick(Parcelable token) {
					 new Thread(new Runnable() {
		                    @Override
		                    public void run() {
		                    	FlexibleToast.Builder builder = new FlexibleToast.Builder(mContext).setGravity(FlexibleToast.GRAVITY_CENTER).setFirstText("�в�Toast").setSecondText("�����snackBar");
		                        BaseApplication.toastShowByBuilder(builder);
		                    }
		                }).start();
				}
			}).show();
//			FlexibleToast toast = new FlexibleToast(mContext);
//			toast.toastShow(builder);
			break;
		case R.id.actionIcon_click:
			new SnackBar.Builder(this)
			.withMessage("��ʾ��Ϣ")
			.withDuration(SnackBar.LONG_SNACK)
			.withActionMessage("ȷ��")
			.withActionIconId(R.drawable.icn_switch)
			.withOnClickListener(new OnMessageClickListener() {
				@Override
				public void onMessageClick(Parcelable token) {
					Toast("�����snackBar");
				}
			})
			.withVisibilityChangeListener(new OnVisibilityChangeListener(){
				@Override
				public void onHide(int stackSize) {
					Toast("onHide:" + stackSize);
				}
				@Override
				public void onShow(int stackSize) {
					Toast("onShow:" + stackSize);
				}})
			.show();
			break;

		default:
			break;
		}
	}

}
