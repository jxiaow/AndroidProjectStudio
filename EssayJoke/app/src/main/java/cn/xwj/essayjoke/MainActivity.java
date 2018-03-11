package cn.xwj.essayjoke;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.xwj.frame.BaseSkinActivity;
import cn.xwj.ioc.annotation.CheckNet;
import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.OnClick;
import cn.xwj.ioc.annotation.ViewById;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseSkinActivity {

    @ViewById(R.id.iv_icon)
    private ImageView mImageView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void setContentView() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @CheckNet
    @OnClick(R.id.tv)
    private void click() {
        try {
            Configuration configuration1 = getResources().getConfiguration();
            Context configurationContext = createConfigurationContext(configuration1);
            AssetManager assets = configurationContext.getAssets();

            Method addAssetPath = assets.getClass().getMethod("addAssetPath", String.class);
            String filePath = Environment.getExternalStorageDirectory() + File.separator + "resource.skin";
            addAssetPath.invoke(assets, filePath);
            int identifier = configurationContext.getResources().getIdentifier("image_src", "drawable", "cn.xwj.skin");
            Log.e("TAG", "-----> " + (configurationContext.getResources() == getResources()));
            Drawable drawable = configurationContext.getResources().getDrawable(identifier);
            mImageView.setImageDrawable(drawable);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        try {
//            AssetManager assetManager = AssetManager.class.newInstance();
//            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
//            String filePath = Environment.getExternalStorageDirectory() + File.separator + "resource.skin";
//            addAssetPath.invoke(assetManager, filePath);
//            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            Configuration configuration = getResources().getConfiguration();
//            Resources resources = new Resources(assetManager, displayMetrics, configuration);
//            int identifier = resources.getIdentifier("image_src", "drawable", "cn.xwj.skin");
//            Drawable drawable = resources.getDrawable(identifier);
//            mImageView.setImageDrawable(drawable);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

    }
}
