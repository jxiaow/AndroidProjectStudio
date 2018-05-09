package cn.xwj.httptest.http;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-09 2018/5/9
 * Description: IEngineCallback
 */
public interface IEngineCallback {

    void onSuccess(String result);

    void onError(Exception e);

    void onPreExecute(EngineOption engineOption);


    IEngineCallback DEFAULT_CALLBACK = new IEngineCallback() {
        @Override
        public void onSuccess(String result) {
        }

        @Override
        public void onError(Exception e) {
        }

        @Override
        public void onPreExecute(EngineOption engineOption) {
        }
    };

}
