package com.example.testutil.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.testutil.R;
import com.example.testutil.ViewActivity;
import com.xuexiang.view.ProgressButton;

/**  
 * ����ʱ�䣺2016-6-26 ����4:44:56  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�ProgressButtonActivity.java  
 **/
public class ProgressButtonActivity extends Activity implements View.OnClickListener{

	private ProgressButton pb_button;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbutton);
        pb_button = (ProgressButton)findViewById(R.id.pb_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pb_btn:
                pb_button.startAnim();
                mHandler.postDelayed(new Runnable(){
					@Override
					public void run() {
					 pb_button.stopAnim(new ProgressButton.OnStopAnim() {
			                @Override
			                public void Stop() {
			                    Intent i=new Intent();
			                    i.setClass(ProgressButtonActivity.this,ViewActivity.class);
			                    startActivity(i);
			                }
			            });
					}            	
                },1500);
                break;
            default:
                break;
        }
    }

}
