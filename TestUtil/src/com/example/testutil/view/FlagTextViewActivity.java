package com.example.testutil.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.FluidLayout;

/**  
 * ����ʱ�䣺2016-6-10 ����12:27:10  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�FlagTextViewActivity.java  
 **/
public class FlagTextViewActivity extends BaseActivity implements OnClickListener{

	private FluidLayout fluidLayout;
    private Button btnCenter;
    private Button btnBtm;
    private Button btnTop;
    private Button btnNormal;
    private ToggleButton btnStroke;

    private String[] tags = new String[]{
            "ٻŮ�Ļ�", "����������", "����ս��", "������β��", "������ս", "�����మ��", "����������",
            "���Ǵ���̽", "�������", "������", "��Ĺ�ʼ�", "����", "����", "��������", "���ʳ���", "WIFI",
            "����", "��������", "��������", "�۷�ս��", "С˵", "��", "�ѹ�Ӣ", "����", "������", "������",
            "�ϼ�", "Java", "Android", "�ȸ�", "�ֻ�", "iPad", "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ",
            "ٻŮ�Ļ�", "����������", "����ս��", "������β��", "������ս", "�����మ��", "����������",
            "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad",
            "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����",
            "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ",
            "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad",
            "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����",
            "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "ֲ���ս��ʬ", "��ˮ��", "ֲ���ս��ʬ", "��ˮ��", "��ˮ��",
            "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����",
            "ٻŮ�Ļ�", "����������", "����ս��", "������β��", "������ս", "�����మ��", "����������",
            "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ",
            "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad",
            "��籦", "ǭ¿����", "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "�ֻ�", "iPad", "��籦", "ǭ¿����",
            "ˮ��", "ֲ���ս��ʬ", "��ˮ��", "ֲ���ս��ʬ", "��ˮ��", "ֲ���ս��ʬ", "��ˮ��", "��ˮ��"};

    private int gravity = Gravity.TOP;
    private boolean hasBg = true;
    private boolean isNormal = true;

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_flagtextview);
		
		initTitleBar("FlagTextViewActivity");
		
		initView();
	}

	private void initView() {
	    fluidLayout = (FluidLayout) findViewById(R.id.fluid_layout);
        btnCenter = (Button) findViewById(R.id.btn_center);
        btnBtm = (Button) findViewById(R.id.btn_btm);
        btnTop = (Button) findViewById(R.id.btn_top);
        btnStroke = (ToggleButton) findViewById(R.id.btn_stroke);
        btnNormal = (Button) findViewById(R.id.btn_normal);

        btnTop.setOnClickListener(this);
        btnBtm.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnNormal.setOnClickListener(this);

        btnStroke.setChecked(hasBg);

        btnStroke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hasBg = isChecked;
                genTag(hasBg);
            }
        });

        genTag(true);
	}

	@Override
    public void onClick(View v) {
        if (v == btnTop) {
            gravity = Gravity.TOP;
            isNormal = false;
            genTag(hasBg);

        } else if (v == btnCenter) {
            gravity = Gravity.CENTER;
            isNormal = false;
            genTag(hasBg);

        } else if (v == btnBtm) {
            gravity = Gravity.BOTTOM;
            isNormal = false;
            genTag(hasBg);

        } else if (v == btnNormal) {
            isNormal = !isNormal;
            genTag(hasBg);
        }
    }

	
	
	  private void genTag(boolean hasBg) {
	        fluidLayout.removeAllViews();
	        fluidLayout.setGravity(gravity);
	        for (int i = 0; i < tags.length; i++) {
	            TextView tv = new TextView(this);
	            tv.setText(tags[i]);
	            tv.setTextSize(20);

	            if (i == 12) {
	                if (!isNormal) {
	                    tv.setHeight(100);
	                    tv.setGravity(Gravity.CENTER);
	                }
	                tv.setBackgroundResource(R.drawable.text_bg_highlight);

	            } else {
	                if (hasBg) {
	                    tv.setBackgroundResource(R.drawable.text_bg);
	                }
	            }

	            if (i % 8 == 0) {
	                tv.setTextColor(Color.parseColor("#FF0000"));
	            } else if (i % 28 == 0) {
	                tv.setTextColor(Color.parseColor("#66CD00"));
	            } else {
	                tv.setTextColor(Color.parseColor("#666666"));
	            }

	            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
	                    ViewGroup.LayoutParams.WRAP_CONTENT,
	                    ViewGroup.LayoutParams.WRAP_CONTENT
	            );
	            params.setMargins(12, 12, 12, 12);
	            tv.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View view) {
						if(view instanceof TextView) {
							((TextView) view).setBackgroundResource(R.drawable.text_bg_highlight);
							Toast.makeText(getApplicationContext(), "�����" + ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
						}
						
					}
				});
	            fluidLayout.addView(tv, params);
	        }
	    }

}
