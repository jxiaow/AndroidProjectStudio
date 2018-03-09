package cn.xwj.http;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Author: xw
 * Date: 2018-03-09 14:03:31
 * Description: HttpUtils <this is description>.
 */

public class HttpUtils {
    private static final int GET = 0x0001;
    private static final int POST = 0x0002;

    private static IHttpEngine sHttpEngine = new OkHttpEngine();
    private String url;
    private Context mContext;
    private ArrayMap<String, Object> mParams;
    private int type = GET;

    private HttpUtils(Context context) {
        this.mContext = context;
        this.mParams = new ArrayMap<>();
    }

    public static void init(IHttpEngine engine) {
        sHttpEngine = engine;
    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils exchangeEngine(IHttpEngine engine) {
        sHttpEngine = engine;
        return this;
    }

    public HttpUtils url(String url) {
        this.url = url;
        return this;
    }

    public HttpUtils addParam(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public HttpUtils addParams(Map<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public HttpUtils get() {
        this.type = GET;
        return this;
    }

    public HttpUtils post() {
        this.type = POST;
        return this;
    }

    public void execute(HttpCallBack callBack) {
        if (callBack == null) {
            callBack = HttpCallBack.DEFAULT_CALLBACK;
        }
        if (this.type == GET) {
            get(url, mParams, callBack);
        }

        if (this.type == POST) {
            post(url, mParams, callBack);
        }
    }

    public void execute() {
        execute(null);
    }


    private void get(String url, ArrayMap<String, Object> params, HttpCallBack callBack) {
        sHttpEngine.get(mContext, url, params, callBack);
    }

    private void post(String url, ArrayMap<String, Object> params, HttpCallBack callBack) {
        sHttpEngine.get(mContext, url, params, callBack);
    }

    public static String joinParams(String url, ArrayMap<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuffer stringBuffer = new StringBuffer(url);
        if (!url.contains("?")) {
            stringBuffer.append("?");
        } else {
            if (!url.endsWith("?")) {
                stringBuffer.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    public static Class<?> analysisClassInfo(Object object) {
        Type type = object.getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) types[0];
    }
}
