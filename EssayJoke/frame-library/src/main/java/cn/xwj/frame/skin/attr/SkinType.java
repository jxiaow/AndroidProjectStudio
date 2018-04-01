package cn.xwj.frame.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xwj.frame.skin.SkinManager;

/**
 * Created by xw on 2018/3/15.
 */

public enum SkinType {

    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String resName) {
            ColorStateList color = SkinManager.getInstance().getSkinResource().getColorByName(resName);
            if (color != null) {
                ((TextView) view).setTextColor(color);
            }
        }
    },
    BACKGROUND("background") {
        @Override
        public void skin(View view, String resName) {
            Drawable drawable = SkinManager.getInstance().getSkinResource().getDrawableByName(resName);
            if (drawable != null) {
                view.setBackgroundDrawable(drawable);
            }
            ColorStateList color = SkinManager.getInstance().getSkinResource().getColorByName(resName);
            if(color != null){
                view.setBackgroundColor(color.getDefaultColor());
            }
        }
    },
    SRC("src") {
        @Override
        public void skin(View view, String resName) {
            Drawable drawable = SkinManager.getInstance().getSkinResource().getDrawableByName(resName);
            if (drawable != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawable);
            }
        }
    };

    private String mResName;

    SkinType(String resName) {
        this.mResName = resName;
    }

    public String getResName() {
        return mResName;
    }

    public abstract void skin(View view, String resName);
}
