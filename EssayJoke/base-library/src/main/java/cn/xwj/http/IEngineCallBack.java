package cn.xwj.http;

import android.content.Context;
import android.support.v4.util.ArrayMap;

/**
 * Created by xw on 2018/3/10.
 */

public interface IEngineCallBack {
    void onError(Exception e);

    void onSuccess(String result);

    IEngineCallBack DEFAULT_CALL_BACK = new IEngineCallBack() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }

        @Override
        public void preExecute(Context context, ArrayMap<String, Object> params) {

        }


    };

    void preExecute(Context context, ArrayMap<String, Object> params);
}
