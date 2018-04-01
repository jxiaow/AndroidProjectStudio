package cn.xwj.frame.skin.attr;

import android.view.View;

/**
 * Created by xw on 2018/3/15.
 */

public class SkinAttr {
    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(String resName, SkinType skinType) {
        this.mResName = resName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view, mResName);
    }
}
