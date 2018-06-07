package cn.xwj.order.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.order.R
import cn.xwj.order.common.OrderConstant
import cn.xwj.order.data.protocol.ShipAddress
import cn.xwj.order.di.component.DaggerShipAddressComponent
import cn.xwj.order.di.module.ShipAddressModule
import cn.xwj.order.presenter.EditShipAddressPresenter
import cn.xwj.order.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * Author: xw
 * Date: 2018-06-07 12:48:11
 * Description: ShipAddressEditActivity: .
 */
/*
    收货人编辑页面
 */
class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {

    private var mAddress: ShipAddress? = null

    /*
        Dagger注册
     */
    override fun initPerComponent() {
        DaggerShipAddressComponent.builder()
                .activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule(this))
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }

    /*
        初始化视图
     */
    private fun initView() {

        mSaveBtn.setOnClickListener {
            if (mShipNameEt.text.isNullOrEmpty()){
                toast("名称不能为空")
                return@setOnClickListener
            }
            if (mShipMobileEt.text.isNullOrEmpty()){
                toast("电话不能为空")
                return@setOnClickListener
            }
            if (mShipAddressEt.text.isNullOrEmpty()){
                toast("地址不能为空")
                return@setOnClickListener
            }
            if (mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(),
                        mShipAddressEt.text.toString())
            }else{
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    /*
        初始化数据
     */
    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }

    }

    /*
        添加收货人信息回调
     */
    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功")
        finish()
    }

    /*
        修改收货人信息回调
     */
    override fun onEditShipAddressResult(result: Boolean) {
        toast("修改地址成功")
        finish()
    }

}
