package cn.xwj.order.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.order.data.protocol.ShipAddress
import cn.xwj.order.data.repository.ShipAddressDataSource
import cn.xwj.order.presenter.view.ShipAddressView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-07 11:33:04
 * Description: ShipAddressPresenter: .
 */
/*
    收货人列表Presenter
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressRepository: ShipAddressDataSource

    /*
        获取收货人列表
     */
    fun getShipAddressList() {
        mView.showLoading()
        shipAddressRepository.getShipAddressList().execute(mView, lifecycleOwner) {
            mView.onGetShipAddressResult(it)
        }
    }

    /*
        设置默认收货人信息
     */
    fun setDefaultShipAddress(address: ShipAddress) {

        mView.showLoading()
        shipAddressRepository.editShipAddress(address).execute(mView, lifecycleOwner) {
            mView.onSetDefaultResult(it!!)
        }
    }

    /*
        删除收货人信息
     */
    fun deleteShipAddress(id: Int) {

        mView.showLoading()
        shipAddressRepository.deleteShipAddress(id).execute(mView, lifecycleOwner) {
            mView.onDeleteResult(it!!)
        }
    }

}
