package com.example.testutil.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testutil.R;

/**  
 * ����ʱ�䣺2016-5-31 ����3:50:00  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�OrmliteActivity.java  
 **/
public class AhibernateActivity extends Activity{

	private Button user,patient;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_ahibernate);
	     
	     user = (Button) findViewById(R.id.user);
	     patient = (Button) findViewById(R.id.patient);
	     
	     user.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AhibernateActivity.this,UserActivity.class));
		}});
	     
	     patient.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
			    startActivity(new Intent(AhibernateActivity.this,PatientActivity.class));
		}});
	     
	 }
}
