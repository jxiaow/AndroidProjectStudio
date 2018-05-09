package cn.xwj.httptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.xwj.httptest.http.HttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baiduUrl = "https://www.baidu.com";
        HttpUtils.with(this)
                .url(baiduUrl)
                .tag(this)
                .addParam("userName", "理视")
                .cache()
                .uiThread()
                .get()
                .execute();

        HttpUtils.cancelTag(this);

    }
}
