package cn.xwj.skinmodel.skin;

import android.view.View;

import java.util.List;

import cn.xwj.skinmodel.skin.attr.SkinAttr;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinView
 */
public class SkinView {
    private View mView;
    private List<SkinAttr> mSkinAttrList;


    public SkinView(View view, List<SkinAttr> skinAttrList) {
        this.mView = view;
        this.mSkinAttrList = skinAttrList;
    }

    public void skin(SkinResource skinResource) {
        if (mSkinAttrList == null || mSkinAttrList.isEmpty()) {
            return;
        }
        for (SkinAttr attr : mSkinAttrList) {
            attr.skin(skinResource, mView);
        }
    }
}
