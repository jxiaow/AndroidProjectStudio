package cn.xwj.http;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by xw on 2018/3/10.
 */

public class HttpUtils {
    private static final int TYPE_GET = 0x00011;
    private static final int TYPE_POST = 0x00012;

    //默认网络引擎
    private static IHttpEngine sIHttpEngine = new OkHttpEngine();

    private Context mContext;
    private String url;
    private int methodType = TYPE_GET;
    private ArrayMap<String, Object> mParams;


    private HttpUtils(Context context) {
        this.mContext = context;
        this.mParams = new ArrayMap<>();
    }

    public static void init(IHttpEngine engine) {
        sIHttpEngine = engine;
    }


    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils url(String url) {
        this.url = url;
        return this;
    }

    public HttpUtils get() {
        this.methodType = TYPE_GET;
        return this;
    }

    public HttpUtils post() {
        this.methodType = TYPE_POST;
        return this;
    }

    public HttpUtils addParam(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public HttpUtils exchangeEngine(IHttpEngine engine) {
        sIHttpEngine = engine;
        return this;
    }

    public void execute() {
        execute(null);
    }

    public void execute(IEngineCallBack callBack) {
        if (url == null) {
            throw new IllegalArgumentException("url is null");
        }

        if (callBack == null) {
            callBack = IEngineCallBack.DEFAULT_CALL_BACK;
        }

        callBack.preExecute(mContext, mParams);

        if (methodType == TYPE_GET) {
            sIHttpEngine.get(mContext, url, mParams, callBack);
        }

        if (methodType == TYPE_POST) {
            sIHttpEngine.post(mContext, url, mParams, callBack);
        }
    }

    public static String generateUrl(String url, ArrayMap<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuffer stringBuffer = new StringBuffer(url);
        stringBuffer.append(!url.contains("?") ? "?" : !url.endsWith("?") ? "&" : "");

        for (String key : params.keySet()) {
            stringBuffer.append(key).append("=").append(params.get(key)).append("&");
        }

        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    public static <T> Type analysisClassType(T t) {
        Type type = t.getClass().getGenericSuperclass();
        Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
        return typeArguments[0];
    }
}
