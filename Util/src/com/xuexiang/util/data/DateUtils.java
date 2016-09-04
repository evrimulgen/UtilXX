/**
 * Copyright 2014 Zhenguo Jin
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
package com.xuexiang.util.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

/**
 * ���ڹ�����
 *
 * @author jingle1267@163.com
 */
public final class DateUtils {

    /**
     * �������� *
     */
    public static final String yyyyMMDD = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String HHmmss = "HH:mm:ss";
    public static final String hhmmss = "HH:mm:ss";
    public static final String LOCALE_DATE_FORMAT = "yyyy��M��d�� HH:mm:ss";
    public static final String DB_DATA_FORMAT = "yyyy-MM-DD HH:mm:ss";
    public static final String NEWS_ITEM_DATE_FORMAT = "hh:mm M��d�� yyyy";

    /**
     * ��Date����ת��Ϊ�����ַ���
     *
     * @param date Date����
     * @param type ��Ҫ�����ڸ�ʽ
     * @return ���������ʽ�������ַ���
     */
    public static String formatDate(Date date, String type) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(type);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �������ַ���ת��ΪDate����
     *
     * @param dateStr �����ַ���
     * @param type    �����ַ�����ʽ
     * @return Date����
     */
    public static Date parseDate(String dateStr, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * �õ���
     *
     * @param date Date����
     * @return ��
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * �õ���
     *
     * @param date Date����
     * @return ��
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;

    }

    /**
     * �õ���
     *
     * @param date Date����
     * @return ��
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * ת������ ������תΪ����, ����, ǰ��, XXXX-XX-XX, ...
     *
     * @param time ʱ��
     * @return ��ǰ����ת��Ϊ���������ķ�ʽ
     */
    public static String translateDate(Long time) {
        long oneDay = 24 * 60 * 60 * 1000;
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //����

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR����12Сʱ�Ƶ�Сʱ�� Calendar.HOUR_OF_DAY����24Сʱ�Ƶ�Сʱ��
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long todayStartTime = today.getTimeInMillis();

        if (time >= todayStartTime && time < todayStartTime + oneDay) { // today
            return "����";
        } else if (time >= todayStartTime - oneDay && time < todayStartTime) { // yesterday
            return "����";
        } else if (time >= todayStartTime - oneDay * 2 && time < todayStartTime - oneDay) { // the day before yesterday
            return "ǰ��";
        } else if (time > todayStartTime + oneDay) { // future
            return "����ĳһ��";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            return dateFormat.format(date);
        }
    }

