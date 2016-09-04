package com.xuexiang.view.ViewpaperAndGridView.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xuexiang.util.resource.RUtils;
import com.xuexiang.util.resource.ResourceUtils;
import com.xuexiang.util.view.DisplayUtils;
import com.xuexiang.view.ViewpaperAndGridView.adapter.GridViewItemAdapter;
import com.xuexiang.view.ViewpaperAndGridView.adapter.ViewPagerAdapter;
import com.xuexiang.view.ViewpaperAndGridView.bean.ChannelItem;



public class GridViewGallery extends LinearLayout {

	private Context mContext;  
    /** ����ʵ��������� */  
    private List<ChannelItem> mList;  
    private ViewPager viewPager;  
    private LinearLayout ll_dot;  
    private ImageView[] dots;  
    /** ViewPager��ǰҳ */  
    private int currentIndex;  
    /** ViewPagerҳ�� */  
    private int viewPager_size;  
    /** Ĭ��һҳ12��item */  
    private int pageItemCount = 12;  
  
    /** ����ÿ��ҳ���GridView��ͼ */  
    private List<View> list_Views;  
  
    public GridViewGallery(Context context, AttributeSet attrs) { 
        super(context, attrs);  
        mContext = context;  
        mList = null;  
        initView();
    }  
    
	public GridViewGallery(Context context, List<ChannelItem> list) {  
        super(context);  
        mContext = context;  
        mList = list;  
        initView();  
        initDots();  
        setAdapter();  
    }  
  
    private void setAdapter() {  
        list_Views = new ArrayList<View>();  
        for (int i = 0; i < viewPager_size; i++) {  
            list_Views.add(getViewPagerItem(i));  
        }  
        viewPager.setAdapter(new ViewPagerAdapter(list_Views));         
    }  
  
    private void initView() {  
        View view = LayoutInflater.from(mContext).inflate(RUtils.getLayout(mContext, "channel_main"), null);  
        viewPager = (ViewPager) view.findViewById(RUtils.getId(mContext, "vPager"));  
        ll_dot = (LinearLayout) view.findViewById(RUtils.getId(mContext, "ll_channel_dots"));  
        addView(view);  
    }  
  
    // ��ʼ���ײ�СԲ��  
    private void initDots() {  
  
        // ������Ļ��ȸ߶ȼ���pageItemCount  
        int width = DisplayUtils.getScreenW(mContext);  
        int high = DisplayUtils.getScreenH(mContext);  
     
        int col = (width / 200) > 2 ? (width /200) : 3;
        int row = (high/400) > 4 ? (high/400):3;

        pageItemCount = col * row;  //ÿһҳ��װitem
        int t=1;
        if(mList.size() % pageItemCount==0){
        	t=0;
        }
        viewPager_size = mList.size() / pageItemCount + t;  
  
        if (0 < viewPager_size) {  
            ll_dot.removeAllViews();  
            if (1 == viewPager_size) {  
                ll_dot.setVisibility(View.GONE);  
            } else if (1 < viewPager_size) {  
                ll_dot.setVisibility(View.VISIBLE);  
                for (int j = 0; j < viewPager_size; j++) {  
                    ImageView image = new ImageView(mContext);  
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);  //dot�Ŀ��
                    params.setMargins(3, 0, 3, 0);  
                    image.setImageBitmap(ResourceUtils.getImageFromAssets(mContext, "play_hide.png"));  
                    ll_dot.addView(image, params);  
                }  
            }  
        }  
        if (viewPager_size != 1) {  
            dots = new ImageView[viewPager_size];  
            for (int i = 0; i < viewPager_size; i++) {
            	//�Ӳ��������dots����
                dots[i] = (ImageView) ll_dot.getChildAt(i);  
            }  
            currentIndex = 0;  //��ǰҳ 
            dots[currentIndex].setImageBitmap(ResourceUtils.getImageFromAssets(mContext, "play_display.png"));
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {  
  
                @Override  
                public void onPageSelected(int position) {  
                    setCurDot(position);  
                }  
  
                @Override  
                public void onPageScrolled(int arg0, float arg1, int arg2) {  
  
                }  
  
                @Override  
                public void onPageScrollStateChanged(int arg0) {  
  
                }  
            });  
        }  
    }  
  
    /** ��ǰ�ײ�СԲ�� */  
    private void setCurDot(int positon) {  
        if (positon < 0 || positon > viewPager_size - 1 || currentIndex == positon) {  
            return;  
        } 
        for(int i=0;i<dots.length;i++){
        	dots[i].setImageBitmap(ResourceUtils.getImageFromAssets(mContext, "play_hide.png"));
        }
        dots[positon].setImageBitmap(ResourceUtils.getImageFromAssets(mContext, "play_display.png"));
        currentIndex = positon;  
    }  
  
    //ViewPager��ÿ��ҳ���GridView����
    private View getViewPagerItem(int index) {  
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        View layout = inflater.inflate(RUtils.getLayout(mContext, "channel_viewpage_gridview"), null);  
        GridView gridView = (GridView) layout.findViewById(RUtils.getId(mContext, "vp_gv"));  
  
        int width = DisplayUtils.getScreenW(mContext);  
        int col = (width / 200) > 2 ? (width /200) : 3;
        
        gridView.setNumColumns(col);
  
        //ÿ��ҳ��GridView��Adpter
        GridViewItemAdapter adapter = new GridViewItemAdapter(mContext, mList, index, pageItemCount);  
  
        gridView.setAdapter(adapter);  
        gridView.setOnItemClickListener(new OnItemClickListener() {  

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (null != mList.get(position + currentIndex * pageItemCount).getOnClickListener())  
                    mList.get(position + currentIndex * pageItemCount).getOnClickListener().ongvItemClickListener(position + currentIndex * pageItemCount); 
			}  
        });  
        return gridView;  
    }  

}
