package com.example.testutil.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.testutil.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.view.Colors;
import com.xuexiang.view.dialog.CircularProgressDialog;
import com.xuexiang.view.dialog.ConfirmDialog;
import com.xuexiang.view.dialog.ConfirmDialog.onConfirmDialogClickListener;
import com.xuexiang.view.dialog.CustomDialog;
import com.xuexiang.view.dialog.HoriztalProgressBarDialog;
import com.xuexiang.view.dialog.LoadingAnimatorDialog;
import com.xuexiang.view.dialog.LoadingView;
import com.xuexiang.view.dialog.MonIndicatorDialog;
import com.xuexiang.view.dialog.RoundProgressBarDialog;
import com.xuexiang.view.dialog.ShapeLoadingDialog;
import com.xuexiang.view.dialog.SpotsDialog;
import com.xuexiang.view.dialog.confirmdialog.ConfirmFragment;
import com.xuexiang.view.dialog.confirmdialog.ConfirmLayout;
import com.xuexiang.view.dialog.confirmdialog.ConfirmPopWindow;
import com.xuexiang.view.dialog.confirmdialog.OnDialogClickListener;


/**  
 * ����ʱ�䣺2016-5-31 ����9:21:32  
 * ��Ŀ���ƣ�UtilTest  
 * @author xuexiang
 * �ļ����ƣ�LoadingDialog.java  
 **/
public class LoadingDialogActivity extends BaseActivity implements OnClickListener, OnDialogClickListener{

	private LoadingView loadingview;
	private boolean flag = false;
	
	private ConfirmDialog mConfirmDialog;
	private Dialog mDialog;
	private ConfirmPopWindow mPopupWindow;
	private ConfirmLayout mConfirmLayout;
	private ConfirmFragment mConfirmFragment;
	
	
	private ShapeLoadingDialog mShapeLoadingDialog;
	private AlertDialog mAlertDialog;
	private RoundProgressBarDialog mRoundProgressBarDialog;
	private HoriztalProgressBarDialog mHoriztalProgressBarDialog;
	private int progress;
	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_loadingdialog);
        
	    initActionBar("���ؿ�������");
	    
	    initView();
	}

	private void initView() {
		loadingview = (LoadingView) findViewById(R.id.loadView);
		loadingview.setVisibility(View.GONE);
		
		mShapeLoadingDialog = new ShapeLoadingDialog(this, "������..");
		//mShapeLoadingDialog.setLoadingText("������..");
		mHandler = new Handler();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirmdialog:
			mConfirmDialog = new ConfirmDialog(mContext, "ȷ���˳�ϵͳ");
			mConfirmDialog.showDialog();
			
			mConfirmDialog.setOnClickListener(new onConfirmDialogClickListener(){
				@Override
				public void onClick(int mode) {
					if(mode == ConfirmDialog.CONFIRM){
						Toast("ȷ��");
					} else{
						Toast( "ȡ��");
					}
			}});
			break;
		case R.id.btn_confirmdialog1:
			mDialog = new com.xuexiang.view.dialog.confirmdialog.ConfirmDialog(mContext, "����", "������ConfirmDialog������", new OnDialogClickListener(){					
				@Override
				public void onSubmit() {
					Toast("���ȷ��");
				}
				@Override
				public void onCancel() {
					Toast("���ȡ��");						
				}
			});
			mDialog.show();
			break;
			
		case R.id.btn_confirmpopwindowdialog:
			mPopupWindow = new ConfirmPopWindow(mContext, "����", "������ConfirmPopWindow������", new OnDialogClickListener(){
				
				@Override
				public void onSubmit() {
					Toast("���ȷ��");
				}
				@Override
				public void onCancel() {
					Toast("���ȡ��");						
				}
			});
			mPopupWindow.showAtBottom(v);
			break;
			
		case R.id.btn_confirmlayoutdialog:
			mConfirmLayout = new ConfirmLayout(mContext, "����", "������ConfirmLayout������", new OnDialogClickListener(){					
				@Override
				public void onSubmit() {
					Toast("���ȷ��");
				}
				@Override
				public void onCancel() {
					Toast("���ȡ��");						
				}
			});
			mConfirmLayout.show();
			break;
			
		case R.id.btn_confirmfragmentdialog:
			mConfirmFragment = new ConfirmFragment().newInstance("����", "������ConfirmFragment������");
			mConfirmFragment.show(getSupportFragmentManager(), "ConfirmFragment");
			break;
			
		case R.id.btn_loading_dialog:			
			mShapeLoadingDialog.show();
			break;
			
		case R.id.btn_loading_view:
			flag = !flag;
			loadingview.setVisibility(flag?View.VISIBLE:View.INVISIBLE);			
			break;
			
		case R.id.btn_loading_Animator_dialog:
			final LoadingAnimatorDialog dialog = new LoadingAnimatorDialog(this, "���ڷ�������...");
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();			
			ThreadPoolManager.getInstance().addTask(new Runnable() {  				  
		            @Override  
		            public void run() {  
		                try {  
		                    Thread.sleep(10000);  //10���ر�  
		                    mActivityManager.currentActivity().runOnUiThread(new Runnable() {    
		                        public void run() {    
		                        	dialog.dismiss();  
		                        }    		                
		                    });    		                     
		                } catch (InterruptedException e) {  
		                    e.printStackTrace();  
		                }  		  
		            }  
		        });  
			break;
			
		case R.id.btn_SpotsDialog:
			mAlertDialog = new SpotsDialog(this,"�������ڷ������У�");
			mAlertDialog.show();
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					mAlertDialog.dismiss();
				}
			}, 5000);
			break;
			
		case R.id.btn_MonIndicatorDialog:
