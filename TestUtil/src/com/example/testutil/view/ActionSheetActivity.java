package com.example.testutil.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.ActionSheet;
import com.xuexiang.view.ActionSheet.ActionSheetListener;

/**  
 * ����ʱ�䣺2016-5-29 ����7:17:49  
 * ��Ŀ���ƣ�TestUtil  
 * @author xuexiang
 * �ļ����ƣ�ActionSheetActivity.java  
 **/
public class ActionSheetActivity extends BaseActivity implements ActionSheetListener, OnClickListener{

	@Override
	public void onCreateActivity() {
		 setContentView(R.layout.activity_actionsheet);
	        
         initActionBar("ActionSheet");
	}


	public void showActionSheet() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("Item1", "Item2", "Item3", "Item4")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }
	@Override
	public void onDismiss(ActionSheet actionSheet, boolean isCancle) {
		Toast("dismissed isCancle = " + isCancle);
	}

	@Override
	public void onOtherButtonClick(ActionSheet actionSheet, int index) {
		  Toast.makeText(getApplicationContext(), "click item index = " + index,
	                Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		  switch (v.getId()) {
          case R.id.ios6:
              setTheme(R.style.ActionSheetStyleiOS6);
              break;
          case R.id.ios7:
              setTheme(R.style.ActionSheetStyleiOS7);
              break;
      }
      showActionSheet();
	}
}