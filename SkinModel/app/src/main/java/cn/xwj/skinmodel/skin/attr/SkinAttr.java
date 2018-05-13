package cn.xwj.skinmodel.skin.attr;

import android.view.View;

import cn.xwj.skinmodel.skin.SkinResource;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinAttr
 */
public class SkinAttr {
    private String resName;
    private SkinType mSkinType;

    public SkinAttr(String resName, SkinType skinType) {
        this.resName = resName;
        this.mSkinType = skinType;
    }


    public void skin(SkinResource skinResource, View view) {
        mSkinType.skin(skinResource, view, resName);
    }
}
