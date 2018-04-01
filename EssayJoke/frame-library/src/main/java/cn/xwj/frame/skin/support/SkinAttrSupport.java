package cn.xwj.frame.skin.support;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import cn.xwj.frame.skin.attr.SkinAttr;
import cn.xwj.frame.skin.attr.SkinType;

/**
 * Created by xw on 2018/3/15.
 */

public class SkinAttrSupport {
    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {

        //背景，src,textColor
        List<SkinAttr> skinAttrs = new ArrayList<>();
        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            SkinType skinType = getSkinType(attributeName);
            if (skinType != null) {
                String resName = getResName(context, attributeValue);
                if (TextUtils.isEmpty(resName)) {
                    continue;
                }
                SkinAttr skinAttr = new SkinAttr(resName, skinType);
                skinAttrs.add(skinAttr);
            }
        }

        return skinAttrs;
    }

    private static String getResName(Context context, String attributeValue) {
        if (TextUtils.isEmpty(attributeValue) || !attributeValue.startsWith("@")) {
            return null;
        }

        attributeValue = attributeValue.substring(1);
        int resId = Integer.parseInt(attributeValue);
        return context.getResources().getResourceEntryName(resId);
    }

    private static SkinType getSkinType(String attributeName) {
        try {
            SkinType[] types = SkinType.values();
            for (SkinType type : types) {

                if(type.getResName().equals(attributeName)){
                    return type;
                }
            }
            return SkinType.valueOf(attributeName);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
}
