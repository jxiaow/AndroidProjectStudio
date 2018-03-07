package cn.xwj.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.xwj.ioc.Injector;

/**
 * Author: xw
 * Date: 2018-03-07 09:37:47
 * Description: BaseActivity <this is description>.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        Injector.inject(this);
        initTitle();
        initView();
        initData();
    }

    public void actionStart(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTitle();

    protected abstract void setContentView();


}
