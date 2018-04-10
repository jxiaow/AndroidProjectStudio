package cn.xwj.dialog.alertdialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

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
        mAlertController = new AlertController(this, getWindow());
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
            mParam.mContentView = null;
            return this;
        }

        public Builder setContentView(@NonNull View view) {
            mParam.mContentView = view;
            mParam.layoutId = 0;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mParam.mCancelable = cancelable;
            return this;
        }

        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                mParam.mAnimation = R.style.dialog_from_bottom_anim;
            }
            mParam.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder setGravity(int gravity) {
            mParam.mGravity = gravity;
            return this;
        }

        public Builder fullWidth() {
            mParam.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            mParam.mHeight = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder addDefaultAnimation() {
            mParam.mAnimation = R.style.dialog_scale_anim;
            return this;
        }

        public Builder setSize(int width, int height) {
            this.mParam.mWidth = width;
            this.mParam.mHeight = height;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener cancelListener) {
            mParam.mOnCancelListener = cancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener dismissListener) {
            mParam.mOnDismissListener = dismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener keyListener) {
            mParam.mOnKeyListener = keyListener;
            return this;
        }

        public Builder setOnClickListener(@IdRes int id, View.OnClickListener listener) {
            mParam.mClickArray.put(id, listener);
            return this;
        }

        public Builder setText(@IdRes int id, String text) {
            mParam.mTextArray.put(id, text);
            return this;
        }

        public AlertDialog create() {
            final AlertDialog dialog = new AlertDialog(mParam.mContext, mParam.mTheme);
            mParam.apply(dialog.mAlertController);
            dialog.setCancelable(mParam.mCancelable);
            if (mParam.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(mParam.mOnCancelListener);
            dialog.setOnDismissListener(mParam.mOnDismissListener);
            if (mParam.mOnKeyListener != null) {
                dialog.setOnKeyListener(mParam.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }


    }
}
