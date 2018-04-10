package cn.xwj.dialog.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.xwj.dialog.R;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/2
 * Description: AlertController.
 */

class AlertController {
    private AlertDialog mDialog;
    private Window mWindow;
    private View mContentView;
    private SparseArrayCompat<View> mViewArray = new SparseArrayCompat<>();

    public AlertController(AlertDialog alertDialog, Window window) {
        this.mDialog = alertDialog;
        this.mWindow = window;
    }

    public <T extends View> T getView(@IdRes int id) {
        View view = mViewArray.get(id);
        if (view == null) {
            view = mContentView.findViewById(id);
            mViewArray.put(id, view);
        }
        return (T) view;
    }

    public static class AlertParam {

        public int layoutId;
        public View mContentView;
        public Context mContext;
        public int mTheme;
        public boolean mCancelable = true;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public Dialog.OnKeyListener mOnKeyListener;
        public int mGravity = Gravity.CENTER;
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mAnimation;
        public SparseArrayCompat<View.OnClickListener> mClickArray
                = new SparseArrayCompat<>();
        public SparseArrayCompat<String> mTextArray = new SparseArrayCompat<>();

        public AlertParam(Context context, int themeResId) {
            this.mContext = context;
            this.mTheme = themeResId;
        }

        public void apply(AlertController alertController) {
            if (layoutId != -1) {
                alertController.mContentView =
                        LayoutInflater.from(mContext).inflate(layoutId, null);
            }

            if (mContentView != null) {
                alertController.mContentView = mContentView;
            }

            if (mContentView == null) {
                throw new IllegalArgumentException("please call setContentView()");
            }

            for (int i = 0; i < mClickArray.size(); i++) {
                int viewId = mClickArray.keyAt(i);
                alertController.getView(viewId).setOnClickListener(mClickArray.get(viewId));
            }

            for (int i = 0; i < mTextArray.size(); i++) {
                int viewId = mTextArray.keyAt(i);
                TextView view = alertController.getView(viewId);
                view.setText(mTextArray.get(viewId));
            }

            alertController.mDialog.setContentView(alertController.mContentView);
            alertController.mDialog.setCancelable(mCancelable);
            alertController.mWindow.setGravity(mGravity);

            alertController.mWindow.setWindowAnimations(mAnimation);

            WindowManager.LayoutParams attributes = alertController.mWindow.getAttributes();
            attributes.width = mWidth;
            attributes.height = mHeight;
            alertController.mWindow.setAttributes(attributes);


        }
    }
}
