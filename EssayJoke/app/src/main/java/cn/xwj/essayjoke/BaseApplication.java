package cn.xwj.essayjoke;

import android.app.Application;

import cn.xwj.base.crash.CrashExceptionHandler;

/**
 * Author: xw
 * Date: 2018-03-05 14:46:08
 * Description: BaseApplication <this is description>.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashExceptionHandler.getInstance().init(this);
    }
}
