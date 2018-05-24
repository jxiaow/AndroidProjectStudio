package cn.xwj.skinmodel.skin.support;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatViewInflater;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Method;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinViewInflater
 */
public class SkinViewInflater extends AppCompatViewInflater {
    private static final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT >= 21;

    private ArrayMap<String, Method> mMethodArrayMap;
    private Window mWindow;

    public SkinViewInflater(Window window) {
        this.mWindow = window;
        this.mMethodArrayMap = new ArrayMap<>();
    }

    /**
     * 创建view
     *
     * @param parent  viewParent
     * @param name    view 名称
     * @param context 上下文
     * @param attrs   view的各种属性
     * @return 返回值
     */
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    // If we have a XmlPullParser, we can detect where we are in the layout
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    // Otherwise we have to use the old heuristic
                    : shouldInheritContext((ViewParent) parent);
        }

        try {

            Method createViewMethod = mMethodArrayMap.get("createView");
            if (createViewMethod == null) {
                createViewMethod = AppCompatViewInflater.class.getDeclaredMethod("createView",
                        View.class, String.class, Context.class, AttributeSet.class, boolean.class,
                        boolean.class, boolean.class, boolean.class);
                createViewMethod.setAccessible(true);
                mMethodArrayMap.put("createView", createViewMethod);
            }
            return (View) createViewMethod.invoke(this, parent, name, context, attrs, inheritContext,
                    IS_PRE_LOLLIPOP, /* Only read android:theme pre-L (L+ handles this anyway) */
                    true, /* Read read app:theme as a fallback at all times for legacy reasons */
                    VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if enabled */
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        try {
            Method createViewFromTagMethod = mMethodArrayMap.get("createViewFromTag");
            if (createViewFromTagMethod == null) {
                createViewFromTagMethod = AppCompatViewInflater.class.getDeclaredMethod(
                        "createViewFromTag",Context.class, String.class, AttributeSet.class);
                createViewFromTagMethod.setAccessible(true);
                mMethodArrayMap.put("createViewFromTag", createViewFromTagMethod);
            }
            return (View) createViewFromTagMethod.invoke(this, context, name, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = mWindow.getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    public void clear() {
        mMethodArrayMap.clear();
    }
}
