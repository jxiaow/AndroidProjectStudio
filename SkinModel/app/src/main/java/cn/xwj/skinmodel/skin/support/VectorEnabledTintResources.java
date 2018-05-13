package cn.xwj.skinmodel.skin.support;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-13 2018/5/13
 * Description: VectorEnabledTintResources
 */
public class VectorEnabledTintResources extends Resources {

    /**
     * Create a new Resources object on top of an existing set of assets in an
     * AssetManager.
     *
     * @param assets  Previously created AssetManager.
     * @param metrics Current display metrics to consider when
     *                selecting/computing resource values.
     * @param config  Desired device configuration to consider when
     * @deprecated Resources should not be constructed by apps.
     * See {@link Context#createConfigurationContext(Configuration)}.
     */
    public VectorEnabledTintResources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        super(assets, metrics, config);
    }

    public static boolean shouldBeUsed() {
        return AppCompatDelegate.isCompatVectorFromResourcesEnabled()
                && Build.VERSION.SDK_INT <= MAX_SDK_WHERE_REQUIRED;
    }

    /**
     * The maximum API level where this class is needed.
     */
    public static final int MAX_SDK_WHERE_REQUIRED = 20;

}
