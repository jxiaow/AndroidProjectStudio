package cn.xwj.ioc;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

import cn.xwj.ioc.annotation.CheckNet;

/**
 * Author: xw
 * Date: 2018-03-05 12:32:23
 * Description: ProxyOnClickListener <this is description>.
 */

public class ProxyOnClickListener implements View.OnClickListener {
    private Method mMethod;
    private Object mObject;

    public ProxyOnClickListener(Method method, Object object) {
        this.mMethod = method;
        this.mObject = object;
    }

    @Override
    public void onClick(View v) {

        //校验该方法
        if (mMethod.isAnnotationPresent(CheckNet.class)
                && !InjectUtils.isNetWorkAvailable(v.getContext())) {
            //处理
            String methodName = mMethod.getAnnotation(CheckNet.class).methodName();
            if (TextUtils.isEmpty(methodName)) {
                Toast.makeText(v.getContext(), "网络不可用", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Method declaredMethod = mObject.getClass().getDeclaredMethod(methodName);
                if (declaredMethod != null) {
                    declaredMethod.invoke(declaredMethod);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }

        mMethod.setAccessible(true);
        try {
            mMethod.invoke(mObject, v);
        } catch (Exception e) {
            try {
                mMethod.invoke(mObject);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }
}
