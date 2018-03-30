package cn.xwj.ioc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;
import cn.xwj.ioc.injector.Injector;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.tv)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);

        mTextView.setText("加载fragment");
    }

    @OnClick(R.id.tv)
    private void replace(View view) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new BlankFragment())
                .commit();
    }
}
