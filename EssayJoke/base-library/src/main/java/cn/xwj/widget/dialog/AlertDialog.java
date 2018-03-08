package cn.xwj.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import cn.xwj.base.R;

/**
 * Author: xw
 * Date: 2018-03-08 10:43:56
 * Description: AlertDialog <this is description>.
 */

public class AlertDialog extends Dialog {

    private AlertController mAlert;

    public AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }


    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId,text);
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnclickListener(viewId,listener);
    }


    public static class Builder {

        private AlertController.AlertParams mParams;


        public Builder(Context context) {
            this(context, R.style.dialog);
        }


        public Builder(Context context, int themeResId) {
            mParams = new AlertController.AlertParams(context, themeResId);
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

        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                mParams.mAnimations = R.style.dialog_from_bottom_anim;
            }
            mParams.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder addDefaultAnimation() {
            mParams.mAnimations = R.style.dialog_scale_anim;
            return this;
        }

        public Builder setAnimations(int styleAnimations) {
            mParams.mAnimations = styleAnimations;
            return this;
        }

        public Builder setContentView(int layoutId) {
            mParams.mView = null;
            mParams.mLayoutId = layoutId;
            return this;
        }

        public Builder setContentView(View view) {
            mParams.mLayoutId = 0;
            mParams.mView = view;
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

        public AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(mParams.mContext, mParams.mThemeResId);
            mParams.apply(dialog.mAlert);
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
