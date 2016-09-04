package com.xuexiang.util.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

/**
 * �Ի�����ʾ�Ĺ�����
 *
 */
public class DialogUtil {
	
	public static ProgressDialog getProgressDia(Activity context,String message){
		ProgressDialog progressdialog = ProgressDialog.show(context, "Loading!",message);
		return progressdialog;
	}
	
	
	public static void showDialog(Context context, String title, String msg,
			String leftbtn, String rightbtn,
			OnClickListener LeftOnClickListener,
			OnClickListener RightOnClickListener, boolean cancelable) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setCancelable(cancelable);
		builder.setTitle(title).setMessage(msg)
				.setNegativeButton(leftbtn, LeftOnClickListener)
				.setPositiveButton(rightbtn, RightOnClickListener).create()
				.show();
	}
    
    /**
     * �ı����ݵ���ʾ
     * @param ctx
     * @param yesListener
     */
    public static void showChangePrompt(Context ctx,String message,OnClickListener yesListener,OnClickListener noListener){
    	AlertDialog.Builder builder = new Builder(ctx);
    	builder.setIcon(android.R.drawable.ic_dialog_info);
    	builder.setTitle(
				"��ʾ");
		builder.setMessage(message);
		builder.setPositiveButton("��", yesListener);
		builder.setNegativeButton("��", noListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
    }
    
    /**
	 * �������Ի���
	 * 
	 * @param title
	 * @param msg
	 */
	public static ProgressDialog progressDialog(Context context,final int maxCount,String title, String msg) {
		final ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setCancelable(false);// ���õ���հ״�Ҳ���ܹرոöԻ���
		
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMax(maxCount);
		dialog.setIndeterminate(false);// ������ʾ��ȷ�Ľ���
		dialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "ȡ��",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// ������ӵ������߼�
					}
				});
		dialog.show();
	  return dialog;
	}

    /**
     * �����������ʾ
     * @param ctx
     * @param message
     */
    public static void showSettingsNetwork(Context ctx,String message){
    	final Context context = ctx;
    	AlertDialog.Builder builder = new Builder(ctx);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("��ʾ");
		builder.setMessage(message);
		builder.setPositiveButton("����", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			}
		});
		
		builder.setNegativeButton("ȡ��", null);
		AlertDialog dialog = builder.create();
		dialog.show();
    }
    
    /**
     * ȷ�϶Ի���
     * @param ctx
     * @param message
     * @param yesListener
     */
    public static AlertDialog getConfirmDialog(Context ctx,String message,OnClickListener yesListener){
		return getConfirmDialog(ctx, null, message, yesListener);
    }
    
    /**
     * ȷ�϶Ի���
     * @param ctx
     * @param message
     * @param yesListener
     */
    public static AlertDialog getConfirmDialog(Context ctx, String title, String message, OnClickListener yesListener){
    	AlertDialog.Builder builder = new Builder(ctx);
    	builder.setIcon(android.R.drawable.ic_dialog_info);
    	if (TextUtils.isEmpty(title)) {
    		builder.setTitle("��ʾ");
    	} else {
    		builder.setTitle(title);
    	}   	
		builder.setMessage(message);
		builder.setPositiveButton("ȷ��", yesListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);

		return dialog;
    }
    
    /**
     * �Զ�����ʾ���ݵ���ʾ��
     * @param ctx
     * @param yesListener
     */
    public static void showCustomPrompt(Context ctx,String message,String yesMsg,String noMsg,OnClickListener yesListener,OnClickListener noListener){
    	AlertDialog.Builder builder = new Builder(ctx);
    	builder.setIcon(android.R.drawable.ic_delete);
    	builder.setTitle(
				"ɾ��");
		builder.setMessage(message);
		builder.setPositiveButton(yesMsg, yesListener);
		builder.setNegativeButton(noMsg, noListener);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
    }
    
    /**
     * ��д���õ��Զ���Ի���
     * @param ctx
     * @param title ����
     * @param et edittext�ؼ�
     * @param edtcontent ��д����
     * @param yesListener
     */
    public static void showFilloutDialog(Context ctx,String title,EditText et,String edtcontent,OnClickListener yesListener){
		et.setText(edtcontent);
    	AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(et)
				.setNegativeButton("ȡ��", null);
		builder.setPositiveButton("ȷ��",yesListener).create().show();
    }

}
