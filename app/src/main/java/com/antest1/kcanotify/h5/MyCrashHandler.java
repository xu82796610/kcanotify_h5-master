package com.antest1.kcanotify.h5;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.antest1.kcanotify.h5.util.HttpUtils;

import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局捕获导常，保存到本地错误日志。日志
 * 路径位于sdcard/错误日志Log/myErrorLog下。
 */
public class MyCrashHandler implements UncaughtExceptionHandler {


    private static MyCrashHandler instance;

    private String logPath;

    public static MyCrashHandler getInstance() {
        if (instance == null) {
            instance = new MyCrashHandler();
        }
        return instance;
    }

    public void init(Context ctx) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 核心方法，当程序crash 会回调此方法， Throwable中存放这错误日志
     */
    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            logPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + File.separator
                    + File.separator
                    + "错误日志Log";

            File file = new File(logPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            try {
                FileWriter fw = new FileWriter(logPath + File.separator
                        + "myErrorlog.log", true);
                fw.write(new Date() + "错误原因：\n");
                // 错误信息
                // 这里还可以加上当前的系统版本，机型型号 等等信息
                StackTraceElement[] stackTrace = arg1.getStackTrace();
                fw.write("手机厂商：" + getDeviceManufacturer() + "\n");
                fw.write("手机类型：" + getDeviceProduct() + "\n");
                fw.write("手机品牌：" + getDeviceBrand() + "\n");
                fw.write("手机型号：" + getDeviceModel() + "\n");
                fw.write("手机设备：" + getDeviceDevice() + "\n");
                fw.write("系统版本：" + getDeviceSDK() + "\n");
                fw.write("系统安卓：" + getDeviceAndroidVersion() + "\n");
                fw.write(arg1.getMessage() + "\n");
                for (int i = 0; i < stackTrace.length; i++) {
                    fw.write("file:" + stackTrace[i].getFileName() + " class:"
                            + stackTrace[i].getClassName() + " method:"
                            + stackTrace[i].getMethodName() + " line:"
                            + stackTrace[i].getLineNumber() + "\n");
                }
                fw.write("\n");
                fw.close();
                // 上传错误信息到服务器
                HttpUtils.uploadToServer();
            } catch (IOException e) {
                Log.e("crash handler", "load file failed...", e.getCause());
            }
        }
        arg1.printStackTrace();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取厂商名
     * **/
    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     * **/
    public static String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 设备名
     * **/
    public static String getDeviceDevice() {
        return android.os.Build.DEVICE;
    }

    /**
     * 获取手机Android 系统SDK
     *
     * @return
     */
    public static int getDeviceSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本
     *
     * @return
     */
    public static String getDeviceAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
}