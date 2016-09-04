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
package com.xuexiang.app;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * �����߳��쳣��������������δ֪�쳣ʱ����ʾ�쳣��Ϣ����һ���Ӻ���������Ӧ��
 * <br>ʹ�ô˹��ܵĵ�һ����Ҫ����AndroidMainfest.xml��ע��me.xiaopan.android.content.StartApplicationBrocastReceiver�㲥��ע�ⲻҪ�κε�filter��
 * <br>�ڶ������������Application��onCreate()�����м���new RebootThreadExceptionHandler(getBaseContext());����
 *
 * @author zhenguo
 */
public class RebootThreadExceptionHandler implements UncaughtExceptionHandler {

    private Context context;
    private String hintText;

    public RebootThreadExceptionHandler(Context context, String hintText) {
        this.context = context;
        this.hintText = hintText;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public RebootThreadExceptionHandler(Context context) {
        this(context, null);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();// ����쳣��Ϣ������̨

        if (TextUtils.isEmpty(hintText)) {
            /* �������߳���ʾ�����쳣 */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(context, hintText, Toast.LENGTH_SHORT)
                            .show();
                    Looper.loop();
                }
            }).start();

			/* ���̵߳ȴ�1���ӣ�����ʾ��Ϣ��ʾ���� */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		/* ���ö�ʱ������1���Ӻ󷢳���������Ĺ㲥 */
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.SECOND, 1);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                PendingIntent.getBroadcast(context, 0, new Intent(context,
                        StartAppReceiver.class), 0));

        android.os.Process.killProcess(android.os.Process.myPid()); // ��������
    }

}
