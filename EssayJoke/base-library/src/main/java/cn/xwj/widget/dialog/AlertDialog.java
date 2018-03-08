package cn.xwj.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import cn.xwj.base.R;

/**
 * Author: xw
 * Date: 2018-03-08 12:17:13
 * Description: AlertDialog <this is description>.
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

    public  <T extends View> T getView(int viewId) {
        return mAlertController.getViewHelper().getView(viewId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mAlertController.getViewHelper().setOnClickListener(viewId, listener);
    }

    public void setText(int viewId, CharSequence charSequence){
        mAlertController.getViewHelper().setText(viewId,charSequence);
    }

    public static class Builder {
        private AlertController.AlertParams mParams;


        public Builder(Context context) {
            this(context, R.style.dialog);
        }


        public Builder(Context context, int themeResId) {
            mParams = new AlertController.AlertParams(context, themeResId);
        }

        public Builder setContentView(@LayoutRes int layoutId) {
            mParams.mView = null;
            mParams.mLayoutId = layoutId;
            return this;
        }

        public Builder setContentView(View view) {
            mParams.mLayoutId = 0;
            mParams.mView = view;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mParams.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            mParams.mOnCancelListener = onCancelListener;
            return this;
        }


        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            mParams.mOnDismissListener = onDismissListener;
            return this;
        }


        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            mParams.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setText(int viewId, CharSequence charSequence) {
            mParams.mTextArray.put(viewId, charSequence);
            return this;
        }

        public Builder setOnClickListener(int viewId, View.OnClickListener listener) {
            mParams.mClickArray.put(viewId, listener);
            return this;
        }

        public Builder fullWidth() {
            mParams.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder setWidthAndHeight(int width, int height) {
            mParams.mWidth = width;
            mParams.mHeight = height;
            return this;
        }

        public Builder fromBottom(boolean isAnimations) {
            if (isAnimations) {
                mParams.mAnimations = R.style.dialog_from_bottom_anim;
            }
            mParams.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder addDefaultAnimations() {
            mParams.mAnimations = R.style.dialog_scale_anim;
            return this;
        }

        public Builder setAnimations(int styleAnimations) {
            mParams.mAnimations = styleAnimations;
            return this;
        }


        public AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(mParams.mContext, mParams.mThemeResId);
            mParams.apply(dialog.mAlertController);
            dialog.setCancelable(mParams.mCancelable);
            if (mParams.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(mParams.mOnCancelListener);
            dialog.setOnDismissListener(mParams.mOnDismissListener);
            if (mParams.mOnKeyListener != null) {
                dialog.setOnKeyListener(mParams.mOnKeyListener);
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
