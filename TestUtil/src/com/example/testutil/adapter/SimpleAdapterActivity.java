package com.example.testutil.adapter;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.testutil.R;
import com.example.testutil.adapter.bean.ItemBean;
import com.xuexiang.util.adapter.baseadapterhelper.BaseAdapterHelper;
import com.xuexiang.util.adapter.baseadapterhelper.QuickAdapter;

public class SimpleAdapterActivity extends Activity
{

	private ListView mListView;
	private List<ItemBean> mDatas = new ArrayList<ItemBean>();

	private QuickAdapter<ItemBean> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapterlist);

		initDatas();

		mListView = (ListView) findViewById(R.id.id_lv_main);
		mAdapter = new QuickAdapter<ItemBean>(
				SimpleAdapterActivity.this, R.layout.adapter_listview_simpleitem, mDatas)
		{

			@Override
			protected void convert(BaseAdapterHelper helper, ItemBean item)
			{
				helper.setText(R.id.tv_title, item.getTitle());
				helper.setText(R.id.tv_describe, item.getDesc());
				helper.setText(R.id.tv_phone, item.getPhone());
				helper.setText(R.id.tv_time, item.getTime());
				// // helper.getView(R.id.tv_title).setOnClickListener(l)
			}
		};
//		mAdapter.showIndeterminateProgress(true);
		// ����������
		mListView.setAdapter(mAdapter);

	}

	private void initDatas()
	{
		ItemBean bean = null;
		bean = new ItemBean("��Ůһֻ", "�������ϼ�����һֻ����ʳ�ö�¥", "10086", "20130240122");
		mDatas.add(bean);
		bean = new ItemBean("��Ůһ��", "�������ϼ�����һ������ʳ����¥", "10086", "20130240122");
		mDatas.add(bean);
		bean = new ItemBean("�ȿ���һ��", "�������ϼ񵽱ȿ���һ������ʳ��һ¥", "10086", "20130240122");
		mDatas.add(bean);
		bean = new ItemBean("����һ��", "�������ϼ�xxxxxxxxxx����xxx", "10086",
				"20130240122");
		mDatas.add(bean);
	}

}
