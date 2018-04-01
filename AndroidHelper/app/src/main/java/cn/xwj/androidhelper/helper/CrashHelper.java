package cn.xwj.androidhelper.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/1
 * Description: CrashHelper 程序奔溃日志收集器.
 */

public class CrashHelper implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHelper";

    private static CrashHelper sInstance; //单例
    private Context mContext; //上下文
    private Thread.UncaughtExceptionHandler mExceptionHandler; //错误信息处理器
    private Map<String, String> mMessageInfo; //一些信息收集集合
    //生成奔溃日志的根目录
    private String root = Environment.getExternalStorageDirectory()
            + File.separator + "crash";

    private static final String CRASH_FILE_PATH = "crash_file_path";

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    private CrashHelper() {
    }

    /**
     * 初始化一些信息
     */
    public CrashHelper init(Context context) {
        mContext = context.getApplicationContext();
        mMessageInfo = new HashMap<>();
        mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        return this;
    }

    /**
     * 设置生成文件的路径
     */
    public CrashHelper setDir(@NonNull String dir) {
        this.root = dir;
        return this;
    }

    /**
     * 获取单例实例
     */
    public static CrashHelper getInstance() {
        if (sInstance == null) {
            synchronized (CrashHelper.class) {
                if (sInstance == null) {
                    sInstance = new CrashHelper();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //处理错误信息
        handException(e);
        if (mExceptionHandler != null) {
            mExceptionHandler.uncaughtException(t, e);
        } else {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(mContext, "程序出现错误即将退出",
                            Toast.LENGTH_LONG).show();
                    Looper.loop();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    android.os.Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }.start();
        }
    }

    /**
     * 自定义处理错误信息
     */
    private void handException(Throwable e) {
        if (e == null) {
            return;
        }

        //获取软件版本信息
        collectVersion();
        //收集Android系统信息
        collectDeviceInfo();
        //解析错误信息和合并收集的信息
        String result = collectInfo(e);
        //将所有信息写入文件
        String filePath = writeToFile(result);
        //保存文件路径
        savePath(filePath);
    }

    /**
     * 保存文件路径
     */
    private void savePath(String filePath) {
        SharedPreferences preferences = mContext.getSharedPreferences(CRASH_FILE_PATH, Context.MODE_PRIVATE);
        preferences.edit().putString(CRASH_FILE_PATH, filePath).apply();
    }

    /**
     * 获取文件路径
     */
    @Nullable
    public String getCrashFilePath() {
        SharedPreferences preferences = mContext.getSharedPreferences(CRASH_FILE_PATH, Context.MODE_PRIVATE);
        return preferences.getString(CRASH_FILE_PATH, null);
    }

    private String writeToFile(String result) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.e(TAG, "外部存储不可用");
            return null;
        }
        try {
            File dir = new File(root);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = "crash-" + mDateFormat.format(System.currentTimeMillis()) + ".log";
            String filePath = dir.getAbsolutePath() + File.separator + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(result.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析错误信息，并组合信息
     */
    private String collectInfo(Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : mMessageInfo.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("\r\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        stringBuilder.append(stringWriter.toString());
        return stringBuilder.toString();
    }

    private void collectDeviceInfo() {
        Field[] fields = Build.class.getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return;
        }

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                mMessageInfo.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 收集版本信息
     */
    private void collectVersion() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            mMessageInfo.put("versionCode", String.valueOf(versionCode));
            mMessageInfo.put("versionName", versionName);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
