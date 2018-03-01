package cn.xwj.ioc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Author: xw
 * Date: 2018-03-01 12:43:35
 * Description: InjectUtils <this is description>.
 */

public class InjectUtils {
    public static boolean isAvailableNetWork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) return false;

        NetworkInfo network = cm.getActiveNetworkInfo();
        return network != null && network.isAvailable() && network.isConnected();
    }
}
