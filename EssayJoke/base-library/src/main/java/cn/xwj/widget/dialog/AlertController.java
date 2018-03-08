package cn.xwj.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.util.SparseArrayCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Author: xw
 * Date: 2018-03-08 10:45:38
 * Description: AlertController <this is description>.
 */

class AlertController {
    private AlertDialog mDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(AlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mViewHelper.setOnClickListener(viewId, listener);
    }


    public static class AlertParams {

        public Context mContext;
        public int mThemeResId;
        public boolean mCancelable = true;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public View mView;
        public int mLayoutId = 0;
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

        public void apply(AlertController controller) {
            DialogViewHelper viewHelper = null;
            if (mView != null) {
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }

            if (mLayoutId != 0) {
                viewHelper = new DialogViewHelper(mContext, mLayoutId);
            }

            if (viewHelper == null) {
                throw new IllegalArgumentException("请设置布局文件 setContentView()");
            }

            controller.mDialog.setContentView(viewHelper.getContentView());
            controller.mViewHelper = viewHelper;

            for (int i = 0; i < mTextArray.size(); i++) {
                viewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            for (int i = 0; i < mClickArray.size(); i++) {
                viewHelper.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            controller.mWindow.setGravity(mGravity);
            if (mAnimations != 0) {
                controller.mWindow.setWindowAnimations(mAnimations);
            }

            WindowManager.LayoutParams attributes = controller.mWindow.getAttributes();
            attributes.width = mWidth;
            attributes.height = mHeight;
            controller.mWindow.setAttributes(attributes);
        }
    }


}
