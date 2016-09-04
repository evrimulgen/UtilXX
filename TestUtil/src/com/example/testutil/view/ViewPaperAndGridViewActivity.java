package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.ViewpaperAndGridView.bean.ChannelItem;
import com.xuexiang.view.ViewpaperAndGridView.bean.ChannelItem.onGridViewItemClickListener;
import com.xuexiang.view.ViewpaperAndGridView.view.GridViewGallery;

/**  
 * ����ʱ�䣺2016-8-1 ����9:12:14  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�ViewPaperAndGridViewActivity.java  
 **/
public class ViewPaperAndGridViewActivity extends BaseActivity {

	private GridViewGallery mGallery;//��Ų��ֵ���ͼ����
	private LinearLayout mLayout;
	private List<ChannelItem> list;//����
	
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_viewpaperandgridview);
		initTitleBar(TAG);
		list = getData();
		initViews();
	}
	
	//��ͼ��ʼ��
	private void initViews() {
		mGallery = new GridViewGallery(this, list);
		mLayout = (LinearLayout) findViewById(R.id.ll_gallery);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mLayout.addView(mGallery, params);
	}
	
	public List<ChannelItem> getData(){
		list = new ArrayList<ChannelItem>();
		
		for(int i= 0;i<48;i++){
			ChannelItem data = new ChannelItem(i+"��",R.drawable.ic_launcher,(i+100));
			list.add(data);
			data.setOnClickListener(new onGridViewItemClickListener(){
				@Override
				public void ongvItemClickListener(int position) {
					Toast.makeText(getApplicationContext(), "�����:"+position+"��", Toast.LENGTH_SHORT).show();
				}});
		}
		return list;
	}
}
