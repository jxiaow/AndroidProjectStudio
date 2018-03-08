package cn.xwj.customviewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import cn.xwj.customviewproject.widget.Bezier2;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Bezier2 bezier2 = findViewById(R.id.bezier);
        RadioGroup group = findViewById(R.id.radio_group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bezier2.setMode(checkedId == R.id.radio_btn1);
            }
        });
    }
}
