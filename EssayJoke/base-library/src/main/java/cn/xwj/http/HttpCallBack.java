package cn.xwj.http;

/**
 * Author: xw
 * Date: 2018-03-09 14:04:49
 * Description: HttpCallBack <this is description>.
 */

public interface HttpCallBack {

    void onError(Exception e);

    void onSuccess(String result);

    HttpCallBack DEFAULT_CALLBACK = new HttpCallBack() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }
    };
}
