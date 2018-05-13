package cn.xwj.skinmodel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import cn.xwj.skinmodel.skin.SkinManager;

public class MainActivity extends BaseSkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.skin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String skinPath = Environment.getExternalStorageDirectory() + File.separator + "skin.skin";
                File file = new File(skinPath);
                if (file.exists()) {
                    try {
                        SkinManager.getInstance().loadSkin(skinPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().resetToDefault();
            }
        });
    }
}
