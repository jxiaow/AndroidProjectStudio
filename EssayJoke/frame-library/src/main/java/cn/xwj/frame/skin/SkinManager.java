package cn.xwj.frame.skin;

import android.app.Activity;
import android.content.Context;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.xwj.frame.BaseSkinActivity;
import cn.xwj.frame.skin.attr.SkinView;

/**
 * Created by xw on 2018/3/15.
 */

public class SkinManager {
    private static SkinManager sInstance;
    private Context mContext;
    private SkinResource mSkinResource;
    private Map<Activity, List<SkinView>> mSkinViews = new HashMap<>();

    static {
        sInstance = new SkinManager();
    }

    public static SkinManager getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public int loadSkin(String skinPath) {
        //校验签名

        //初始化资源管理
        mSkinResource = new SkinResource(mContext, skinPath);
        //改变皮肤
        Collection<List<SkinView>> values = mSkinViews.values();
        for (List<SkinView> value : values) {
            for (SkinView skinView : value) {
                skinView.skin();
            }
        }
        return 0;
    }

    public SkinResource getSkinResource() {
        return mSkinResource;
    }

    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }


    public void register(Activity activity, List<SkinView> skinViews) {
        mSkinViews.put(activity, skinViews);
    }

    public void unRegister(Activity activity){
        mSkinViews.remove(activity);
    }
}
