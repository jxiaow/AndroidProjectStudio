package cn.xwj.essayjoke;

import android.Manifest;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.xwj.easy.permission.EPermission;
import cn.xwj.easy.util.LogUtil;
import cn.xwj.easy.util.ToastUtil;
import cn.xwj.frame.BaseSkinActivity;
import cn.xwj.ioc.annotation.CheckNet;
import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseSkinActivity {
    private static final String TAG = "MainActivity";
    @ViewById(R.id.tv)
    private TextView mTextView;


    @Override
    protected void initData() {
        EPermission.create(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(new EPermission.PermissionResult() {
                    @Override
                    public void onResult(boolean granted) {
                        if (granted) {
                            String filePath = Environment.getExternalStorageDirectory() + File.separator + "fix.dex";
                            File file = new File(filePath);
                            if (file.exists()) {
                                try {
                                    BaseApplication.sFixDexManager.addFixDex(filePath);
                                    ToastUtil.showMsg("修复成功");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ToastUtil.showMsg("修复失败");
                                }
                            }
                        }
                    }
                });
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
    }

    @CheckNet
    @OnClick(R.id.tv)
    private void click() {
        Toast.makeText(this, 2 / 0 + "测试", Toast.LENGTH_SHORT).show();
    }
}
