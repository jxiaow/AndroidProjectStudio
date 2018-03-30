package cn.xwj.ioc.injector;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;

/**
 * Author: xw
 * Date: 2018-03-30 14:30:15
 * Description: Injector: 注入实现类.
 */

public class Injector {
    private final static List<Class<?>> IGNORE_CLASS = new ArrayList<>();

    static {
        IGNORE_CLASS.add(Activity.class);
        IGNORE_CLASS.add(FragmentActivity.class);
        IGNORE_CLASS.add(AppCompatActivity.class);
        IGNORE_CLASS.add(Fragment.class);
        IGNORE_CLASS.add(android.support.v4.app.Fragment.class);
        IGNORE_CLASS.add(Object.class);
    }

    /**
     * 注入activity
     */
    public static void inject(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("activity is null");
        }
        //用findContentView()
        Class<? extends Activity> aClass = activity.getClass();
        ContentView contentView = findContentView(aClass);
        if (contentView != null) {
            setContentView(activity, contentView);
        }
        //注入
        inject(new ViewFinder(activity), activity);
    }


    /**
     * 注入view， 在fragment等中使用
     */
    public static void inject(Object object, View view) {
        if (view == null || object == null) {
            throw new IllegalArgumentException("view or object is null");
        }
        inject(new ViewFinder(view), object);
    }

    /**
     * view，fragment等控件配合{@link cn.xwj.ioc.annotation.ContentView}使用
     */
    public static View inject(Object object, LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        ContentView contentView = findContentView(object.getClass());
        if (contentView == null) {
            throw new IllegalArgumentException("please call @ContentView() in " + object.getClass());
        }
        int value = contentView.value();
        if (value == -1) {
            throw new IllegalArgumentException("@ContentView()'s value is -1");
        }
        View view = inflater.inflate(value, container, false);
        if (view == null) {
            throw new IllegalArgumentException("not find " + value + " with " + object.getClass());
        }
        inject(new ViewFinder(view), object);
        return view;
    }

    /**
     * 将view注入各种控件
     *
     * @param viewFinder findView辅助类
     * @param o          需要注入的类
     */
    private static void inject(ViewFinder viewFinder, Object o) {
        injectFields(viewFinder, o);
        injectEvents(viewFinder, o);
    }

    /**
     * 注入动作事件
     */
    private static void injectEvents(ViewFinder viewFinder, Object object) {
        Class<?> clazz = object.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (declaredMethods == null || declaredMethods.length <= 0) {
            return;
        }
        for (Method declaredMethod : declaredMethods) {
            if (!declaredMethod.isAnnotationPresent(OnClick.class)) {
                continue;
            }
            OnClick annotation = declaredMethod.getAnnotation(OnClick.class);
            int[] values = annotation.value();
            if (values.length > 0) {

                for (int value : values) {
                    View view = viewFinder.findViewById(value);
                    if (view == null) {
                        throw new IllegalArgumentException("not find " + value + " in " + clazz);
                    }
                    view.setOnClickListener(new ProxyOnClickListener(object, declaredMethod));
                }
            }
        }
    }

    /**
     * 注入字段
     */
    private static void injectFields(ViewFinder viewFinder, Object object) {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return;
        }

        for (Field declaredField : declaredFields) {

            if (!declaredField.isAnnotationPresent(ViewById.class)) {
                continue;
            }

            ViewById viewById = declaredField.getAnnotation(ViewById.class);
            int value = viewById.value();
            View view = viewFinder.findViewById(value);
            if (view == null) {
                throw new IllegalArgumentException("not find " + value + " in " + clazz);
            }
            declaredField.setAccessible(true);
            try {
                declaredField.set(object, view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 寻找@ContentView()标注的注解
     */
    private static ContentView findContentView(Class<?> clazz) {
        //如果clazz为空，或者输入过滤的class中的，就返回null
        if (clazz == null || IGNORE_CLASS.contains(clazz)) {
            return null;
        }
        //判断当前的class是不是有contentView标识
        boolean annotationPresent = clazz.isAnnotationPresent(ContentView.class);
        if (annotationPresent) {
            return clazz.getAnnotation(ContentView.class); //有就取出，返回
        }
        findContentView(clazz.getSuperclass());//没有继续查找
        return null;
    }

    /**
     * 给activity设置view
     */
    private static void setContentView(Activity activity, ContentView contentView) {
        int value = contentView.value();
        if (value == -1) {
            throw new IllegalArgumentException("contentView value is -1");
        }

        try {
            Class<?> aClass = activity.getClass();
            Method setContentView = aClass.getMethod("setContentView", int.class);
            setContentView.invoke(activity, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
