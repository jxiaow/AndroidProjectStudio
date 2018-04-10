package cn.xwj.navigationbar;

/**
 * Email: i.xiaowujiang@gmail.com
 * Author: xw
 * Date:  2018/4/8
 * Description: INavigationBar.
 */

public interface INavigationBar {
    /**
     * 绑定layoutId
     */
    int bindLayoutId();

    /**
     * 更新View
     */
    void apply();
}
