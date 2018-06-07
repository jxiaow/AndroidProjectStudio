package cn.xwj.order.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.order.data.protocol.ShipAddress
import cn.xwj.order.data.repository.ShipAddressDataSource
import cn.xwj.order.presenter.view.EditShipAddressView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-07 12:50:51
 * Description: EditShipAddressPresenter: .
 */
/*
    编辑收货人信息Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipaddressRepository: ShipAddressDataSource


    /*
        添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        mView.showLoading()
        shipaddressRepository.addShipAddress(shipUserName, shipUserMobile, shipAddress)
                .execute(mView, lifecycleOwner) {
                    mView.onAddShipAddressResult(it!!)
                }
    }

    /*
        修改收货人信息
     */
    fun editShipAddress(address: ShipAddress) {
        mView.showLoading()
        shipaddressRepository.editShipAddress(address)
                .execute(mView, lifecycleOwner) {
                    mView.onEditShipAddressResult(it!!)
                }
    }


}
