/**
 * Copyright 2014 Zhenguo Jin (jinzhenguo1990@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.util.system;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

/**
 * �ֻ�״̬������ ��Ҫ�������硢��������Ļ���ȡ�����ģʽ��������
 * 
 * @author zhenguo
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class DeviceStatusUtils {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private DeviceStatusUtils() {
		throw new Error("Do not need instantiate!");
	}

	/**
	 * ��ȡϵͳ��Ļ����ģʽ��״̬����ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC���Զ���System.
	 *         SCREEN_BRIGHTNESS_MODE_AUTOMATIC
	 *         ���ֶ���Ĭ�ϣ�System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
	 */
	public static int getScreenBrightnessModeState(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS_MODE,
				Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	}

	/**
	 * �ж�ϵͳ��Ļ����ģʽ�Ƿ����Զ�����ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return true���Զ���false���ֶ���Ĭ�ϣ�true
	 */
	public static boolean isScreenBrightnessModeAuto(Context context) {
		return getScreenBrightnessModeState(context) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC ? true
				: false;
	}

	/**
	 * ����ϵͳ��Ļ����ģʽ����ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @param auto
	 *            �Զ�
	 * @return �Ƿ����óɹ�
	 */
	public static boolean setScreenBrightnessMode(Context context, boolean auto) {
		boolean result = true;
		if (isScreenBrightnessModeAuto(context) != auto) {
			result = Settings.System.putInt(context.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE,
					auto ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
							: Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
		}
		return result;
	}

	/**
	 * ��ȡϵͳ���ȣ���ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return ���ȣ���Χ��0-255��Ĭ��255
	 */
	public static int getScreenBrightness(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, 255);
	}

	/**
	 * ����ϵͳ���ȣ��˷���ֻ�Ǹ�����ϵͳ���������ԣ������ܿ���Ч����Ҫ�뿴��Ч������ʹ��setWindowBrightness()�������ô��ڵ����ȣ���
	 * ��ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @param screenBrightness
	 *            ���ȣ���Χ��0-255
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean setScreenBrightness(Context context,
			int screenBrightness) {
		int brightness = screenBrightness;
		if (screenBrightness < 1) {
			brightness = 1;
		} else if (screenBrightness > 255) {
			brightness = screenBrightness % 255;
			if (brightness == 0) {
				brightness = 255;
			}
		}
		boolean result = Settings.System.putInt(context.getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, brightness);
		return result;
	}

	/**
	 * ���ø���Activity�Ĵ��ڵ����ȣ����Կ���Ч������ϵͳ���������Բ���ı䣩
	 * 
	 * @param activity
	 *            Ҫͨ����Activity�����ô��ڵ�����
	 * @param screenBrightness
	 *            ���ȣ���Χ��0-255
	 */
	public static void setWindowBrightness(Activity activity,
			float screenBrightness) {
		float brightness = screenBrightness;
		if (screenBrightness < 1) {
			brightness = 1;
		} else if (screenBrightness > 255) {
			brightness = screenBrightness % 255;
			if (brightness == 0) {
				brightness = 255;
			}
		}
		Window window = activity.getWindow();
		WindowManager.LayoutParams localLayoutParams = window.getAttributes();
		localLayoutParams.screenBrightness = (float) brightness / 255;
		window.setAttributes(localLayoutParams);
	}

	/**
	 * ����ϵͳ���Ȳ�ʵʱ���Կ���Ч������ҪWRITE_SETTINGSȨ��
	 * 
	 * @param activity
	 *            Ҫͨ����Activity�����ô��ڵ�����
	 * @param screenBrightness
	 *            ���ȣ���Χ��0-255
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean setScreenBrightnessAndApply(Activity activity,
			int screenBrightness) {
		boolean result = true;
		result = setScreenBrightness(activity, screenBrightness);
		if (result) {
			setWindowBrightness(activity, screenBrightness);
		}
		return result;
	}

	/**
	 * ��ȡ��Ļ����ʱ�䣬��ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return ��Ļ����ʱ�䣬��λ���룬Ĭ��30000
	 */
	public static int getScreenDormantTime(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.SCREEN_OFF_TIMEOUT, 30000);
	}

	/**
	 * ������Ļ����ʱ�䣬��ҪWRITE_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean setScreenDormantTime(Context context, int millis) {
		return Settings.System.putInt(context.getContentResolver(),
				Settings.System.SCREEN_OFF_TIMEOUT, millis);
	}

	/**
	 * ��ȡ����ģʽ��״̬����ҪWRITE_APN_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return 1���򿪣�0���رգ�Ĭ�ϣ��ر�
	 */
	@SuppressWarnings("deprecation")
	public static int getAirplaneModeState(Context context) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
			return Settings.System.getInt(context.getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 0);
		} else {
			return Settings.Global.getInt(context.getContentResolver(),
					Settings.Global.AIRPLANE_MODE_ON, 0);
		}
	}

	/**
	 * �жϷ���ģʽ�Ƿ�򿪣���ҪWRITE_APN_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return true���򿪣�false���رգ�Ĭ�Ϲر�
	 */
	public static boolean isAirplaneModeOpen(Context context) {
		return getAirplaneModeState(context) == 1 ? true : false;
	}

	/**
	 * ���÷���ģʽ��״̬����ҪWRITE_APN_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @param enable
	 *            ����ģʽ��״̬
	 * @return �����Ƿ�ɹ�
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) @SuppressWarnings("deprecation")
	public static boolean setAirplaneMode(Context context, boolean enable) {
		boolean result = true;
		// �������ģʽ��ǰ��״̬��Ҫ���õ�״̬��һ��
		if (isAirplaneModeOpen(context) != enable) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
				result = Settings.System.putInt(context.getContentResolver(),
						Settings.System.AIRPLANE_MODE_ON, enable ? 1 : 0);
			} else {
				result = Settings.Global.putInt(context.getContentResolver(),
						Settings.Global.AIRPLANE_MODE_ON, enable ? 1 : 0);
			}
			// ���ͷ���ģʽ�Ѿ��ı�㲥
			context.sendBroadcast(new Intent(
					Intent.ACTION_AIRPLANE_MODE_CHANGED));
		}
		return result;
	}

	/**
	 * ��ȡ������״̬
	 * 
	 * @return ȡֵΪBluetoothAdapter���ĸ���̬�ֶΣ�STATE_OFF, STATE_TURNING_OFF,
	 *         STATE_ON, STATE_TURNING_ON
	 * @throws Exception
	 *             û���ҵ������豸
	 */
	public static int getBluetoothState() throws Exception {
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (bluetoothAdapter == null) {
			throw new Exception("bluetooth device not found!");
		} else {
			return bluetoothAdapter.getState();
		}
	}

	/**
	 * �ж������Ƿ��
	 * 
	 * @return true���Ѿ��򿪻������ڴ򿪣�false���Ѿ��رջ������ڹر�
	 *             û���ҵ������豸
	 */
	public static boolean isBluetoothOpen() throws Exception {
		int bluetoothStateCode = getBluetoothState();
		return bluetoothStateCode == BluetoothAdapter.STATE_ON
				|| bluetoothStateCode == BluetoothAdapter.STATE_TURNING_ON ? true
				: false;
	}

	/**
	 * ��������״̬
	 * 
	 * @param enable
	 *            ��
	 *             û���ҵ������豸
	 */
	public static void setBluetooth(boolean enable) throws Exception {
		// �����ǰ������״̬��Ҫ���õ�״̬��һ��
		if (isBluetoothOpen() != enable) {
			// �����Ҫ�򿪾ʹ򿪣�����ر�
			if (enable) {
				BluetoothAdapter.getDefaultAdapter().enable();
			} else {
				BluetoothAdapter.getDefaultAdapter().disable();
			}
		}
	}

	/**
	 * ��ȡý����������ҪWRITE_APN_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return ý��������ȡֵ��ΧΪ0-15��Ĭ��0
	 */
	public static int getMediaVolume(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.VOLUME_MUSIC, 0);
	}

	/**
	 * ��ȡý����������ҪWRITE_APN_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return ý��������ȡֵ��ΧΪ0-15
	 */
	public static boolean setMediaVolume(Context context, int mediaVloume) {
		if (mediaVloume < 0) {
			mediaVloume = 0;
		} else if (mediaVloume > 15) {
			mediaVloume = mediaVloume % 15;
			if (mediaVloume == 0) {
				mediaVloume = 15;
			}
		}
		return Settings.System.putInt(context.getContentResolver(),
				Settings.System.VOLUME_MUSIC, mediaVloume);
	}

	/**
	 * ��ȡ������������ҪWRITE_APN_SETTINGSȨ��
	 * 
	 * @param context
	 *            ������
	 * @return ����������ȡֵ��ΧΪ0-7��Ĭ��Ϊ0
	 */
	public static int getRingVolume(Context context) {
		return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getStreamVolume(AudioManager.STREAM_RING);
	}

	/**
	 * ��ȡý������
	 * 
	 * @param context
	 *            ������
	 * @return ý��������ȡֵ��ΧΪ0-7
	 */
	public static void setRingVolume(Context context, int ringVloume) {
		if (ringVloume < 0) {
			ringVloume = 0;
		} else if (ringVloume > 7) {
			ringVloume = ringVloume % 7;
			if (ringVloume == 0) {
				ringVloume = 7;
			}
		}

		((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setStreamVolume(AudioManager.STREAM_RING,
				ringVloume, AudioManager.FLAG_PLAY_SOUND);
	}

	// /**
	// * �Ҳ����豸�쳣
	// */
	// public static class DeviceNotFoundException extends Exception{
	// private static final long serialVersionUID = 1L;
	//
	// public DeviceNotFoundException(){}
	//
	// public DeviceNotFoundException(String message){
	// super(message);
	// }
	// }
	
	private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
	
	/**
     * ����״̬���߶ȸ߶�
     * getStatusBarHeight
     * @return
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
