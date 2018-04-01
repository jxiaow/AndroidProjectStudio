package cn.xwj.frame.skin.attr;

import android.view.View;

import java.util.List;

/**
 * Created by xw on 2018/3/15.
 */

public class SkinView {
    private View mView;
    private List<SkinAttr> mSkinAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mSkinAttrs = skinAttrs;
    }


    public void skin() {
        for (SkinAttr skinAttr : mSkinAttrs) {
            skinAttr.skin(mView);
        }
    }
}
