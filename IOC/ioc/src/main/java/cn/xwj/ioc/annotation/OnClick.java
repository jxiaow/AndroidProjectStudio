package cn.xwj.ioc.annotation;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.xwj.ioc.R;

/**
 * Author: xw
 * Date: 2018-03-30 14:28:39
 * Description: OnClick: view的onClick事件.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
    @IdRes int[] value();
}
