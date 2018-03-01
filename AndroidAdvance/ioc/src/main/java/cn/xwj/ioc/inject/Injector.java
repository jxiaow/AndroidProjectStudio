package cn.xwj.ioc.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.xwj.ioc.R;
import cn.xwj.ioc.annotation.CheckNet;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;
import cn.xwj.ioc.entity.ViewFinder;

/**
 * Author: xw
 * Date: 2018-03-01 12:24:02
 * Description: Injector <this is description>.
 */

public class Injector {

    public static void inject(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("activity is null");
        }
        inject(new ViewFinder(activity), activity);
    }

    public static void inject(View view, Object object) {
        if (view == null) {
            throw new IllegalArgumentException("view is null");
        }
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }
        inject(new ViewFinder(view), object);
    }

    private static void inject(ViewFinder viewFinder, Object object) {
        injectFields(viewFinder, object);
        injectEvents(viewFinder, object);
    }

    private static void injectEvents(ViewFinder viewFinder, Object object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        if (methods == null || methods.length == 0) {
            return;
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                int[] ids = method.getAnnotation(OnClick.class).value();
                if (ids.length == 0) {
                    return;
                }

                for (int id : ids) {
                    View view = viewFinder.findViewById(id);
                    if (view == null) {
                        throw new IllegalArgumentException(id +
                                "can not find in" + object.getClass().getCanonicalName());
                    }
                    view.setOnClickListener(new DeclaredViewOnClickListener(method, object));
                }
            }
        }
    }

    private static void injectFields(ViewFinder viewFinder, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewById.class)) {
                int id = field.getAnnotation(ViewById.class).value();
                View view = viewFinder.findViewById(id);
                if (view == null) {
                    throw new IllegalArgumentException("can not find " + id + "in " + field.getName());
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
}
