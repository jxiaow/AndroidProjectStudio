package cn.xwj.androidadvance;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import cn.xwj.base.BaseActivity;
import cn.xwj.ioc.annotation.CheckNet;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;
import cn.xwj.ioc.inject.Injector;

public class MainActivity extends BaseActivity {

    @ViewById(R.id.tv)
    private TextView mTextView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTextView.setText("你好");
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @CheckNet(methodName = "show")
    @OnClick(R.id.tv)
    private void click() {
        Toast.makeText(this, mTextView.getText(), Toast.LENGTH_SHORT).show();
    }

    private void show(){
        Snackbar.make(mTextView, "网络不可用", Snackbar.LENGTH_SHORT)
                .setAction("重试", v ->{} ).show();
    }
}
