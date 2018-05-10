package cn.xwj.httptest.http;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: IHttpEngine
 */
public interface IHttpEngine {
    /**
     * get 方法
     *
     * @param option   引擎配置
     * @param callback 回调方法
     */
    void get(EngineOption option, IEngineCallback callback);

    /**
     * post 方法
     *
     * @param option   引擎配置
     * @param callback 回调方法
     */
    void post(EngineOption option, IEngineCallback callback);

    /**
     * 根据tag 取消任务
     *
     * @param tag 绑定的tag
     */
    void cancel(Object tag);
}
