package cn.xwj.httptest.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.util.Map;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: HttpUtils
 */
public class HttpUtils {
    //默认引擎
    private static IHttpEngine sHttpEngine = new OkHttpEngine();
    // 引擎参数的配置
    private EngineOption mEngineOption;

    public static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * 初始化引擎
     *
     * @param httpEngine
     */
    public static void init(IHttpEngine httpEngine) {
        sHttpEngine = httpEngine;
    }

    /**
     * HttpUtils 构造方法
     *
     * @param context 上下文
     */
    private HttpUtils(Context context) {
        mEngineOption = new EngineOption(context);
    }

    /**
     * 创建一个HttpUtils 实例
     *
     * @param context 上下文
     * @return 返回HttpUtils 实例
     */
    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    /**
     * 设置访问的url
     *
     * @param url url
     * @return HttpsUtils
     */
    public HttpUtils url(String url) {
        this.mEngineOption.setUrl(url);
        return this;
    }


    /**
     * 设置tag 主要用来取消请求
     *
     * @param object tag
     * @return HttpUtils
     */
    public HttpUtils tag(Object object) {
        mEngineOption.setTag(object);
        return this;
    }

    /**
     * 添加请求参数
     *
     * @param key   请求参数key
     * @param value 请求参数值
     * @return HttpUtils
     */
    public HttpUtils addParam(String key, Object value) {
        this.mEngineOption.getParams().put(key, value);
        return this;
    }

    /**
     * 添加请求参数
     *
     * @param params map参数集合
     * @return HttpUtils
     */
    public HttpUtils addParams(Map<String, Object> params) {
        this.mEngineOption.getParams().putAll(params);
        return this;
    }

    /**
     * 添加请求header
     *
     * @param headers map 头部集合
     * @return HttpUtils
     */
    public HttpUtils addHeaders(Map<String, String> headers) {
        this.mEngineOption.getHeaders().putAll(headers);
        return this;
    }

    /**
     * 添加请求header
     *
     * @param key   key
     * @param value value
     * @return HttpUtils
     */
    public HttpUtils addHeader(String key, String value) {
        this.mEngineOption.getHeaders().put(key, value);
        return this;
    }


    /**
     * 当前请求为get 方法
     *
     * @return HttpUtils
     */
    public HttpUtils get() {
        this.mEngineOption.setMethodType(EngineOption.METHOD_GET);
        return this;
    }

    /**
     * 设置当前请求为post 方法
     *
     * @param formData 设置post的内容请求方式
     * @return HttpUtils
     */
    public HttpUtils post(FormData formData) {
        //默认设置
        this.mEngineOption.getHeaders().put("Content-Type", formData.getValue());
        this.mEngineOption.setMethodType(EngineOption.METHOD_POST);
        return this;
    }


    /**
     * 设置当前请求为post 方法
     *
     * @return
     */
    public HttpUtils post() {
        //默认设置
        this.mEngineOption.getHeaders().put("Content-Type", FormData.FORM_DATA.getValue());
        this.mEngineOption.setMethodType(EngineOption.METHOD_POST);
        return this;
    }


    /**
     * 设置缓存
     *
     * @return HttpUtils
     */
    public HttpUtils cache() {
        this.mEngineOption.setCache(true);
        return this;
    }

    /**
     * 切换引擎
     *
     * @param engine 网络请求框架
     * @return HttpUtils
     */
    public HttpUtils exechange(IHttpEngine engine) {
        sHttpEngine = engine;
        return this;
    }

    /**
     * 请求回调运行在主线程中
     *
     * @return httpUtils
     */
    public HttpUtils uiThread() {
        mEngineOption.setThreadType(EngineOption.UI_THREAD);
        return this;
    }

    /**
     * 请求回调运行在子线程
     *
     * @return HttpUtils
     */
    public HttpUtils workThread() {
        mEngineOption.setThreadType(EngineOption.WORK_THREAD);
        return this;
    }

    /**
     * 开始执行请求
     */
    public void execute() {
        execute(IEngineCallback.DEFAULT_CALLBACK);
    }

    /**
     * 开始执行请求
     *
     * @param callback 请求结果回调方法
     */
    public void execute(IEngineCallback callback) {

        if (TextUtils.isEmpty(mEngineOption.getUrl())) {
            throw new IllegalArgumentException("请求路径不能为空");
        }

        callback.onPreExecute(mEngineOption);

        if (mEngineOption.getMethodType() == EngineOption.METHOD_GET) {
            sHttpEngine.get(mEngineOption, callback);
            return;
        }

        if (mEngineOption.getMethodType() == EngineOption.METHOD_POST) {
            sHttpEngine.post(mEngineOption, callback);
        }
    }

    /**
     * 根据tag取消请求
     */
    public static void cancelTag(Object tag) {
        sHttpEngine.cancel(tag);
    }
}
