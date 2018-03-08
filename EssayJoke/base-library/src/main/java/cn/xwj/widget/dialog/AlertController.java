package cn.xwj.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.util.SparseArrayCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.Dictionary;

/**
 * Author: xw
 * Date: 2018-03-08 12:19:12
 * Description: AlertController <this is description>.
 */

public class AlertController {

    private AlertDialog mDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(AlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public DialogViewHelper getViewHelper() {
        return mViewHelper;
    }

    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        public View mView;
        public int mLayoutId;
        public boolean mCancelable = true;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public SparseArrayCompat<CharSequence> mTextArray = new SparseArrayCompat<>();
        public SparseArrayCompat<View.OnClickListener> mClickArray = new SparseArrayCompat<>();
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mAnimations = 0;
        public int mGravity = Gravity.CENTER;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        public void apply(AlertController alertController) {
            if (mLayoutId != 0) {
                alertController.mViewHelper = new DialogViewHelper(mContext, mLayoutId);
            }
            if (mView != null) {
                alertController.mViewHelper = new DialogViewHelper();
                alertController.mViewHelper.setContentView(mView);
            }
            if (alertController.mViewHelper == null) {
                throw new IllegalArgumentException("请设置布局 setContentView()");
            }

            alertController.mDialog.setContentView(alertController.mViewHelper.getContentView());

            for (int i = 0; i < mTextArray.size(); i++) {
                alertController.mViewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            for (int i = 0; i < mClickArray.size(); i++) {
                alertController.mViewHelper.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            alertController.mWindow.setGravity(mGravity);
            if (mAnimations != 0) {
                alertController.mWindow.setWindowAnimations(mAnimations);
            }

            WindowManager.LayoutParams attributes = alertController.mWindow.getAttributes();
            attributes.width = mWidth;
            attributes.height = mHeight;
            alertController.mWindow.setAttributes(attributes);
        }
    }
}
