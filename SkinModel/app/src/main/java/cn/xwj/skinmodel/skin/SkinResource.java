package cn.xwj.skinmodel.skin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import java.lang.reflect.Method;

import cn.xwj.skinmodel.skin.util.SkinUtil;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinResource
 */
public class SkinResource {

    private String mSkinPath;
    private Context mContext;
    private Resources mResources;

    public SkinResource(Context context, String skinPath) {
        this.mContext = context;
        this.mSkinPath = skinPath;
        this.mResources = createResource();
    }

    private Resources createResource() {

        Resources contextResources = mContext.getResources();

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            @SuppressLint("PrivateApi")
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.setAccessible(true);
            method.invoke(assetManager, mSkinPath);

            DisplayMetrics displayMetrics = contextResources.getDisplayMetrics();
            Configuration configuration = contextResources.getConfiguration();
            return new Resources(assetManager, displayMetrics, configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public Resources getResources() {
        return mResources;
    }

    public ColorStateList getColorStateList(String resName) {
        try {
            String packageName = SkinUtil.getPackageNameByPath(mContext, mSkinPath);
            if (packageName != null && mResources != null) {

                int color = mResources.getIdentifier(resName, "color", packageName);
                return mResources.getColorStateList(color);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public Drawable getDrawable(String resName) {

        try {
            String packageName = SkinUtil.getPackageNameByPath(mContext, mSkinPath);
            if (packageName != null && mResources != null) {
                int drawable = mResources.getIdentifier(resName, "drawable", packageName);
                return mResources.getDrawable(drawable);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
