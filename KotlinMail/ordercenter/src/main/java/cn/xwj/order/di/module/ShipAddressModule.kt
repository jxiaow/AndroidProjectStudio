package cn.xwj.order.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.order.data.repository.ShipAddressDataSource
import cn.xwj.order.data.repository.ShipAddressRepository
import cn.xwj.order.presenter.view.EditShipAddressView
import cn.xwj.order.presenter.view.ShipAddressView
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: GoodsModule
 */
@Module
class ShipAddressModule {

    private lateinit var shipAddressView: ShipAddressView
    private lateinit var editShipAddressView: EditShipAddressView


    constructor(shipAddressView: ShipAddressView) {
        this.shipAddressView = shipAddressView
    }

    constructor(editShipAddressView: EditShipAddressView) {
        this.editShipAddressView = editShipAddressView
    }

    @Provides
    @PerComponentScope
    fun provideShipAddressRepository(shipAddressRepository: ShipAddressRepository): ShipAddressDataSource {
        return shipAddressRepository
    }


    @Provides
    @PerComponentScope
    fun provideShipAddressView(): ShipAddressView {
        return shipAddressView
    }

    @Provides
    @PerComponentScope
    fun provideEditShipAddressView(): EditShipAddressView {
        return editShipAddressView
    }


}