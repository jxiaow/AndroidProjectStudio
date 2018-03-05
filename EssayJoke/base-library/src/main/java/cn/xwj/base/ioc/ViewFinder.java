package cn.xwj.base.ioc;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author: xw
 * Date: 2018-03-05 10:52:05
 * Description: ViewFinder <this is description>.
 */

public class ViewFinder {
    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        mActivity = activity;
    }

    public ViewFinder(View view) {
        mView = view;
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int id) {
        return (T) (mActivity != null ? mActivity.findViewById(id) : mView.findViewById(id));
    }

    public Context getContext() {
        return mActivity != null ? mActivity : mView.getContext();
    }
}
