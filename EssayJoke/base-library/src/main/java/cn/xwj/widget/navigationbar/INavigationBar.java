package cn.xwj.widget.navigationbar;

/**
 * Author: xw
 * Date: 2018-03-09 09:20:56
 * Description: INavigationBar <this is description>.
 */

public interface INavigationBar {

    /**
     * 绑定LayoutId
     * @return
     */
   int bindLayoutId();

    /**
     * 绑定各种控件
     */
   void applyView();
}
