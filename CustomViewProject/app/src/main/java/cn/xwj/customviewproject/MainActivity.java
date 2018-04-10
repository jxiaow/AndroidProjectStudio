package cn.xwj.customviewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import cn.xwj.customviewproject.widget.Bezier2;
import cn.xwj.dialog.alertdialog.AlertDialog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AlertDialog.Builder(this)
                .setContentView(R.layout.activity_main)
                .setCancelable(true)
                .fromBottom(true)
                .fullWidth()
                .addDefaultAnimation()
                .show();
    }
}
