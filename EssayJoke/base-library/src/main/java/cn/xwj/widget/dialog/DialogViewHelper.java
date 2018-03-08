package cn.xwj.widget.dialog;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Author: xw
 * Date: 2018-03-08 12:19:22
 * Description: DialogViewHelper <this is description>.
 */

public class DialogViewHelper {
    private View mContentView;
    private SparseArrayCompat<WeakReference<View>> mViews;

    public DialogViewHelper() {
        this.mViews = new SparseArrayCompat<>();
    }

    public DialogViewHelper(Context context, int layoutId) {
        this();
        this.mContentView = LayoutInflater.from(context).inflate(layoutId, null);
    }


    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    public void setText(int viewId, CharSequence charSequence) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(charSequence);
        }
    }

    public  <T extends View> T getView(int viewId) {
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

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public View getContentView() {
        return mContentView;
    }
}
