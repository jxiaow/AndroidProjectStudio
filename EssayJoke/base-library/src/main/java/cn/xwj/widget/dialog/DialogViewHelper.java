package cn.xwj.widget.dialog;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Author: xw
 * Date: 2018-03-08 10:45:55
 * Description: DialogViewHelper <this is description>.
 */

class DialogViewHelper {

    private View mContentView;
    private SparseArrayCompat<WeakReference<View>> mViews;

    public DialogViewHelper(@NonNull Context context, @LayoutRes int layoutId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutId, null);
    }

    public DialogViewHelper() {
        mViews = new SparseArrayCompat<>();
    }


    public void setContentView(View contentView) {
        mContentView = contentView;
    }

    public View getContentView() {
        return mContentView;
    }

    public void setText(int viewId, CharSequence charSequence) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(charSequence);
        }
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> viewWeakReference = mViews.get(viewId);
        View view = null;
        if (viewWeakReference != null) {
            view = viewWeakReference.get();
        }

        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }
}
