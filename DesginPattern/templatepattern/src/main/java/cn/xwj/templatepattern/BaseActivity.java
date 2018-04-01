package cn.xwj.templatepattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/1
 * Description: BaseActivity 利用模板设计模式生成BaseActivity.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initTitle();
        initView();
        initData();
    }

    /**
     * 初始化data
     */
    protected abstract void initData();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 设置标题
     */
    protected abstract void initTitle();

    /**
     * 设置ContentView
     */
    public abstract void setContentView();
}
