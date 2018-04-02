package cn.xwj.dialog.alertdialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

import cn.xwj.dialog.R;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/2
 * Description: AlertDialog 万能dialog.
 */

public class AlertDialog extends Dialog {
    private AlertController mAlertController;

    public AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    public static class Builder {
        private AlertController.AlertParam mParam;


        public Builder(@NonNull Context context) {
            this(context, R.style.defaultDialogTheme);
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            mParam = new AlertController.AlertParam(context, themeResId);
        }

        public Builder setContentView(@LayoutRes int layoutId) {
            mParam.layoutId = layoutId;
            mParam.mView = null;
            return this;
        }
    }
}
