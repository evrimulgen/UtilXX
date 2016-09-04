package com.example.testutil.view;

import java.util.Collections;
import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testutil.R;
import com.example.testutil.view.swipelistview.ListViewItemShowActivity;
import com.example.testutil.view.swipelistview.adapter.RecentAdapter;
import com.example.testutil.view.swipelistview.entity.RecentItem;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.swipelistview.BaseSwipeListViewListener;
import com.xuexiang.view.swipelistview.SwipeListView;
import com.xuexiang.view.swipelistview.pop.ActionItem;
import com.xuexiang.view.swipelistview.pop.QuickAction;

/**  
 * ����ʱ�䣺2016-8-3 ����8:26:40  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�SwipeListViewActivity.java  
 **/
public class SwipeListViewActivity extends BaseActivity {

	//listview��������
	private RecentAdapter mAdapter;
	//listview����Դ
	public  LinkedList<RecentItem> mRecentDatas;
	//�Զ����listview
	private SwipeListView mRecentListView;
	private TextView mEmpty;
	private int mClickItem = -1;
	// ���item����
	protected QuickAction mItemClickQuickAction = null;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_swipelistview);
		initTitleBar(TAG);
        initView();		
		initPop();
	}
	
	//����listviewĳһ��ʱ�����Ե���pop��QuickActionʵ�����Ǽ̳�PopupWindows
	private void initPop() {
		// ����Action
		mItemClickQuickAction = new QuickAction(this, QuickAction.VERTICAL);
		
		ActionItem pointItem = new ActionItem(1, "��ϸ��Ϣ", null);
		ActionItem areaItem = new ActionItem(2, "��Ϣ2", null);
		pointItem.setIcon(null);
		areaItem.setIcon(null);
		
		mItemClickQuickAction.addActionItem(pointItem);
		mItemClickQuickAction.addActionItem(areaItem);
		
		mItemClickQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {
				if (pos == 0) {
					Intent intent = new Intent(source.GetActionItemsGroup().getContext(),ListViewItemShowActivity.class);
					RecentItem item = new RecentItem();
					item = mRecentDatas.get(mClickItem);
					Bundle bundle = new Bundle();
					bundle.putInt("HeadImg", item.getHeadImg());
					bundle.putInt("NewNum", item.getNewNum());
					bundle.putString("Name", item.getName());
					intent.putExtras(bundle);
					startActivity(intent);					
				}
				//��������ʧ
				mItemClickQuickAction.dismiss();
			}
		});
	}
	
	private void initView() {
		mRecentListView = (SwipeListView) findViewById(R.id.recent_listview);
		mRecentDatas = getRecentList();
		mAdapter = new RecentAdapter(this, mRecentDatas, mRecentListView);
		mRecentListView.setAdapter(mAdapter);
		
		mEmpty = (TextView) findViewById(R.id.empty);
		mRecentListView.setEmptyView(mEmpty);
		mRecentListView
				.setSwipeListViewListener(new BaseSwipeListViewListener() {
					
					@Override
					public void onClickFrontView(View view, int position) {
						mClickItem = position;
						//��������
						mItemClickQuickAction.show(view);
					}

					@Override
					public void onClickBackView(int position) {
						mRecentListView.closeOpenedItems();// �رմ򿪵���
					}

					@Override
					public void onDismiss(int[] reverseSortedPositions) {
						for (int position : reverseSortedPositions) {
							mAdapter.remove(position);
						}
						
					}
				});
	}
	
	//��̬����Դ�Ļ�ȡ
	public LinkedList<RecentItem> getRecentList() {
		LinkedList<RecentItem> list = new LinkedList<RecentItem>();
		nameArray = getResources().getStringArray(R.array.jazzy_effects_ch);
		for (int i = 0; i < 20; i++) {
			int icon = heads[i];
			String name = nameArray[i];
			int num = numArray[i];
			RecentItem item = new RecentItem(name, icon, num);
			list.add(item);
		}
		Collections.sort(list);// ����
		return list;
	}
	
	public static  int[] heads = { R.drawable.h0, R.drawable.h1,
		R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
		R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9,
		R.drawable.h10, R.drawable.h11, R.drawable.h12, R.drawable.h13,
		R.drawable.h14, R.drawable.h15, R.drawable.h16, R.drawable.h17,
		R.drawable.h18, R.drawable.h19, R.drawable.h20, R.drawable.h21};
	
	public static  int[] numArray = { 21,67,65,8,54,67,23,78,98,54,67,23,67,423,23,67,90,12,34,56,978};
	
	public static String[] nameArray = new String[21];

}
