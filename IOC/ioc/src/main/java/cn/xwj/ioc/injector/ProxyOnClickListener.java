package cn.xwj.ioc.injector;

import android.view.View;

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
        try {
            mMethod.invoke(mObject, v);
        } catch (Exception e) {
            try {
                mMethod.invoke(mObject);
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new RuntimeException("not find " + mMethod.getName());
            }
            e.printStackTrace();
        }
    }
}
