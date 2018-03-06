package cn.xwj.essayjoke;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import cn.xwj.base.ioc.Injector;
import cn.xwj.base.ioc.annotation.CheckNet;
import cn.xwj.base.ioc.annotation.ContentView;
import cn.xwj.base.ioc.annotation.OnClick;
import cn.xwj.base.ioc.annotation.ViewById;
import cn.xwj.easy.permission.EPermission;
import cn.xwj.easy.util.ToastUtil;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
        EPermission.create(this).permission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new EPermission.PermissionResult() {
                    @Override
                    public void onResult(boolean granted) {
                        if (!granted) {
                            MainActivity.this.finish();
                        } else {
                            granted();
                        }
                    }
                });
    }

    private void granted() {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "fix.apatch";
        if(new File(filePath).exists()){
            try {
                BaseApplication.sPatchManager.addPatch(filePath);
                ToastUtil.showMsg("修复成功");
            } catch (IOException e) {
                e.printStackTrace();
                ToastUtil.showMsg("修复失败");
            }
        }
        mTextView.setText("你好");
    }


    @CheckNet
    @OnClick(R.id.tv)
    private void click() {
        Toast.makeText(this, 2 / 1 + "测试", Toast.LENGTH_SHORT).show();
    }
}
