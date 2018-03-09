package cn.xwj.http;


import android.content.Context;
import android.support.v4.util.ArrayMap;

import java.util.Map;

/**
 * Author: xw
 * Date: 2018-03-09 14:03:49
 * Description: IHttpEngine <this is description>.
 */

public interface IHttpEngine {

    void get(Context context, String url, ArrayMap<String, Object> params, HttpCallBack callBack);

    void post(Context context, String url, ArrayMap<String, Object> params, HttpCallBack callBack);
}
