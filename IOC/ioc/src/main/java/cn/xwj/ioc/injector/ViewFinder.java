package cn.xwj.ioc.injector;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

import cn.xwj.ioc.R;

/**
 * Author: xw
 * Date: 2018-03-30 14:32:32
 * Description: ViewFinder: findViewById的辅助类.
 */

public class ViewFinder {
    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewFinder(View view) {
        this.mView = view;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        if (mActivity != null) {
            return mActivity.findViewById(id);
        }
        return mView.findViewById(id);
    }
}
