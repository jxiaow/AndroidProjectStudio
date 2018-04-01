package cn.xwj.essayjoke;

import android.os.Environment;

import java.io.File;

import cn.xwj.frame.BaseSkinActivity;
import cn.xwj.frame.skin.SkinManager;
import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseSkinActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void setContentView() {
    }

    @OnClick(R.id.switch_skin)
    private void switchSkin() {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "red.skin";
        SkinManager.getInstance().loadSkin(filePath);
    }

    @OnClick(R.id.restore_skin)
    private void restoreSkin() {

    }

    @OnClick(R.id.skip)
    private void skip() {

    }
}
