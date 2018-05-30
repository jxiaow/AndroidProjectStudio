package cn.xwj.usercenter.data.respository

import io.reactivex.Observable

/**
 * Author: xw
 * Date: 2018-05-30 16:15:34
 * Description: UploadDataSource: .
 */
interface UploadDataSource {
    /*
      获取七牛云上传凭证
   */
    fun getUploadToken(): Observable<String>
}