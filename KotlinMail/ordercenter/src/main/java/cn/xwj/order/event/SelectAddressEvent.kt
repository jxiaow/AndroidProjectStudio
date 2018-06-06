package cn.xwj.order.event

import cn.xwj.order.data.protocol.ShipAddress

/**
 * Author: xw
 * Date: 2018-06-06 16:57:02
 * Description: SelectAddressEvent: .
 */
/*
    选择收货人信息事件
 */
class SelectAddressEvent(val address: ShipAddress)
