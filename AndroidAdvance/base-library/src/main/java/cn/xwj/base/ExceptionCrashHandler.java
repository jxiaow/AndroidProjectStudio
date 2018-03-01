package cn.xwj.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: xw
 * Date: 2018-03-01 14:10:11
 * Description: ExceptionCrashHandler <this is description>.
 */

public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler {

    private ExceptionCrashHandler mCrashHandler;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> mInfos = new HashMap<>();
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private ExceptionCrashHandler() {
    }

    public ExceptionCrashHandler getCrashHandler() {
        if (mCrashHandler == null) {
            synchronized (ExceptionCrashHandler.class) {
                if (mCrashHandler == null) {
                    mCrashHandler = new ExceptionCrashHandler();
                }
            }
        }
        return mCrashHandler;
    }

    public void init(Context context) {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(this);
        Thread.currentThread().setUncaughtExceptionHandler(this);
        this.mContext = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {

        if (mDefaultHandler != null && !handlerException(ex)) {
            mDefaultHandler.uncaughtException(t, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handlerException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉, 程序即将退出", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collectDeviceInfo();
        String fileName = saveCrashInfo2File(ex);
        cacheCrashFile(fileName);
        return true;
    }

    private void cacheCrashFile(String fileName) {
        if (fileName == null) {
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("crash",
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("CRASH_FILE_NAME", fileName).apply();
    }

    public File getCacheCrashFile() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("crash",
                Context.MODE_PRIVATE);
        String fileName = sharedPreferences.getString("CRASH_FILE_NAME", "");
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        return new File(fileName);
    }

    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : mInfos.entrySet()) {
            stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
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
        stringBuffer.append(printWriter.toString());

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //文件名
            String fileName = "crash-" + mFormat.format(new Date()) + ".txt";
            File cacheDir = mContext.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }

            File dir = new File(cacheDir + File.separator + "crash" + File.separator);
            if (dir.exists()) {
                deleteDir(dir);
            }
            cacheDir.mkdirs();

            try {
                FileOutputStream fos = new FileOutputStream(dir + fileName);
                fos.write(stringBuffer.toString().getBytes());
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dir + fileName;
        }
        return null;
    }

    private void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] list = dir.list();
            for (int i = 0; i < list.length; i++) {
                deleteDir(new File(list[i]));
            }
        }
    }

    private void collectDeviceInfo() {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (packageInfo == null) {
                return;
            }
            mInfos.put("versionName", packageInfo.versionName == null ? "null" : packageInfo.versionName);
            mInfos.put("versionCode", String.valueOf(packageInfo.versionCode));
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
                mInfos.put(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
