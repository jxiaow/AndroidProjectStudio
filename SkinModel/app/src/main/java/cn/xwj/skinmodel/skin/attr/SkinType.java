package cn.xwj.skinmodel.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xwj.skinmodel.skin.SkinResource;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinType
 */
public class SkinType {

    public static final String BACKGROUND_TYPE = "background";
    public static final String SRC_TYPE = "src";
    public static final String TEXT_COLOR_TYPE = "textColor";

    private String mSkinType;

    private String resName;

    SkinType(String resName, String skinType) {
        this.resName = resName;
        this.mSkinType = skinType;
    }

    public void setSkinType(String skinType) {
        mSkinType = skinType;
    }

    public String getResName() {
        return resName;
    }

    public String getSkinType() {
        return mSkinType;
    }

    @Nullable
    public static SkinType getSkinType(String attributeName) {
        if (TextUtils.isEmpty(attributeName)) {
            return null;
        }
        switch (attributeName) {
            case BACKGROUND_TYPE:
                return new SkinType(BACKGROUND_TYPE, BACKGROUND_TYPE);
            case SRC_TYPE:
                return new SkinType(SRC_TYPE, SRC_TYPE);
            case TEXT_COLOR_TYPE:
                return new SkinType(TEXT_COLOR_TYPE, TEXT_COLOR_TYPE);
        }

        return null;
    }

    public void skin(SkinResource skinResource, View view, String resName) {
        switch (mSkinType) {
            case BACKGROUND_TYPE:
                Drawable drawable = skinResource.getDrawable(resName);
                if (drawable != null) {
                    view.setBackgroundDrawable(drawable);
                    return;
                }

                ColorStateList colorStateList = skinResource.getColorStateList(resName);
                if (colorStateList != null) {
                    view.setBackgroundColor(colorStateList.getDefaultColor());
                }
                break;
            case SRC_TYPE:
                Drawable srcDrawable = skinResource.getDrawable(resName);
                if (srcDrawable != null) {
                    ((ImageView) view).setImageDrawable(srcDrawable);
                }

                break;
            case TEXT_COLOR_TYPE:
                ColorStateList textColorList = skinResource.getColorStateList(resName);
                if (textColorList != null) {
                    ((TextView) view).setTextColor(textColorList.getDefaultColor());
                }
                break;
        }
    }
}
