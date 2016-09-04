package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.swipelistview.SwipeDismissListView;
import com.xuexiang.view.swipelistview.SwipeDismissListView.OnDismissCallback;

/**  
 * ����ʱ�䣺2016-7-31 ����9:03:04  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�SwipeListViewActivity.java  
 **/
public class SwipeDismissListViewActivity extends BaseActivity {
	private SwipeDismissListView swipeDismissListView;
	private ArrayAdapter<String> adapter;
	private List<String> dataSourceList = new ArrayList<String>();
	@Override
	public void onCreateActivity() {		
        setContentView(R.layout.activity_swipedismisslistview);
        initTitleBar(TAG);
        init();
	}
	private void init() {
		swipeDismissListView = (SwipeDismissListView) findViewById(R.id.swipeDismissListView);
		for (int i = 0; i < 20; i++) {
			dataSourceList.add("����ɾ��" + i);
		}

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
                android.R.id.text1, dataSourceList);
		
		swipeDismissListView.setAdapter(adapter);
		
		swipeDismissListView.setOnDismissCallback(new OnDismissCallback() {
			
			@Override
			public void onDismiss(int dismissPosition) {
				 adapter.remove(adapter.getItem(dismissPosition)); 
			}
		});
		
		
		swipeDismissListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast(adapter.getItem(position));
			}
		});

	}

}
