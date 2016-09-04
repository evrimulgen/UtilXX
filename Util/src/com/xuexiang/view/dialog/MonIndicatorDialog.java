package com.xuexiang.view.dialog;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.dialog.monindicator.MonIndicator;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

/**  
 * ����ʱ�䣺2016-6-4 ����12:03:08  
 * ��Ŀ���ƣ�spotsloadingdialog  
 * @author xuexiang
 * �ļ����ƣ�MonIndicatorDialog.java  
 **/
public class MonIndicatorDialog extends AlertDialog{
    private TextView mTitle;
	private String mLoadingText;
	private MonIndicator mMonIndicator;
	private int[] colors;

	public MonIndicatorDialog(Context context) {
		super(context);
	}
	
	public MonIndicatorDialog(Context context,String title) {
		super(context);
	    mLoadingText = title;
	}
	
	public MonIndicatorDialog(Context context,String title, int[] colorlist) {
		super(context);
	    mLoadingText = title;
	    colors = colorlist;
	}
	
    public MonIndicatorDialog(Context context, int theme) {
	    super(context, theme);
	}

	public MonIndicatorDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
	    super(context, cancelable, cancelListener);
    }
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_monindicator"));
        
        initView();
    }

	private void initView() {
		 mTitle = (TextView) findViewById(RUtils.getId(getContext(), "title"));
	     mMonIndicator = (MonIndicator) findViewById(RUtils.getId(getContext(), "monIndicator"));
	     if (TextUtils.isEmpty(mLoadingText)) {
	    	 mTitle.setText("���ڼ�����...");
	     } else {
	    	 mTitle.setText(mLoadingText);
	     }
	     mMonIndicator.setColors(colors);	    
	     setCanceledOnTouchOutside(false);
	}
	
	public void setLoadingText(String loadingText) {
		mLoadingText = loadingText;
		if (mTitle != null) {
			 mTitle.setText(mLoadingText);
		}
	} 
	 
	public void setLoadingColors(int[] colorlist) {
		colors = colorlist;
		if (mMonIndicator != null) {
			mMonIndicator.setColors(colors);	    
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
