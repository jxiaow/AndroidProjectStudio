package cn.xwj.skinmodel;

import android.app.Application;

import cn.xwj.skinmodel.skin.SkinManager;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: App
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
