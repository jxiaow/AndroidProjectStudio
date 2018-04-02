package cn.xwj.androidfix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFixDex();
//        loadPath();
    }

    private void loadFixDex() {
        String dexPath = Environment.getExternalStorageDirectory()
                + File.separator + "fix.dex";
        File file = new File(dexPath);
        if (file.exists()) {
            try {
                BaseApplication.sDexFixManager.addFixDex(file.getAbsolutePath());
                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "修复失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadPath() {
//        String patchPath = Environment.getExternalStorageDirectory()
//                + File.separator + "fix.apatch";
//        File file = new File(patchPath);
//        if (file.exists()) {
//            try {
//                BaseApplication.sPatchManager.addPatch(patchPath);
//                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    public void test(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

}
