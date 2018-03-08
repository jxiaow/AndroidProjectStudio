package cn.xwj.essayjoke;

import android.widget.TextView;

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
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setContentView(R.layout.detail_comment_dialog)
                .setText(R.id.submit_btn, "接收")
                .fullWidth()
                .show();
    }
}
