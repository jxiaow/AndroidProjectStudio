package cn.xwj.goods.data.protocol

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: Category
 */

data class Category(val id: Int,
                    val categoryName: String,
                    val categoryIcon: String = "",
                    val parentId: Int,
                    var isSelected: Boolean = false)