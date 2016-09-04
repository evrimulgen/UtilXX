package com.example.testutil.view;

import java.util.ArrayList;

import android.widget.ListView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.timeline.TimeItem;
import com.xuexiang.view.timeline.TimeLineAdapter;

/**  
 * ����ʱ�䣺2016-7-8 ����8:51:41  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�TimeLineActivity.java  
 **/
public class TimeLineActivity extends BaseActivity {
	private ListView uplevel_list;
	private ArrayList<TimeItem> list;
	private TimeLineAdapter adapter;

	@Override
	public void onCreateActivity() {
        setContentView(R.layout.activity_timeline);
        initView();
	}
	
	private void initView() {
		uplevel_list = (ListView) findViewById(R.id.uplevel_list);
		initData();
	}

	private void initData() {
		list = new ArrayList<TimeItem>();
		for (int i = 1; i <= 100; i++) {
			TimeItem item = new TimeItem("2015/8/17", "����׬��" + i + "000��");			
			list.add(item);
		}
		adapter = new TimeLineAdapter(this, list);
		uplevel_list.setAdapter(adapter);
	}

}
