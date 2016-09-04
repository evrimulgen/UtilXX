package com.xuexiang.view.expandableview.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.view.MyView.MyGridView;
import com.xuexiang.view.expandableview.entity.IconItem;

public class ExpandableGridAdapter extends BaseExpandableListAdapter implements OnItemClickListener {

	private String[][] mChildTextArray;
	private Context mContext;
	private MyGridView mGridview;

	private List<IconItem> mList;
	private List<String> mChildArray;

	public ExpandableGridAdapter(Context context,
			List<IconItem> list, String[][] childtextarray) {
		mContext = context;
		mList = list;
		mChildTextArray = childtextarray;
	}

	/**
	 * ��ȡһ����ǩ����
	 */
	@Override
	public int getGroupCount() {
		return mList.size();
	}

	/**
	 * ��ȡһ����ǩ�¶�����ǩ������
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// ���ﷵ��1��Ϊ����ExpandableListViewֻ��ʾһ��ChildView��������չ��
		// ExpandableListViewʱ����ʾ��ChildCount������ͬ��GridView
		return 1;
	}

	/**
	 * ��ȡһ����ǩ����
	 */
	@Override
	public CharSequence getGroup(int groupPosition) {
		return mList.get(groupPosition).mTitle;
	}

	/**
	 * ��ȡһ����ǩ�¶�����ǩ������
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mChildTextArray[groupPosition][childPosition];
	}

	/**
	 * ��ȡһ����ǩ��ID
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * ��ȡ������ǩ��ID
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * ָ��λ����Ӧ������ͼ
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	/**
	 * ��һ����ǩ��������
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView = (LinearLayout) LinearLayout.inflate(mContext,
				RUtils.getLayout(mContext, "item_gridview_group_layout"), null);

		TextView group_title = (TextView) convertView
				.findViewById(RUtils.getId(mContext, "group_title"));
		if (isExpanded) {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					RUtils.getDrawable(mContext, "group_down"), 0);
		} else {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					RUtils.getDrawable(mContext, "group_up"), 0);
		}
		group_title.setText(mList.get(groupPosition).mTitle);
		return convertView;
	}

	/**
	 * ��һ����ǩ�µĶ�����ǩ��������
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = (RelativeLayout) RelativeLayout.inflate(mContext,
				RUtils.getLayout(mContext, "item_grid_child_layout"), null);
		mGridview = (MyGridView) convertView.findViewById(RUtils.getId(mContext, "gridview"));

		int size = mChildTextArray[groupPosition].length;
		mChildArray = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			mChildArray.add(mChildTextArray[groupPosition][i]);
		}
		mGridview.setAdapter(new GridTextAdapter(mContext, mChildArray));
		mGridview.setOnItemClickListener(this);
		return convertView;
	}

	/**
	 * ��ѡ���ӽڵ��ʱ�򣬵��ø÷���
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(mContext, "��ǰѡ�е���:" + mChildArray.get(position),
				Toast.LENGTH_SHORT).show();
	}
}