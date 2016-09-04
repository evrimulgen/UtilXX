package com.example.testutil.view.expandableview;

import java.util.ArrayList;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.expandableview.adapter.ExpandableGridAdapter;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ExpandableGridViewActivity extends BaseActivity {

	private ExpandableListView expandableGridView;

	ExpandableGridAdapter adapter;

	private ArrayList<IconItem> mList;
	private String[][] child_text_array;

	private int sign = -1;// �����б��չ��

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_expandable_gridview);
		initTitleBar(TAG);
		init();
		initModle();
		setListener();		
	}

	private void init() {
		expandableGridView = (ExpandableListView) findViewById(R.id.list);

		child_text_array = Model.EXPANDABLE_MOREGRIDVIEW_TXT;
	}

	private void setListener() {
		expandableGridView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (sign == -1) {
					// չ����ѡ��group
					expandableGridView.expandGroup(groupPosition);
					// ���ñ�ѡ�е�group���ڶ���
					expandableGridView.setSelectedGroup(groupPosition);
					sign = groupPosition;
				} else if (sign == groupPosition) {
					expandableGridView.collapseGroup(sign);
					sign = -1;
				} else {
					expandableGridView.collapseGroup(sign);
					// չ����ѡ��group
					expandableGridView.expandGroup(groupPosition);
					// ���ñ�ѡ�е�group���ڶ���
					expandableGridView.setSelectedGroup(groupPosition);
					sign = groupPosition;
				}
				return true;
			}
		});
	}

	private void initModle() {
		mList = new ArrayList<IconItem>();
		for (int i = 0; i < Model.EXPANDABLE_GRIDVIEW_TXT.length; i++) {
			mList.add(new IconItem(Model.EXPANDABLE_GRIDVIEW_TXT[i]));
		}
		adapter = new ExpandableGridAdapter(this, mList, child_text_array);
		expandableGridView.setAdapter(adapter);
	}

	

}
