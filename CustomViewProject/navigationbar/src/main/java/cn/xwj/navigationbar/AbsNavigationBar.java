package cn.xwj.navigationbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/8
 * Description: AbsNavigationBar.
 */

public abstract class AbsNavigationBar implements INavigationBar {
    private Holder mHolder;
    private View mNavigationView;

    public AbsNavigationBar(Holder holder) {
        this.mHolder = holder;
        createAndBind();
    }

    private void createAndBind() {
        if (mHolder.mParent == null) {
            View decorView = ((Activity) mHolder.mContext).getWindow().getDecorView();
            ViewGroup viewGroup = (ViewGroup) decorView;
            View view = viewGroup.getChildAt(0);
            mHolder.mParent = (ViewGroup) view;
        }

        if (mHolder.mParent == null) {
            return;
        }
        mNavigationView = LayoutInflater.from(mHolder.mContext)
                .inflate(bindLayoutId(), mHolder.mParent, false);
        mHolder.mParent.addView(mNavigationView, 0);
        apply();
    }


    public static abstract class Holder {
        public Context mContext;
        public ViewGroup mParent;

        public Holder(Context context) {
            this(context, null);
        }

        public Holder(Context context, ViewGroup parent) {
            this.mContext = context;
            this.mParent = parent;
        }

        public abstract <T extends AbsNavigationBar> T create();
    }
}
