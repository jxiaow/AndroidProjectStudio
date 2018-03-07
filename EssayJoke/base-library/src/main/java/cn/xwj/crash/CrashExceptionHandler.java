package cn.xwj.crash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: xw
 * Date: 2018-03-05 14:08:14
 * Description: CrashExceptionHandler <this is description>.
 */

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static CrashExceptionHandler sInstance;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    private Map<String, String> infos = new HashMap<>();
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static final String CRASH_FILE_NAME = "crash_file_name";

    private CrashExceptionHandler() {
    }


    public static CrashExceptionHandler getInstance() {
        if (sInstance == null) {
            synchronized (CrashExceptionHandler.class) {
                if (sInstance == null) {
                    sInstance = new CrashExceptionHandler();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        handleException(e);
        if (mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t, e);
            return;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "程序出现问题，即将退出!", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        android.os.Process.killProcess(Process.myPid());
    }

    private void handleException(Throwable e) {
        if (e == null) {
            return;
        }
        //收集应用信息和设备信息
        collectDeviceInfo();
        //将信息写入文件，并返回文件路径
        String filePath = save2File(e);
        //保存文件路径
        saveFilePath(filePath);
    }

    public String getFilePath() {
        SharedPreferences preferences = mContext.getSharedPreferences(CRASH_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(CRASH_FILE_NAME, null);
    }

    private void saveFilePath(String filePath) {
        SharedPreferences preferences = mContext.getSharedPreferences(CRASH_FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(CRASH_FILE_NAME, filePath).apply();
    }

    private String save2File(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("\r\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        sb.append(stringWriter.toString());

        //写入文件中
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            long timeStamp = System.currentTimeMillis();
            String date = mDateFormat.format(new Date());
            String fileName = "crash-" + date + "_" + timeStamp + ".log";
            String root = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(root + File.separator + mContext.getPackageName() +
                    File.separator + "crash");
            if (!file.exists()) {
                file.mkdirs();
            }

            try {
                FileOutputStream fos = new FileOutputStream(file + File.separator + fileName);
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
                return file + File.separator + fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void collectDeviceInfo() {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                infos.put("versionName", packageInfo.versionName == null ? "" : packageInfo.versionName);
                infos.put("versionCode", String.valueOf(packageInfo.versionCode));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Field[] fields = Build.class.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                infos.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
