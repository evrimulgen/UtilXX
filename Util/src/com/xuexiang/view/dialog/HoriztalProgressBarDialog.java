package com.xuexiang.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.customprogressbar.MyHoriztalProgressBar;

public class HoriztalProgressBarDialog extends AlertDialog {
	
	private TextView mTitle;
	private String mLoadingText;
	private LinearLayout mDialogLayout;
	private MyHoriztalProgressBar mHoriztalProgressBar;
	private int progress = 0;
	
	public HoriztalProgressBarDialog(Context context) {
		super(context, RUtils.getStyle(context, "custom_dialog"));
		
	}
	public HoriztalProgressBarDialog(Context context, String title) {
		super(context, RUtils.getStyle(context, "custom_dialog"));
	    mLoadingText = title;
	}
	
	public HoriztalProgressBarDialog(Context context, int theme) {
		super(context, theme);
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(RUtils.getLayout(getContext(), "dialog_horiztalprogressbar"));
        
        initView();
    }
	
	private void initView() {
		mDialogLayout = (LinearLayout) findViewById(RUtils.getId(getContext(), "dialoglayout"));
	    ViewGroup.LayoutParams dialoglayoutParams = mDialogLayout.getLayoutParams();
	    dialoglayoutParams.width = 3 * DisplayUtils.getScreenWidth(getContext()) / 4;    
	    
		mTitle = (TextView) findViewById(RUtils.getId(getContext(), "title"));
		mHoriztalProgressBar = (MyHoriztalProgressBar) findViewById(RUtils.getId(getContext(), "progressbar"));
	    if (TextUtils.isEmpty(mLoadingText)) {
	    	mTitle.setText("���ڼ�����...");
	    } else {
	    	mTitle.setText(mLoadingText);
	    }
	    mHoriztalProgressBar.setProgress(progress);
	    setCanceledOnTouchOutside(false);
	}

	public void setLoadingText(String loadingText) {
		mLoadingText = loadingText;
		if (mTitle != null) {
			  mTitle.setText(mLoadingText);
		}
	}

	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
		if (mHoriztalProgressBar != null) {
			mHoriztalProgressBar.setProgress(progress);
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