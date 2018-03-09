package cn.xwj.widget.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: xw
 * Date: 2018-03-09 09:22:48
 * Description: AbsNavigationBar <this is description>.
 */

public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder> implements INavigationBar {
    protected P mBuilder;
    private View mNavigationView;

    public AbsNavigationBar(P params) {
        this.mBuilder = params;
        createAndBindView();
    }

    protected void setText(int viewId, String title) {
        TextView view = mNavigationView.findViewById(viewId);
        if (view != null && !TextUtils.isEmpty(title)) {
            view.setVisibility(View.VISIBLE);
            view.setText(title);
        }
    }

    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        mNavigationView.findViewById(viewId).setOnClickListener(listener);
    }

    private void createAndBindView() {

        if (mBuilder.mParent == null) {
            ViewGroup activityRoot = (ViewGroup) ((Activity) mBuilder.mContext).getWindow().getDecorView();
            mBuilder.mParent = (ViewGroup) activityRoot.getChildAt(0);
        }

        if (mBuilder.mParent == null) {
            return;
        }

        mNavigationView = LayoutInflater.from(mBuilder.mContext).inflate(bindLayoutId(), mBuilder.mParent, false);
        mBuilder.mParent.addView(mNavigationView, 0);
        applyView();
    }

    public static abstract class Builder<T extends AbsNavigationBar> {
        protected Context mContext;
        protected ViewGroup mParent;

        public Builder(Context context) {
            this(context, null);
        }

        public Builder(Context context, ViewGroup parent) {
            this.mContext = context;
            this.mParent = parent;
        }

        public abstract T build();
    }

}
