package cn.xwj.essayjoke;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

import cn.xwj.base.crash.CrashExceptionHandler;
import cn.xwj.base.util.Utils;
import cn.xwj.easy.E;

/**
 * Author: xw
 * Date: 2018-03-05 14:46:08
 * Description: BaseApplication <this is description>.
 */

public class BaseApplication extends Application {
    public static PatchManager sPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        E.context(this);
        CrashExceptionHandler.getInstance().init(this);
        sPatchManager = new PatchManager(this);
        String version = Utils.getAppVersion(this);
        if (version != null) {
            sPatchManager.init(version);
            sPatchManager.loadPatch();
        }
    }
}
