package cn.xwj.frame;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import cn.xwj.widget.navigationbar.AbsNavigationBar;

/**
 * Author: xw
 * Date: 2018-03-09 09:39:12
 * Description: DefaultNavigationBar <this is description>.
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder> {

    private DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        // 绑定效果
        setText(R.id.title, mBuilder.mTitle);
        setText(R.id.right_text, mBuilder.mRightText);
        setOnClickListener(R.id.right_text, mBuilder.mRightClickListener);
        // 左边 要写一个默认的  finishActivity
        setOnClickListener(R.id.back, mBuilder.mLeftClickListener);
    }


    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar> {

        private String mTitle;
        private String mRightText;
        private View.OnClickListener mRightClickListener;
        private View.OnClickListener mLeftClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).finish();
            }
        };

        public Builder(Context context) {
            super(context);
        }


        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setRightText(String rightText) {
            this.mRightText = rightText;
            return this;
        }

        public Builder setLeftClickListener(View.OnClickListener listener) {
            this.mLeftClickListener = listener;
            return this;
        }

        public Builder setRightClickListener(View.OnClickListener listener) {
            this.mRightClickListener = listener;
            return this;
        }

        @Override
        public DefaultNavigationBar build() {
            return new DefaultNavigationBar(this);
        }


    }

}
