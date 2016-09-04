package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.TitleBar;
import com.xuexiang.view.popwindow.ActionItem;
import com.xuexiang.view.popwindow.PopWindow;
import com.xuexiang.view.popwindow.PopWindow.OnItemSelectedListerner;
import com.xuexiang.view.popwindow.TitlePopup.OnItemOnClickListener;

/**  
 * ����ʱ�䣺2016-6-8 ����9:49:23  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�TitleBarActivity.java  
 **/
public class TitleBarActivity extends BaseActivity implements OnClickListener {

//	 private ImageView mCollectView;
//	 private boolean mIsSelected;
	 ArrayList<ActionItem> Itemlist = new ArrayList<ActionItem>();
	    
	 private Button btShare;
     private PopWindow mPopWindow;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_titlebar);
		
//		initTitleBar("TitleBarActivity");
		
		btShare = (Button) findViewById(R.id.bt_share);
        btShare.setOnClickListener(this);
        mPopWindow = new PopWindow(this,"����");
		
		initActionItem();
		initTitleBarWithRightMenu("TitleBarActivity", Itemlist, new OnItemOnClickListener() {
			@Override
			public void onItemClick(ActionItem item, int position) {
				Toast("������"+ item.mTitle);
			}
		});
		
//		initActionBar();
	}
	private void initActionItem() {
		Itemlist.add(new ActionItem(mContext, "��ҳ", R.drawable.mm_title_btn_home_normal));
		Itemlist.add(new ActionItem(mContext, "���ﳵ", R.drawable.mm_title_btn_cart_normal));
		Itemlist.add(new ActionItem(mContext, "ɨһɨ",  R.drawable.mm_title_btn_qrcode_normal));
		Itemlist.add(new ActionItem(mContext, "�ҵ��־�", R.drawable.mm_title_btn_account_normal));
	}

	private void initActionBar() {
		  
//	        mCollectView = (ImageView) mTitleBar.addAction(new TitleBar.ImageAction(R.drawable.collect) {
//	            @Override
//	            public void performAction(View view) {
//	                Toast("������ղ�");
//	                mCollectView.setImageResource(R.drawable.publish);
//	                mTitleBar.setTitle(mIsSelected ? "��������\n����Ȧ" : "��������");
//	                mIsSelected = !mIsSelected;
//	            }
//	        });

	        mTitleBar.addAction(new TitleBar.TextAction("����") {
	            @Override
	            public void performAction(View view) {
	                Toast("����˷���");
	            }
	        });
	}
	
	 private List<ActionItem> getShareInfo() {
	    	List<ActionItem> list = new ArrayList<ActionItem>();
	    	list.add(new ActionItem(mContext, "΢��", R.drawable.share_weixin));
	    	list.add(new ActionItem(mContext, "����Ȧ", R.drawable.share_momment));
	    	list.add(new ActionItem(mContext, "����΢��", R.drawable.share_sina));
	    	list.add(new ActionItem(mContext, "QQ", R.drawable.share_qq));
	    	list.add(new ActionItem(mContext, "QQ�ռ�", R.drawable.share_qzeon));
	    	list.add(new ActionItem(mContext, "����", R.drawable.share_message));   	
	    	return list;
	 }
	@Override
	public void onClick(View v) {
		mPopWindow.setShareInfo(getShareInfo());
    	mPopWindow.setOnItemSelectedListerner(new OnItemSelectedListerner(){
			@Override
			public void onSelected(ActionItem shareInfo) {
				Toast.makeText(getApplicationContext(), "�����ˣ�" + shareInfo.mTitle, Toast.LENGTH_SHORT).show();
				//mPopWindow.hide();
			}
		});
    	mPopWindow.show();
	}

}
