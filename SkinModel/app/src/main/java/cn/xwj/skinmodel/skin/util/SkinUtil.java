package cn.xwj.skinmodel.skin.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinUtil
 */
public class SkinUtil {

    /**
     * 根据文件路径获取packageName
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return pageName
     */
    @Nullable
    public static String getPackageNameByPath(@NonNull Context context, @NonNull String filePath) {

        PackageInfo packageInfo = context.getPackageManager()
                .getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);

        if (packageInfo == null) {
            return null;
        }

        String packageName = packageInfo.packageName;
        return TextUtils.isEmpty(packageName)?null:packageName;
    }
}
