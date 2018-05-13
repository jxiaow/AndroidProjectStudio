package cn.xwj.skinmodel.skin.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SPUtils
 */
public class SPUtils {

    private static final String SKIN_NAME = "skin_path";

    public static void saveSkinPath(Context context, String path) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SKIN_NAME,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(SKIN_NAME, path).apply();
    }

    public static String getSkinPath(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SKIN_NAME,
                Context.MODE_PRIVATE);
        return preferences.getString(SKIN_NAME, "");
    }

    public static void clearSkinInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SKIN_NAME,
                Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}
