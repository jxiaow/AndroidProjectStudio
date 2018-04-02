package cn.xwj.androidfix.fix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dalvik.system.BaseDexClassLoader;

/**
 * Author: xw
 * Date: 2018-04-02 12:50:57
 * Description: DexFixManager: .
 */

public class DexFixManager {
    private static final String TAG = "DexFixManager";
    private static final String ROOT_NAME = "odex";
    private File mDexRootDir; //dex存储路径
    private ClassLoader mAppClassLoader; //app的classLoader
    private Field mPathListField; // pathList的字段
    private Object mPathList; // pathList的字段值
    private Field mDexElementsField; //dexElements字段
    private File mOptimizedDirectory;//dex解压的路径


    public void init(Context context) {
        mDexRootDir = context.getDir(ROOT_NAME, Context.MODE_PRIVATE);
        mAppClassLoader = context.getClassLoader();
        mOptimizedDirectory = new File(mDexRootDir + File.separator + "dex");
        if (!mOptimizedDirectory.exists()) {
            mOptimizedDirectory.mkdirs();
        }
    }

    public void loadFixDex() {
        if (mDexRootDir == null) {
            return;
        }

        File[] files = mDexRootDir.listFiles();
        if (files == null || files.length <= 0) {
            return;
        }
        List<File> fileList = Arrays.asList(files);
        try {
            addDexElements(fileList);
        } catch (Exception e) {
            e.printStackTrace();
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
        List<File> fileList = new ArrayList<>();
        fileList.add(dest);
        addDexElements(fileList);
        Log.d(TAG, "完成");
    }

    private void addDexElements(List<File> fileList) throws Exception {
        if (fileList == null) {
            return;
        }
        //通过反射获取appClassLoader中的dexPathList, dexElement
        if (mPathListField == null) {
            mPathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            mPathListField.setAccessible(true);
            mPathList = mPathListField.get(mAppClassLoader);
        }

        if (mDexElementsField == null) {
            mDexElementsField = mPathList.getClass().getDeclaredField("dexElements");
            mDexElementsField.setAccessible(true);
        }

        Object dexElements = mDexElementsField.get(mPathList);
        for (File file : fileList) {
            //创建ClassLoader
            BaseDexClassLoader classLoader = new BaseDexClassLoader(
                    file.getAbsolutePath(),
                    mOptimizedDirectory,
                    null,
                    mAppClassLoader
            );
            //通过classLoader获取fixDex中的dexElements
            Object fixDexPathList = mPathListField.get(classLoader);
            Object fixDexElements = mDexElementsField.get(fixDexPathList);
            //和并dexElements
            Object object = combineDexElements(dexElements, fixDexElements);
            //将合并的重新设置给appClassLoader所在的dexElements中
            mDexElementsField.set(mPathList, object);
        }
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
