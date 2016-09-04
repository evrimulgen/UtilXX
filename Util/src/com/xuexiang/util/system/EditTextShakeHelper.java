
package com.xuexiang.util.system;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/**
 * 
 * @ClassName: com.example.sharkdemo.EditTextShakeHelper
 * @Description:�������Ч��������
 * @author zhaokaiqiang
 * @date 2014-11-21 ����9:56:15
 * 
 */
public class EditTextShakeHelper {

	// �𶯶���
	private Animation shakeAnimation;
	// ��ֵ��
	private CycleInterpolator cycleInterpolator;
	// ����
	private Vibrator shakeVibrator;

	public EditTextShakeHelper(Context context) {

		// ��ʼ������
		shakeVibrator = (Vibrator) context
				.getSystemService(Service.VIBRATOR_SERVICE);
		// ��ʼ���𶯶���
		shakeAnimation = new TranslateAnimation(0, 10, 0, 0);
		shakeAnimation.setDuration(300);
		cycleInterpolator = new CycleInterpolator(8);
		shakeAnimation.setInterpolator(cycleInterpolator);

	}

	/**
	 * ��ʼ��
	 * 
	 * @param editTexts
	 */
	public void shake(EditText... editTexts) {
		for (EditText editText : editTexts) {
			editText.startAnimation(shakeAnimation);
		}

		shakeVibrator.vibrate(new long[] { 0, 500 }, -1);

	}

}
