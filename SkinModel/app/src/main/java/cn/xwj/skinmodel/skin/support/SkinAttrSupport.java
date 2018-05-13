package cn.xwj.skinmodel.skin.support;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.xwj.skinmodel.skin.attr.SkinAttr;
import cn.xwj.skinmodel.skin.attr.SkinType;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: SkinAttrSupport
 */
public class SkinAttrSupport {

    private static final String TAG = "skinAttrSupport";


    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {
        int attributeCount = attrs.getAttributeCount();
        List<SkinAttr> skinAttrs = new ArrayList<>();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);

            Log.d(TAG, "attribute name: " + attributeName
                    + "  value: " + attributeValue);

            SkinType skinType = SkinType.getSkinType(attributeName);
            if (skinType == null) {
                continue;
            }
            String resName = getResName(context, attributeValue);
            if (!TextUtils.isEmpty(resName)) {
                SkinAttr skinAttr = new SkinAttr(resName, skinType);
                skinAttrs.add(skinAttr);
            }
        }
        return skinAttrs;
    }

    private static String getResName(Context context, String attributeValue) {
        if (attributeValue.startsWith("@")) {
            attributeValue = attributeValue.substring(1);

            try {
                int resId = Integer.parseInt(attributeValue);
                return context.getResources().getResourceEntryName(resId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
