package com.example.testutil.view;

import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.PatternView;

/**  
 * ����ʱ�䣺2016-5-29 ����11:58:50  
 * ��Ŀ���ƣ�TestUtil  
 * @author xuexiang
 * �ļ����ƣ�PatternViewActivity.java  
 **/
public class PatternViewActivity extends BaseActivity {

	private PatternView patternView;

	private String patternString;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_patternview);		
		
		initView();
	}
	private void initView() {
		 patternView = (PatternView) findViewById(R.id.patternView);
	        Toast.makeText(getApplicationContext(), "ENTER PATTERN", Toast.LENGTH_LONG).show();
	        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {
	            
	            @Override
	            public void onPatternDetected() {
	                if (patternString == null) {
	                    patternString = patternView.getPatternString();
	                    patternView.clearPattern();
	                    return;
	                }
	                if (patternString.equals(patternView.getPatternString())) {
	                    Toast("PATTERN CORRECT");
	                    return;
	                }
	                Toast("PATTERN NOT CORRECT");
	                patternView.clearPattern();
	            }
	        });

	}

}
