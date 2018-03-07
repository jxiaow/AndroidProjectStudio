package cn.xwj.essayjoke;

import android.app.Application;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import cn.xwj.crash.CrashExceptionHandler;
import cn.xwj.fixbug.FixDexManager;
import cn.xwj.util.Utils;
import cn.xwj.easy.E;

/**
 * Author: xw
 * Date: 2018-03-05 14:46:08
 * Description: BaseApplication <this is description>.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    public static FixDexManager sFixDexManager;

    @Override
    public void onCreate() {
        super.onCreate();
        E.context(this);
        sFixDexManager = new FixDexManager(this);
        try {
            sFixDexManager.loadFixDex();
            Log.d(TAG, "sFixDexManager 加载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
