package cn.xwj.ioc.injector;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: xw
 * Date: 2018-03-30 15:49:46
 * Description: ProxyOnClickListener: OnClickListener 代理类.
 */

public class ProxyOnClickListener implements View.OnClickListener {
    private Object mObject;
    private Method mMethod;

    public ProxyOnClickListener(Object object, Method declaredMethod) {
        this.mObject = object;
        this.mMethod = declaredMethod;
    }


    @Override
    public void onClick(View v) {
        mMethod.setAccessible(true);
        Class<?>[] parameterTypes = mMethod.getParameterTypes();
        if (parameterTypes == null || parameterTypes.length <= 0) {
            try {
                mMethod.invoke(mObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            mMethod.invoke(mObject, v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
