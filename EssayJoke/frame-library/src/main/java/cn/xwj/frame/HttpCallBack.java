package cn.xwj.frame;

import android.content.Context;
import android.support.v4.util.ArrayMap;


import com.google.gson.Gson;

import java.lang.reflect.Type;

import cn.xwj.http.HttpUtils;
import cn.xwj.http.IEngineCallBack;

/**
 * Created by xw on 2018/3/10.
 */

public abstract class HttpCallBack<T> implements IEngineCallBack {

    @Override
    public void onSuccess(String result) {
        Type type = HttpUtils.analysisClassType(this);
        Gson gson = new Gson();
        onSuccess((T) gson.fromJson(result, type));
    }

    protected abstract void onSuccess(T t);

    @Override
    public void preExecute(Context context, ArrayMap<String, Object> params) {
        //组织公共参数
        preExecute();
    }

    protected void preExecute() {


    }
}
