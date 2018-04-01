package cn.xwj.androidfix;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

import cn.xwj.androidfix.util.AppUtils;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/1
 * Description: BaseApplication.
 */

public class BaseApplication extends Application {
    public static PatchManager sPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sPatchManager = new PatchManager(this);
        sPatchManager.init(AppUtils.getVersion(this));
        sPatchManager.loadPatch();
    }
}
