package cn.xwj.ioc;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;

import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;

/**
 * Author: xw
 * Date: 2018-03-05 10:47:49
 * Description: Injector <this is description>.
 */

public class Injector {
    private static final HashSet<Class<?>> IGNORES = new HashSet<>();

    static {
        IGNORES.add(Activity.class);
        IGNORES.add(AppCompatActivity.class);
        IGNORES.add(Object.class);
        IGNORES.add(Fragment.class);
        IGNORES.add(FragmentActivity.class);
    }

    public static void inject(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("activity is null");
        }

        inject(new ViewFinder(activity), activity);
    }

    public static void inject(View view, Object object) {
        if (view == null || object == null) {
            throw new IllegalArgumentException("view or object is null");
        }
        inject(new ViewFinder(view), object);
    }


    private static void inject(ViewFinder viewFinder, Object object) {
        injectContentView(viewFinder, object);
        injectFields(viewFinder, object);
        injectEvents(viewFinder, object);
    }

    private static void injectContentView(ViewFinder viewFinder, Object object) {
        Class<?> clazz = object.getClass();
        ContentView contentView = findContentView(clazz);
        if (contentView != null) {
            int id = contentView.value();
            if (id > 0) {
                try {
                    Method setContentView = clazz.getMethod("setContentView", int.class);
                    setContentView.invoke(object, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static ContentView findContentView(Class<?> clazz) {
        if (clazz == null || IGNORES.contains(clazz)) {
            return null;
        }
        if (!clazz.isAnnotationPresent(ContentView.class)) {
            findContentView(clazz.getSuperclass());
        } else {
            return clazz.getAnnotation(ContentView.class);
        }
        return null;
    }


    private static void injectFields(ViewFinder viewFinder, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewById.class)) {
                int value = field.getAnnotation(ViewById.class).value();
                View view = viewFinder.findViewById(value);
                if (view == null) {
                    throw new IllegalArgumentException("can not find " + value + " with " + field.getName());
                }
                field.setAccessible(true);
                try {
                    field.set(object, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectEvents(ViewFinder viewFinder, Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        if (methods == null || methods.length == 0) {
            return;
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                int[] value = method.getAnnotation(OnClick.class).value();
                if (value == null || value.length == 0) {
                    continue;
                }
                for (int id : value) {
                    View view = viewFinder.findViewById(id);
                    if (view == null) {
                        throw new IllegalArgumentException("can not find " + id + " with " + method.getName());
                    }
                    view.setOnClickListener(new ProxyOnClickListener(method, object));
                }
            }
        }
    }
}
