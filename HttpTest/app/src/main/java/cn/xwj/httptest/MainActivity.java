package cn.xwj.httptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.xwj.httptest.http.EngineOption;
import cn.xwj.httptest.http.HttpUtils;
import cn.xwj.httptest.http.IEngineCallback;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpUtils.with(this)
                .url("http://www.wanandroid.com/user/login")
                .tag(this)
                .workThread()
                .post()
                .addParam("username", "张三")
                .addParam("password", "123456")
                .execute(new IEngineCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, result);
                        Log.e(TAG, "threadId: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        Log.e(TAG, "threadId: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onPreExecute(EngineOption engineOption) {

                    }
                });

//        String baiduUrl = "http://www.wanandroid.com/article/list/1/json";
//        HttpUtils.with(this)
//                .url(baiduUrl)
//                .tag(this)
//                .uiThread()
//                .get()
//                .execute(new IEngineCallback() {
//                    @Override
//                    public void onSuccess(String result) {
//                        Log.e(TAG, result);
//                        Log.e(TAG, "threadId: " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.e(TAG, e.getMessage());
//                        Log.e(TAG, "threadId: " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onPreExecute(EngineOption engineOption) {
//
//                    }
//                });

//        HttpUtils.cancelTag(this);

    }
}
