package cn.xwj.androidfix.fix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;

/**
 * Author: xw
 * Date: 2018-04-02 12:50:57
 * Description: DexFixManager: .
 */

public class DexFixManager {
    private static final String TAG = "DexFixManager";
    private static final String ROOT_NAME = "odex";
    private File mDexRootDir;
    private Context mContext;
    private ClassLoader mAppClassLoader; //app的classLoader

    public void init(Context context) {
        mContext = context;
        mDexRootDir = mContext.getDir(ROOT_NAME, Context.MODE_PRIVATE);
        mAppClassLoader = mContext.getClassLoader();

    }

    public void loadFixDex() {
        if (mDexRootDir == null) {
            return;
        }

        File[] files = mDexRootDir.listFiles();
        if (files == null || files.length <= 0) {
            return;
        }

        for (File file : files) {

        }
    }

    public void addFixDex(@NonNull String dexPath) throws Exception {
        //判断路径是否为空
        if (TextUtils.isEmpty(dexPath)) {
            throw new IllegalArgumentException("dexPath is null");
        }
        //判断文件是否存在
        File src = new File(dexPath);
        if (!src.exists()) {
            throw new FileNotFoundException(dexPath);
        }

        File dest = new File(mDexRootDir, src.getName());
        //判断文件是否已经被加载
        if (dest.exists()) {
            Log.d(TAG, "dex [" + dexPath + "] has already loaded");
            return;
        }
        //将文件拷贝到指定的目录
        FileUtils.copyFile(src, dest);
        //通过反射获取appClassLoader中的dexPathList, dexElement
        ClassLoader appClassLoader = mContext.getClassLoader();
        Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(appClassLoader);
        Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElements = dexElementsField.get(pathList);

        //创建ClassLoader
        File optimizedDirectory = new File(mDexRootDir + File.separator + "dex");
        if (!optimizedDirectory.exists()) {
            optimizedDirectory.mkdirs();
        }
        BaseDexClassLoader classLoader = new BaseDexClassLoader(
                dest.getAbsolutePath(),
                optimizedDirectory,
                null,
                appClassLoader
        );
        //通过classLoader获取fixDex中的dexElements
        Object fixDexPathList = pathListField.get(classLoader);
        Object fixDexElements = dexElementsField.get(fixDexPathList);
        //和并dexElements
        Object object = combineDexElements(dexElements, fixDexElements);
        //将合并的重新设置给appClassLoader所在的dexElements中
        dexElementsField.set(pathList, object);
        Log.d(TAG, "完成");
    }

    private Object combineDexElements(Object dexElements, Object fixDexElements) {
        int j = Array.getLength(fixDexElements);
        int length = Array.getLength(dexElements) + j;
        Object newInstance = Array.newInstance(dexElements.getClass().getComponentType(), length);
        for (int i = 0; i < length; i++) {

            if (i < j) {
                Array.set(newInstance, i, Array.get(fixDexElements, i));
            } else {
                Array.set(newInstance, i, Array.get(dexElements, i - j));
            }
        }
        return newInstance;
    }

}
