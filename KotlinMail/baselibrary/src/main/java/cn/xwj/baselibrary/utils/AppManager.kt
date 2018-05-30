package cn.xwj.baselibrary.utils

import android.app.Activity
import android.os.Process
import java.util.*

/**
 * Author: xw
 * Date: 2018-05-30 09:10:19
 * Description: AppManager: .
 */
class AppManager private constructor() {

    private val appStack: Stack<Activity> = Stack()

    companion object {
        val instances: AppManager by lazy { AppManager() }
    }


    fun addActivity(activity: Activity) {
        appStack.push(activity)
    }


    fun removeActivity(activity: Activity) {
        activity.finish()
        appStack.remove(activity)
    }

    fun finishAllActivity() {
        appStack.forEach {
            it.finish()
        }
        appStack.clear()
    }

    fun currentActivity(): Activity {
        return appStack.lastElement()
    }

    fun exitApp() {
        finishAllActivity()
        android.os.Process.killProcess(Process.myPid())
        System.exit(0)
    }

}