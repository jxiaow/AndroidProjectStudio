package cn.xwj.skinmodel.skin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import cn.xwj.skinmodel.skin.listener.ISkinChangeListener;
import cn.xwj.skinmodel.skin.util.SPUtils;
import cn.xwj.skinmodel.skin.util.SkinUtil;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinManager
 */
public class SkinManager {

    private static SkinManager sSkinManager;
    private Context mContext;
    private SkinResource mSkinResource;
    private static ArrayMap<ISkinChangeListener, List<SkinView>> skinViewMap = new ArrayMap<>();


    public static SkinManager getInstance() {
        if (sSkinManager == null) {
            synchronized (SkinManager.class) {
                if (sSkinManager == null) {
                    sSkinManager = new SkinManager();
                }
            }
        }
        return sSkinManager;
    }


    public void init(Context context) {
        this.mContext = context.getApplicationContext();

        String skinPath = SPUtils.getSkinPath(mContext);
        File file = new File(skinPath);
        if (!file.exists()) {
            SPUtils.clearSkinInfo(context);
            return;
        }

        String packageName = SkinUtil.getPackageNameByPath(mContext, skinPath);
        if (TextUtils.isEmpty(packageName)) {
            SPUtils.clearSkinInfo(context);
            return;
        }
        mSkinResource = new SkinResource(mContext, skinPath);
    }

    @Nullable
    public List<SkinView> getSkinViews(Activity activity) {
        return skinViewMap.get(activity);
    }

    public void register(ISkinChangeListener listener, List<SkinView> skinViews) {
        skinViewMap.put(listener, skinViews);
    }

    public void unRegister(ISkinChangeListener listener) {
        List<SkinView> skinViews = skinViewMap.get(listener);
        if (skinViews != null) {
            skinViews.clear();
            skinViewMap.remove(listener);
        }
    }


    public void loadSkin(String skinPath) throws Exception {

        File skinFile = new File(skinPath);
        //校验文件是否存在
        if (!skinFile.exists()) {
            throw new FileNotFoundException(skinPath);
        }

        //校验文件是否有效
        // 获取包名
        String packageName = SkinUtil.getPackageNameByPath(mContext, skinPath);
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalStateException("不是有效的皮肤文件");
        }

        String path = SPUtils.getSkinPath(mContext);
        if (skinPath.equals(path)) {
            return;
        }

        mSkinResource = new SkinResource(mContext, skinPath);

        changeSkin();

        SPUtils.saveSkinPath(mContext, skinPath);
    }

    public void resetToDefault() {
        String skinPath = SPUtils.getSkinPath(mContext);
        if (TextUtils.isEmpty(skinPath)) {
            return;
        }

        String packageResourcePath = mContext.getPackageResourcePath();
        mSkinResource = new SkinResource(mContext, packageResourcePath);
        changeSkin();
        SPUtils.clearSkinInfo(mContext);
    }

    private void changeSkin() {
        for (Map.Entry<ISkinChangeListener, List<SkinView>> entry : skinViewMap.entrySet()) {
            List<SkinView> skinViews = entry.getValue();
            for (SkinView skinView : skinViews) {
                skinView.skin(mSkinResource);
            }
            entry.getKey().changeSkin(mSkinResource);
        }
    }

    public void checkChangeSkin(ISkinChangeListener listener, SkinView skinView) {
        String skinPath = SPUtils.getSkinPath(mContext);
        if (!TextUtils.isEmpty(skinPath)) {
            skinView.skin(mSkinResource);
            if (listener != null) {
                listener.changeSkin(mSkinResource);
            }
        }
    }

    public SkinResource getSkinResource() {
        return mSkinResource;
    }
}
