package cn.xwj.essayjoke;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import cn.xwj.base.ioc.Injector;
import cn.xwj.base.ioc.annotation.CheckNet;
import cn.xwj.base.ioc.annotation.ContentView;
import cn.xwj.base.ioc.annotation.OnClick;
import cn.xwj.base.ioc.annotation.ViewById;
import cn.xwj.easy.permission.EPermission;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Injector.inject(this);
        EPermission.create(this).permission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new EPermission.PermissionResult() {
                    @Override
                    public void onResult(boolean granted) {
                        if (!granted) {
                            MainActivity.this.finish();
                        }
                    }
                });
        mTextView.setText("你好");
    }


    @CheckNet
    @OnClick(R.id.tv)
    private void click() {
        int i = 2 / 0;
        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
    }
}
