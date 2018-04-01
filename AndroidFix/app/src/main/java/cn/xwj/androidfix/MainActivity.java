package cn.xwj.androidfix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity();
        loadPath();
    }

    private void loadPath() {
        String patchPath = Environment.getExternalStorageDirectory()
                + File.separator + "fix.apatch";
        File file = new File(patchPath);
        if (file.exists()) {
            try {
                BaseApplication.sPatchManager.addPatch(patchPath);
                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void test(View view) {
        Toast.makeText(this, "test: " + 1 / 2, Toast.LENGTH_LONG).show();
    }

}
