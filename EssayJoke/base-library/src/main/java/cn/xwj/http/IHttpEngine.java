package cn.xwj.http;

import android.content.Context;
import android.support.v4.util.ArrayMap;

/**
 * Created by xw on 2018/3/10.
 */

public interface IHttpEngine {
    void get(Context context, String url, ArrayMap<String, Object> params, IEngineCallBack callBack);
    void post(Context context, String url, ArrayMap<String, Object> params, IEngineCallBack callBack);
}
