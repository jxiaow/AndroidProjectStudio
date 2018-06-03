package cn.xwj.goods.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.goods.data.repository.CategoryDataSource
import cn.xwj.goods.data.repository.CategoryRepository
import cn.xwj.goods.presenter.view.CategoryView
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryModule
 */
@Module
class CategoryModule(private val categoryView: CategoryView) {

    @Provides
    @PerComponentScope
    fun provideCategoryView(): CategoryView = categoryView

    @Provides
    @PerComponentScope
    fun provideCategoryRepository(categoryRepository: CategoryRepository): CategoryDataSource {
        return categoryRepository
    }
}