package cn.xwj.ioc.inject;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

import cn.xwj.ioc.annotation.CheckNet;
import cn.xwj.ioc.utils.InjectUtils;

/**
 * Author: xw
 * Date: 2018-03-01 12:40:37
 * Description: DeclaredViewOnClickListener <this is description>.
 */

public class DeclaredViewOnClickListener implements View.OnClickListener {
    private Method mMethod;
    private Object mObject;


    public DeclaredViewOnClickListener(Method method, Object object) {
        this.mMethod = method;
        this.mObject = object;
    }

    @Override
    public void onClick(View v) {
        if(mMethod.isAnnotationPresent(CheckNet.class)
                && !InjectUtils.isAvailableNetWork(v.getContext())){
            String methodName = mMethod.getAnnotation(CheckNet.class).methodName();
            if(TextUtils.isEmpty(methodName)){
                Toast.makeText(v.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Method method = mObject.getClass().getDeclaredMethod(methodName);
                if(method != null){
                    method.setAccessible(true);
                    method.invoke(mObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        mMethod.setAccessible(true);
        try {
            mMethod.invoke(mObject, v);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                mMethod.invoke(mObject);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
