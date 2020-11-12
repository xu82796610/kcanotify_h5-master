package com.antest1.kcanotify.h5;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.antest1.kcanotify.h5.util.HttpUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private static MyCrashHandler instance;

    public static MyCrashHandler getInstance() {
        if (instance == null) {
            instance = new MyCrashHandler();
        }
        return instance;
    }

    public void init(Context ctx) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private String getNowDateTime(String strFormat){
        if(strFormat==""){
            strFormat="yyyy-MM-dd HH:mm:ss";
        }
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat(strFormat);//设置日期格式
        return df.format(now); // new Date()为获取当前系统时间
    }

    /**
     * 核心方法，当程序crash 会回调此方法， Throwable中存放这错误日志
     */
    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {

        String logPath;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String strNowDateTime=getNowDateTime("yyyy-MM-dd|HH:mm:ss");//当前时间
            StackTraceElement[] stackTrace = arg1.getStackTrace();
            // 错误信息
            // 这里还可以加上当前的系统版本，机型型号 等等信息
            String error_message =  "错误原因：\n" + arg1.getMessage() + "\n";
            for (int i = 0; i < stackTrace.length; i++) {
                error_message += "file:" + stackTrace[i].getFileName() + " class:"
                        + stackTrace[i].getClassName() + " method:"
                        + stackTrace[i].getMethodName() + " line:"
                        + stackTrace[i].getLineNumber() + "\n";
            }
            error_message += "\n";
            // 上传错误信息到服务器
            Map<String,String> params = new HashMap<String,String>();
            params.put("time", strNowDateTime);
            params.put("message", error_message);
            String strUrlPath = "http://3.104.109.219/infs3202/kcanotify/store_error";

            String strResult= HttpUtils.submitPostData(strUrlPath,params, "utf-8");
        }
        arg1.printStackTrace();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
