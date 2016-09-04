package com.xuexiang.util.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ChannelUtil {

    private static final String CHANNEL_KEY = "channel_key";
    private static final String CHANNEL_VERSION_KEY = "channel_version_key";
    private static String mChannel;

    /**
     * �����г���  �����ȡʧ�ܷ���""
     *
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        return getChannel(context, "Channel_Default");
    }

    /**
     * �����г���  �����ȡʧ�ܷ���defaultChannel
     *
     * @param context
     * @param defaultChannel
     * @return
     */
    public static String getChannel(Context context, String defaultChannel) {
        //�ڴ��л�ȡ
        if (!TextUtils.isEmpty(mChannel)) {
            return mChannel;
        }
        //sp�л�ȡ
        mChannel = getChannelBySharedPreferences(context);
        if (!TextUtils.isEmpty(mChannel)) {
            return mChannel;
        }
        //��apk�л�ȡ
        mChannel = getChannelFromApk(context, CHANNEL_KEY);
        if (!TextUtils.isEmpty(mChannel)) {
            //����sp�б���
            saveChannelBySharedPreferences(context, mChannel);
            return mChannel;
        }
        //ȫ����ȡʧ��
        return defaultChannel;
    }

    /**
     * ��apk�л�ȡ�汾��Ϣ
     *
     * @param context
     * @param channelKey
     * @return
     */
    private static String getChannelFromApk(Context context, String channelKey) {
        //��apk���л�ȡ
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        //Ĭ�Ϸ���meta-inf/� ������Ҫ��ƴ��һ��
        String key = "META-INF/" + channelKey;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith(key)) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = ret.split("\\*");
        String channel = "";
        if (split != null && split.length >= 2) {
            channel = ret.substring(split[0].length() + 1);
        }
        return channel;
    }

    /**
     * ���ر���channel & ��Ӧ�汾��
     *
     * @param context
     * @param channel
     */
    private static void saveChannelBySharedPreferences(Context context, String channel) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sp.edit();
        editor.putString(CHANNEL_KEY, channel);
        editor.putInt(CHANNEL_VERSION_KEY, getVersionCode(context));
        editor.commit();
    }

    /**
     * ��sp�л�ȡchannel
     *
     * @param context
     * @return Ϊ�ձ�ʾ��ȡ�쳣��sp�е�ֵ�Ѿ�ʧЧ��sp��û�д�ֵ
     */
    private static String getChannelBySharedPreferences(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        int currentVersionCode = getVersionCode(context);
        if (currentVersionCode == -1) {
            //��ȡ����
            return "";
        }
        int versionCodeSaved = sp.getInt(CHANNEL_VERSION_KEY, -1);
        if (versionCodeSaved == -1) {
            //����û�д洢��channel��Ӧ�İ汾��
            //��һ��ʹ��  ���� ԭ�ȴ洢�汾���쳣
            return "";
        }
        if (currentVersionCode != versionCodeSaved) {
            return "";
        }
        return sp.getString(CHANNEL_KEY, "");
    }

    /**
     * �Ӱ���Ϣ�л�ȡ�汾��
     *
     * @param context
     * @return
     */
    private static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