    /**
     * ת������ ת��Ϊ��Ϊ���Ի���ʱ��
     *
     * @param time ʱ��
     * @return
     */
    public static String translateDate(long time, long curTime) {
        long oneDay = 24 * 60 * 60;
        Calendar today = Calendar.getInstance();    //����
        today.setTimeInMillis(curTime * 1000);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        long todayStartTime = today.getTimeInMillis() / 1000;
        if (time >= todayStartTime) {
            long d = curTime - time;
            if (d <= 60) {
                return "1����ǰ";
            } else if (d <= 60 * 60) {
                long m = d / 60;
                if (m <= 0) {
                    m = 1;
                }
                return m + "����ǰ";
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("���� HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        } else {
            if (time < todayStartTime && time > todayStartTime - oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("���� HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {

                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else  if (time < todayStartTime - oneDay && time > todayStartTime - 2 * oneDay){
                SimpleDateFormat dateFormat = new SimpleDateFormat("ǰ�� HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        }
    }
    
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static final int YEAR = 365 * 24 * 60 * 60;// ��
	private static final int MONTH = 30 * 24 * 60 * 60;// ��
	private static final int DAY = 24 * 60 * 60;// ��
	private static final int HOUR = 60 * 60;// Сʱ
	private static final int MINUTE = 60;// ����
    
    /**
	 * ����ʱ�����ȡ������ʱ�䣬��3����ǰ��1��ǰ
	 * 
	 * @param timestamp
	 *            ʱ��� ��λΪ����
	 * @return ʱ���ַ���
	 */
	public static String getDescriptionTimeFromTimestamp(long timestamp) {
		long currentTime = System.currentTimeMillis();
		long timeGap = (currentTime - timestamp) / 1000;// ������ʱ���������
		String timeStr = null;
		if (timeGap > YEAR) {
			timeStr = timeGap / YEAR + "��ǰ";
		} else if (timeGap > MONTH) {
			timeStr = timeGap / MONTH + "����ǰ";
		} else if (timeGap > DAY) {// 1������
			timeStr = timeGap / DAY + "��ǰ";
		} else if (timeGap > HOUR) {// 1Сʱ-24Сʱ
			timeStr = timeGap / HOUR + "Сʱǰ";
		} else if (timeGap > MINUTE) {// 1����-59����
			timeStr = timeGap / MINUTE + "����ǰ";
		} else {// 1����-59����
			timeStr = "�ո�";
		}
		return timeStr;
	}
	
	/**
	 * ����ʱ�����ȡ������ʱ�䣬��3����ǰ��1��ǰ
	 * @param strTime Ҫת����String���͵�ʱ��
	 * @param formatType ʱ���ʽ
	 * @return
	 */
	public static String getDescriptionTimeFromTimestamp(String strTime, String formatType) {
		return getDescriptionTimeFromTimestamp(stringToLong(strTime, formatType));
	}
	
	/**
	 * ��ȡ��ǰ���ڵ�ָ����ʽ���ַ���
	 * 
	 * @param format
	 *            ָ��������ʱ���ʽ����Ϊnull��""��ʹ��ָ���ĸ�ʽ"yyyy-MM-dd HH:MM"
	 * @return
	 */
	public static String getCurrentTime(String format) {
		if (format == null || format.trim().equals("")) {
			sdf.applyPattern(FORMAT_DATE_TIME);
		} else {
			sdf.applyPattern(format);
		}
		return sdf.format(new Date());
	}
	
 	/**
 	 * date����ת��ΪString����
 	 * @param data Date���͵�ʱ��
 	 * @param formatType ��ʽΪyyyy-MM-dd HH:mm:ss//yyyy��MM��dd�� HHʱmm��ss��
 	 * @return
 	 */
 	public static String dateToString(Date data, String formatType) {
 		return new SimpleDateFormat(formatType).format(data);
 	}
 	
 	/**
 	 * string����ת��Ϊdate����
 	 * @param strTime Ҫת����string���͵�ʱ�䣬formatTypeҪת���ĸ�ʽyyyy-MM-dd HH:mm:ss//yyyy��MM��dd��
 	 * @param formatType ʱ���ʽ����Ҫ��formatType��ʱ���ʽ��ͬ
 	 * @return
 	 */
 	public static Date stringToDate(String strTime, String formatType){
 		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
 		Date date = null;
 		try {
			date = formatter.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		return date;
 	}
 	
 	/**
 	 * string����ת��Ϊlong����
 	 * @param strTime Ҫת����String���͵�ʱ�� ,strTime��ʱ���ʽ��formatType��ʱ���ʽ������ͬ
 	 * @param formatType ʱ���ʽ
 	 * @return
 	 */
 	public static long stringToLong(String strTime, String formatType){
 		Date date = stringToDate(strTime, formatType); // String����ת��date����
 		if (date == null) {
 			return 0;
 		} else {
 			long currentTime = dateToLong(date); // date����ת��long����
 			return currentTime;
 		}
 	}
 	
 	/**
 	 * date����ת��Ϊlong����
 	 * @param date Ҫת����date���͵�ʱ��
 	 * @return
 	 */
 	public static long dateToLong(Date date) {
 		return date.getTime();
 	}
 	
 	/**
	 * ����ˢ��ʱ��
	 * @param created
	 * @return
	 */
	public static String getUploadtime(long created) {
		StringBuffer when = new StringBuffer();
		int difference_seconds;
		int difference_minutes;
		int difference_hours;
		int difference_days;
		int difference_months;
		long curTime = System.currentTimeMillis();
		difference_months = (int) (((curTime / 2592000) % 12) - ((created / 2592000) % 12));
		if (difference_months > 0) {
			when.append(difference_months + "��");
		}

		difference_days = (int) (((curTime / 86400) % 30) - ((created / 86400) % 30));
		if (difference_days > 0) {
			when.append(difference_days + "��");
		}

		difference_hours = (int) (((curTime / 3600) % 24) - ((created / 3600) % 24));
		if (difference_hours > 0) {
			when.append(difference_hours + "Сʱ");
		}

		difference_minutes = (int) (((curTime / 60) % 60) - ((created / 60) % 60));
		if (difference_minutes > 0) {
			when.append(difference_minutes + "����");
		}

		difference_seconds = (int) ((curTime % 60) - (created % 60));
		if (difference_seconds > 0) {
			when.append(difference_seconds + "��");
		}

		return when.append("ǰ").toString();
	}


}