//			mAlertDialog = new MonIndicatorDialog(this);
//			mAlertDialog = new MonIndicatorDialog(this, "���ڷ������У�");
			mAlertDialog = new MonIndicatorDialog(this, "���ڷ������У�", new int[]{Colors.BLACK, Colors.GOLD, Colors.GREEN_LIGHT, Colors.YELLOW, Colors.RED_DARK});
			mAlertDialog.show();
            mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					mAlertDialog.dismiss();
				}
			}, 5000);
			break;
		case R.id.btn_CircularProgressDialog:
			mAlertDialog = new CircularProgressDialog(this);
//			mAlertDialog = new CircularProgressDialog(this, "���ڷ������У�");
			mAlertDialog.show();
            mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					mAlertDialog.dismiss();
				}
			}, 5000);
			break;
		case R.id.btn_RoundProgressBarDialog:
			mRoundProgressBarDialog = new RoundProgressBarDialog(this);
			mRoundProgressBarDialog.show();
			progress = 0;
			ThreadPoolManager.getInstance().addTask(new Runnable() {
				@Override
				public void run() {
					while (progress <= 100) {
						progress += 2;
						mRoundProgressBarDialog.setProgress(progress);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					mRoundProgressBarDialog.dismiss();
				}
			});
			break;
			
		case R.id.btn_HoriztalProgressBarDialog:
			mHoriztalProgressBarDialog = new HoriztalProgressBarDialog(this);
			mHoriztalProgressBarDialog.show();
			progress = 0;
			ThreadPoolManager.getInstance().addTask(new Runnable() {
				@Override
				public void run() {
					while (progress <= 100) {
						progress += 2;
						mHoriztalProgressBarDialog.setProgress(progress);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					mHoriztalProgressBarDialog.dismiss();
				}
			});
			break;
			
		case R.id.btn_CustomDialog:
			mAlertDialog = new CustomDialog(this, "����͸����xxxxxxxxxxxxxxxxxxxxxxx");
			mAlertDialog = new CustomDialog(this);
			mAlertDialog.show();
			
			if (mAlertDialog instanceof CustomDialog) {
				((CustomDialog) mAlertDialog).setCanceledByBackEvent(true);	     
//		        ((CustomDialog) mAlertDialog).setLoadingText("�����ڷ�����...");
			}		   

			break;
			
		default:
			break;
		}
		
	}

	@Override
	public void onCancel() {
		Toast("���ȡ��");
	}

	@Override
	public void onSubmit() {
		Toast("���ȷ��");
	}

}
