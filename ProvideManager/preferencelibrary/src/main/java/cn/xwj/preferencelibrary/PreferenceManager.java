package cn.xwj.preferencelibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Author: xw
 * Date: 2018-03-30 17:20:25
 * Description: PreferenceManager: .
 */

public class PreferenceManager {
    private volatile static PreferenceManager sInstance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private ArrayMap<String, Method> mMethodMap = new ArrayMap<>();

    public void init(Context context, String fileName) {
        mContext = context.getApplicationContext();
        mSharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public void exchangeFile(String name) {
        mSharedPreferences = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 获取数据
     *
     * @param clazz 需要获取数据的class
     * @param <T>   返回数据的类型
     * @return 返回数据
     */
    @Nullable
    public <T> T get(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return null;
        }
        try {
            T newInstance = clazz.newInstance();
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                Object object = get(name, declaredField.getType());
                declaredField.setAccessible(true);
                declaredField.set(newInstance, object);
            }
            return newInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反射get方法
     */
    private Object get(String name, Class<?> type) throws Exception {
        String typeName = validateType(type);
        String methodName = "get" + typeName;
        Method method = mMethodMap.get(methodName);
        if (method == null) {
            method = SharedPreferences.class.getMethod(methodName, getParamsType(typeName));
            mMethodMap.put(methodName, method);
        }
        return method.invoke(mSharedPreferences, name, type.isPrimitive() ? -1 : null);
    }

    /**
     * 将对象数据存入
     */
    public <T> void put(T t) {
        if (t == null) {
            return;
        }

        //获取所有的Field
        Field[] fields = t.getClass().getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return;
        }
        mEditor = mSharedPreferences.edit();
        //遍历
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                //获取value
                Object value = field.get(t);
                if (value == null) {
                    continue;
                }
                put(field.getName(), value, field.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mEditor.apply();
    }

    /**
     * put方法
     */
    private void put(String name, Object value, Class<?> type) throws Exception {
        //根据类型组装put方法
        //1.1 获取可以拼接put方法的类型
        String typeName = validateType(type);
        //1.2 拼接方法
        String methodName = "put" + typeName;
        Method putMethod = mMethodMap.get(methodName);
        if (putMethod == null) {
            //1.3 利用反射获取方法
            putMethod = SharedPreferences.Editor.class.getMethod(methodName,
                    String.class, getParamsType(typeName));
            mMethodMap.put(methodName, putMethod);
        }
        //调用方法，存入数据
        putMethod.invoke(mEditor, name, value);
    }

    /**
     * 通过数据类型的simpleName 获取反射方法的参数类型
     */
    private Class<?> getParamsType(String typeName) throws Exception {
        String classForName = "java.lang." + typeName;
        if ("StringSet".equals(typeName)) {
            classForName = "java.util.Set";
        } else if (!"String".equals(typeName)) {
            Class<?> forNameClass = Class.forName(classForName);
            return (Class<?>) forNameClass.getField("TYPE").get(null);
        }
        return Class.forName(classForName);
    }

    public boolean commit() {
        return mEditor.commit();
    }

    public void apply() {
        mEditor.apply();
    }

    /**
     * 校验类型
     */
    private String validateType(Class<?> type) {
        String typeName = type.getSimpleName();

        if (type.isPrimitive()) {
            typeName = capitalize(typeName);
        }

        if ("Double".equals(typeName) || "Float".equals(typeName)) {
            typeName = "Float";
        } else if ("Int".equals(typeName) || "Integer".equals(typeName)
                || "Short".equals(typeName)) {
            typeName = "Int";
        } else if ("Long".equals(typeName)) {
            typeName = "Long";
        } else if ("StringSet".equals(typeName)) {
            typeName = "StringSet";
        } else {
            typeName = "String";
        }
        return typeName;
    }

    /**
     * 将基本类型的首字母大写
     */
    private String capitalize(String typeName) {
        return typeName.substring(0, 1).toUpperCase(Locale.US)
                + typeName.substring(1, typeName.length());
    }


    public static PreferenceManager getInstance() {
        if (sInstance == null) {
            synchronized (PreferenceManager.class) {
                if (sInstance == null) {
                    sInstance = new PreferenceManager();
                }
            }
        }
        return sInstance;
    }

}
