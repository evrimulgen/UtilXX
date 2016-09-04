package com.xuexiang.view.dialog;

import com.xuexiang.util.resource.RUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

/**  
 * ����ʱ�䣺2016-6-4 ����1:32:33  
 * ��Ŀ���ƣ�spotsloadingdialog  
 * @author xuexiang
 * �ļ����ƣ�CircularProgressDialog.java  
 **/
public class CircularProgressDialog extends AlertDialog {
	private TextView mTitle;
	private String mLoadingText;

	public CircularProgressDialog(Context context) {
		super(context, RUtils.getStyle(context, "custom_dialog"));
		
	}

	public CircularProgressDialog(Context context,String title) {
		super(context, RUtils.getStyle(context, "custom_dialog"));
	    mLoadingText = title;
	}
	
	public CircularProgressDialog(Context context, int theme) {
		super(context, theme);
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_circularprogress"));
        
        initView();
    }

	private void initView() {
		 mTitle = (TextView) findViewById(RUtils.getId(getContext(), "title"));
	     if (TextUtils.isEmpty(mLoadingText)) {
	    	 mTitle.setText("���ڼ�����...");
	     } else {
	    	 mTitle.setText(mLoadingText);
	     }
	         
	     setCanceledOnTouchOutside(false);
	}

	public void setLoadingText(String loadingText) {
		mLoadingText = loadingText;
		if (mTitle != null) {
			  mTitle.setText(mLoadingText);
		}
	}
	
    private boolean mIsNeedCanceled;
	
	public void setCanceledByBackEvent(boolean isNeedCanceled) {
    	mIsNeedCanceled = isNeedCanceled;
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
            	if (mIsNeedCanceled) {
            		if (isShowing()) {
            			dismiss();
            		}
            	}
                break;
        }
        return true;
    }

}
