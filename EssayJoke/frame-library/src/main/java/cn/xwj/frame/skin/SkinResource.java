package cn.xwj.frame.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.lang.reflect.Method;

/**
 * Created by xw on 2018/3/15.
 */

public class SkinResource {
    private Resources mSkinResource;
    private Context mContext;
    private String mPackageName;

    public SkinResource(Context context, String skinPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinPath);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            Configuration configuration = context.getResources().getConfiguration();
            mSkinResource = new Resources(assetManager, displayMetrics, configuration);
            this.mContext = context;
            mPackageName = mContext.getPackageManager().getPackageArchiveInfo(skinPath,
                    PackageManager.GET_ACTIVITIES).packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Drawable getDrawableByName(String resName) {
        try {

            int resId = mSkinResource.getIdentifier(resName, "drawable", mPackageName);
            return mSkinResource.getDrawable(resId);
        } catch (Exception e) {

        }
        return null;

    }

    public ColorStateList getColorByName(String resName) {
      try {
          int resId = mSkinResource.getIdentifier(resName, "color", mPackageName);
          return mSkinResource.getColorStateList(resId);
      }catch (Exception e){}

      return null;
    }


}
