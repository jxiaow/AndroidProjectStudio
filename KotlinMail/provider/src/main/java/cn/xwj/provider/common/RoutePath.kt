package cn.xwj.provider.common

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: RoutePath
 */
object RoutePath {

    class UserCenter {

        companion object {
            private const val PATH = "/userCenter"
            const val LOGIN_PATH = "$PATH/login"
            const val USER_INFO_PATH = "$PATH/userInfo"
        }
    }

    class GoodsCenter {
        companion object {
            private const val PATH = "/goodsCenter"
            const val GET_CATEGORY_FRAGMENT = "$PATH/getCategoryFragment"
            const val GET_GOODS_LIST = "$PATH/getGoodsList"
            const val SEARCH_GOODS_BY_KEYWORD = "$PATH/searchGoodsByKeyWord"
        }
    }

    class OrderCenter {
        companion object {
            private const val PATH = "/orderCenter"
            const val PATH_ORDER_CONFIRM = "$PATH/orderConfirm"
        }
    }

    class PaySDK {
        companion object {
            private const val PATH = "/paySDK"
            const val PATH_PAY = "$PATH/pay"
        }

    }class MessageCenter {
        companion object {
            private const val PATH = "/messageCenter"
            const val PATH_MESSAGE_ORDER = "$PATH/path_message_order"
        }

    }
}