package cn.xwj.order.data.protocol

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Author: xw
 * Date: 2018-06-06 15:48:44
 * Description: ShipAddress: .
 */
/*
   收货地址
 */
@Parcelize
data class ShipAddress(
        val id: Int,
        var shipUserName: String,
        var shipUserMobile: String,
        var shipAddress: String,
        var shipIsDefault: Int
) : Parcelable
