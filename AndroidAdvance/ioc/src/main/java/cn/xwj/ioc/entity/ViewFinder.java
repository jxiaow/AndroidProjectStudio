package cn.xwj.ioc.entity;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

import cn.xwj.ioc.R;

/**
 * Author: xw
 * Date: 2018-03-01 12:25:53
 * Description: ViewFinder <findViewById 的辅助类>.
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
    public <T extends View> T findViewById(@IdRes int id){
        return (T) (mActivity != null?mActivity.findViewById(id):mView.findViewById(id));
    }
}
