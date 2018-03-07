package cn.xwj.fixbug;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.xwj.util.FileUtils;
import dalvik.system.BaseDexClassLoader;

/**
 * Author: xw
 * Date: 2018-03-07 10:11:15
 * Description: FixDexManager <this is description>.
 */

public class FixDexManager {

    private static final String TAG = "FixDexManager";

    private Context mContext;
    private File mDexDir;


    public FixDexManager(Context context) {
        this.mContext = context;
        this.mDexDir = context.getDir("odex", Context.MODE_PRIVATE);
    }

    public void loadFixDex() throws Exception {
        if (!mDexDir.exists()) {
            return;
        }

        File[] files = mDexDir.listFiles();
        List<File> fixDexFiles = new ArrayList<>();
        for (File file : files) {
            if (!file.isDirectory() && file.getName().endsWith(".dex")) {
                fixDexFiles.add(file);
            }
        }
        fixDexFiles(fixDexFiles);
    }


    public void addFixDex(String fixDexPath) throws Exception {
        //判断fixDexPath文件是否存在
        File fixDexFile = new File(fixDexPath);
        if (!fixDexFile.exists()) {
            throw new FileNotFoundException(fixDexFile.getName());
        }
        //将文件Copy到一个系统可以访问的目录
        File destFile = new File(mDexDir, fixDexFile.getName());
        if(destFile.exists()){
            Log.d(TAG, destFile.getName() + " has already loaded");
            return;
        }
        FileUtils.copyFiles(fixDexFile, destFile);

        List<File> fixDexFiles = new ArrayList<>();
        fixDexFiles.add(fixDexFile);
        fixDexFiles(fixDexFiles);
    }

    private void fixDexFiles(List<File> fixDexFiles) throws Exception {
        ClassLoader appClassLoader = mContext.getClassLoader();
        Object appDexElements = getDexElementsByClassLoader(appClassLoader);

        File optimizedDirectory = new File(mDexDir, "odex");
        if (!optimizedDirectory.exists()) {
            optimizedDirectory.mkdirs();
        }
        for (File fixDexFile : fixDexFiles) {
            ClassLoader classLoader = new BaseDexClassLoader(
                    fixDexFile.getAbsolutePath(),
                    optimizedDirectory,
                    null,
                    appClassLoader
            );
            Object fixDexElements = getDexElementsByClassLoader(classLoader);
            appDexElements = combineArray(fixDexElements, appDexElements);
        }
        injectDexElements(appClassLoader, appDexElements);
    }

    /**
     * 将dexElements注入到classLoader中
     *
     * @param classLoader
     * @param dexElements
     * @throws Exception
     */
    private void injectDexElements(ClassLoader classLoader, Object dexElements) throws Exception {
        Class<?> classLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
        Field pathListField = classLoaderClass.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);
        Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        dexElementsField.set(pathList, dexElements);
    }

    private Object combineArray(Object src, Object dest) {

        Class<?> componentType = src.getClass().getComponentType();
        int i = Array.getLength(src);
        int j = i + Array.getLength(dest);
        Object result = Array.newInstance(componentType, j);
        for (int k = 0; k < j; k++) {
            if (k < i) {
                Array.set(result, k, Array.get(src, k));
            } else {
                Array.set(result, k, Array.get(dest, k - i));
            }
        }

        return result;
    }

    /**
     * 通过ClassLoader得到DexElements
     *
     * @param classLoader
     * @return
     * @throws Exception
     */
    private Object getDexElementsByClassLoader(ClassLoader classLoader) throws Exception {
        Class<?> classLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
        Field pathListField = classLoaderClass.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);
        Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        return dexElementsField.get(pathList);
    }
}
