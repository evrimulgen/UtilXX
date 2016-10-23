package com.xuexiang.app;
import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.util.app.ActivityManager;
import com.xuexiang.util.common.ToastUtil;
import com.xuexiang.util.data.sharedPreferences.SettingSharePreferenceUtil;
import com.xuexiang.util.data.sharedPreferences.UserSharePreferenceUtil;
import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.system.EditTextShakeHelper;
import com.xuexiang.view.TitleBar;
import com.xuexiang.view.popwindow.ActionItem;
import com.xuexiang.view.popwindow.TitlePopup;
import com.xuexiang.view.popwindow.TitlePopup.OnItemOnClickListener;


/** activity�Ļ���
  * @ClassName: BaseActivity
  * @Description: TODO
  * @author xx
  */
public abstract class BaseActivity extends FragmentActivity {
	public final String TAG = getClass().getSimpleName();
	
	public Context mContext;
	public BaseApplication myApplication;
	public UserSharePreferenceUtil mUserManager;	
	public SettingSharePreferenceUtil mSettingManager;
	public ActivityManager mActivityManager;
	public Handler mHandler;// ���������ݵĽ��
	
	public TextView mTitle;
	public ImageView mLeftMenu,mRightMenu;
	public TitleBar mTitleBar;
	public ImageView mTitleBarRightMenu;
    private TitlePopup mTitlePopup;
	
	public ProgressDialog mProgressDialog = null;  
	public ToastUtil mToastUtil;
	private Dialog mydialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		
		initManager();

		onCreateActivity();
	}
	
	private void initManager() {
		mContext = this;
		myApplication = BaseApplication.getInstance();
		mUserManager = UserSharePreferenceUtil.getInstance(this);
		mSettingManager = SettingSharePreferenceUtil.getInstance(this);
		mActivityManager = ActivityManager.getInstance();
		mActivityManager.addActivity(this);
		mToastUtil = ToastUtil.getInstance(this);
	}
	
	public abstract void onCreateActivity();

	/**
	 * ��ʼ��ActionBar
	 */
	public void initActionBar(String title){
		mTitle = (TextView)findViewById(MResource.getIdByName(mContext, "id", "tv_title"));
		mTitle.setText(title);
		mLeftMenu = (ImageView)findViewById(MResource.getIdByName(mContext, "id", "left_btn"));
		mLeftMenu.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
                 finish();
			}});
		mRightMenu = (ImageView)findViewById(MResource.getIdByName(mContext, "id", "right_menu"));
		mRightMenu.setVisibility(View.GONE);
	}
	
	
	/**
	 * ����TitleBar��ʼ��ActionBar
	 */
	public void initTitleBar(String title){
		mTitleBar = (TitleBar) findViewById(RUtils.getId(mContext, "title_bar"));
		mTitleBar.setImmersive(false);

		mTitleBar.setBackgroundColor(Color.parseColor("#64b4ff"));

		mTitleBar.setLeftImageResource(RUtils.getDrawable(mContext, "back_white"));
        mTitleBar.setLeftText("����");
        mTitleBar.setLeftTextColor(Color.WHITE);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });        
        mTitleBar.setTitle(title);
        mTitleBar.setTitleColor(Color.WHITE);
        mTitleBar.setSubTitleColor(Color.WHITE);
        mTitleBar.setDividerColor(Color.GRAY);
        mTitleBar.setActionTextColor(Color.WHITE);
        
	}
	
	/**
	 * ����TitleBar��ʼ��ActionBar
	 */
	public void initTitleBarWithRightMenu(String title, ArrayList<ActionItem> actionItemlist, OnItemOnClickListener rightMenuAction ){
		initTitleBar(title);
		
		mTitlePopup = new TitlePopup(mContext, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mTitlePopup.setAction(actionItemlist);
		mTitlePopup.setItemOnClickListener(rightMenuAction);
		mTitleBarRightMenu = (ImageView) mTitleBar.addAction(new TitleBar.ImageAction(RUtils.getDrawable(mContext, "more_message")){
			@Override
			public void performAction(View view) {
				mTitlePopup.show(view);
		}});   
	}
	
	
	/**
	 * ����EditText�����Ƿ�Ϊ��
	 * @param et  EditText�ؼ�
	 * @param msg Ϊ��ʱ����ʾ����
	 * @return
	 */
	public boolean IsEditTextEmpty(EditText et,String msg){
		boolean result = false;
		if(TextUtils.isEmpty(et.getText().toString())){
			if(mSettingManager.isAllowVibrate()){
			  new EditTextShakeHelper(mContext).shake(et);
			}
			Toast(msg);
			result = true;
		}
		return result;
	}
	
	public void showLoadingDialog(String title) {
		mProgressDialog = ProgressDialog.show(this,null,title);  
	}

	public void closeLoadingDialog() {
		if (mydialog != null && mydialog.isShowing()) {
			mydialog.dismiss();
		}
	}

	/**
	 * ��log��־
	 * @param msg
	 */
	public void ShowLog(String msg){
		Log.e(TAG, msg);
	}
	
	public void Toast(CharSequence hint){
	    Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
	}	
	
	protected void onDestroy(){
		mActivityManager.finishActivity(this);
		super.onDestroy();
	}
	
	/***
	 * ��̬����listview�ĸ߶� item �ܲ��ֱ�����linearLayout
	 * 
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	
	protected void exitBy2Click(){
		mActivityManager.exitBy2Click(mContext);
	}
	

}
