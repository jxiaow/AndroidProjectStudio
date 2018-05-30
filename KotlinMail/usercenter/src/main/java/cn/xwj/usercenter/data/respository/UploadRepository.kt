package cn.xwj.usercenter.data.respository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.usercenter.data.api.UploadApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: UserRepository
 */
class UploadRepository @Inject constructor() : UploadDataSource {
    override fun getUploadToken(): Observable<String> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
                .convert()

    }


}