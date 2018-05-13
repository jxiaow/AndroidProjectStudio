package cn.xwj.skinmodel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.xwj.skinmodel.skin.SkinManager;
import cn.xwj.skinmodel.skin.SkinResource;
import cn.xwj.skinmodel.skin.SkinView;
import cn.xwj.skinmodel.skin.attr.SkinAttr;
import cn.xwj.skinmodel.skin.listener.ISkinChangeListener;
import cn.xwj.skinmodel.skin.support.SkinAttrSupport;
import cn.xwj.skinmodel.skin.support.SkinViewInflater;


/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-04-20 2018/4/20
 * Description: BaseSkinActivity
 */
public class BaseSkinActivity extends AppCompatActivity implements LayoutInflater.Factory2,
        ISkinChangeListener {
    private static final String TAG = "BaseSkinActivity";
    private SkinViewInflater mSkinViewInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = createView(parent, name, context, attrs);
        Log.e(TAG, "name: " + name);
        if (view == null) {
            return null;
        }

        List<SkinAttr> skinAttrList = SkinAttrSupport.getSkinAttrs(context, attrs);
        if (skinAttrList == null || skinAttrList.isEmpty()) {
            return view;
        }

        SkinView skinView = new SkinView(view, skinAttrList);
        managerView(skinView);
        SkinManager.getInstance().checkChangeSkin(this, skinView);
        return view;

    }

    private void managerView(SkinView skinView) {

        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if (skinViews == null) {
            skinViews = new ArrayList<>();
            SkinManager.getInstance().register(this, skinViews);
        }
        skinViews.add(skinView);
    }

    private View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (mSkinViewInflater == null) {
            mSkinViewInflater = new SkinViewInflater(this.getWindow());
        }
        return mSkinViewInflater.createView(parent, name, context, attrs);
    }

    @Override
    public void changeSkin(SkinResource skinResource) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unRegister(this);
        mSkinViewInflater.clear();
    }
}
