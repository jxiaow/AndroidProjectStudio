package cn.xwj.httptest.http;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: EngineOption
 */
class EngineOption {

    /**
     * GET 请求
     */
    static final int METHOD_GET = 0x0001;
    /**
     * post 请求
     */
    static final int METHOD_POST = 0x0002;

    /**
     * 工作线程（子线程）
     */
    static final int WORK_THREAD = 0x00001;
    /**
     * UI线程（主线程）
     */
    static final int UI_THREAD = 0x00002;

    //上下文
    private Context mContext;
    //Tag
    private Object mTag;
    //是否需要缓存
    private boolean cache;
    //请求的url
    private String url;
    // 请求的方法
    private int methodType = METHOD_GET;
    // 请求参数
    private Map<String, Object> mParams;
    // 请求头
    private Map<String, String> mHeaders;
    //回调方法运行线程
    private int threadType = WORK_THREAD;


    public EngineOption(Context context) {
        this.mContext = context;
        this.mParams = new HashMap<>();
        this.mHeaders = new HashMap<>();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object tag) {
        mTag = tag;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public Map<String, Object> getParams() {
        return mParams;
    }

    public void setParams(Map<String, Object> params) {
        mParams = params;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public void setHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }

    public int getThreadType() {
        return threadType;
    }

    public void setThreadType(int threadType) {
        this.threadType = threadType;
    }
}
