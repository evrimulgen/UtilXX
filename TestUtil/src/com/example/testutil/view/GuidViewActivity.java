package com.example.testutil.view;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.GuideView;

/**  
 * ����ʱ�䣺2016-7-27 ����8:38:14  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�GuidViewActivity.java  
 **/
public class GuidViewActivity extends BaseActivity {
	private ImageButton menu;
    private Button btnTest;
    private Button btnTest2;
    private GuideView guideView;
    private GuideView guideView3;
    private GuideView guideView2;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_guidview);

		menu = (ImageButton) findViewById(R.id.ib_menu);
        btnTest = (Button) findViewById(R.id.btn_test);
        btnTest2 = (Button) findViewById(R.id.btn_test2);

	}
	
	 private void setGuideView() {

	        // ʹ��ͼƬ
	        ImageView iv = new ImageView(this);
	        iv.setImageResource(R.drawable.img_new_task_guide);
	        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	        iv.setLayoutParams(params);

	        // ʹ������
	        TextView tv = new TextView(this);
	        tv.setText("��ӭʹ��");
	        tv.setTextColor(getResources().getColor(R.color.white));
	        tv.setTextSize(30);
	        tv.setGravity(Gravity.CENTER);

	        // ʹ������
	        final TextView tv2 = new TextView(this);
	        tv2.setText("��ӭʹ��2");
	        tv2.setTextColor(getResources().getColor(R.color.white));
	        tv2.setTextSize(30);
	        tv2.setGravity(Gravity.CENTER);


	        guideView = GuideView.Builder
	                .newInstance(this)
	                .setTargetView(menu)//����Ŀ��
	                .setCustomGuideView(iv)
	                .setDirction(GuideView.Direction.LEFT_BOTTOM)
	                .setShape(GuideView.MyShape.CIRCULAR)   // ����Բ����ʾ����
	                .setBgColor(getResources().getColor(R.color.shadow_guidview))
	                .setOnclickListener(new GuideView.OnClickCallback() {
	                    @Override
	                    public void onClickedGuideView() {
	                        guideView.hide();
	                        guideView2.show();
	                    }
	                })
	                .build();


	        guideView2 = GuideView.Builder
	                .newInstance(this)
	                .setTargetView(btnTest)
	                .setCustomGuideView(tv)
	                .setDirction(GuideView.Direction.LEFT_BOTTOM)
	                .setShape(GuideView.MyShape.ELLIPSE)   // ������Բ����ʾ����
	                .setBgColor(getResources().getColor(R.color.shadow_guidview))
	                .setOnclickListener(new GuideView.OnClickCallback() {
	                    @Override
	                    public void onClickedGuideView() {
	                        guideView2.hide();
	                        guideView3.show();
	                    }
	                })
	                .build();


	        guideView3 = GuideView.Builder
	                .newInstance(this)
	                .setTargetView(btnTest2)
	                .setCustomGuideView(tv2)
	                .setDirction(GuideView.Direction.LEFT_BOTTOM)
	                .setShape(GuideView.MyShape.RECTANGULAR)   // ���þ�����ʾ����
	                .setRadius(80)          // ����Բ�λ����͸������뾶��Ĭ����targetView����ʾ���εİ뾶������Ǿ��Σ����������þ���Բ�Ǵ�С
	                .setBgColor(getResources().getColor(R.color.shadow_guidview))
	                .setOnclickListener(new GuideView.OnClickCallback() {
	                    @Override
	                    public void onClickedGuideView() {
	                        guideView3.hide();
	                        guideView.show();
	                    }
	                })
	                .build();

	        guideView.show();
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        setGuideView();
	    }

}
