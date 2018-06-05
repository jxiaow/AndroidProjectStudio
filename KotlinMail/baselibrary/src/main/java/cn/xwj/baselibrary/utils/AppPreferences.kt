package cn.xwj.baselibrary.utils

import android.content.Context
import android.content.SharedPreferences
import cn.xwj.baselibrary.common.BaseConstants

/**
 * Author: xw
 * Date: 2018-05-31 09:21:05
 * Description: AppPreferences: .
 */

class AppPreferences {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        instance.context = context
        sharedPreferences = context.getSharedPreferences(BaseConstants.SP_TABLE, Context.MODE_PRIVATE)
    }

    companion object {
        val instance: AppPreferences by lazy { AppPreferences() }
    }

    fun putStringSet(key: String, value: MutableSet<String>) {
        val mutableSet = getStringSet(key, mutableSetOf())
        mutableSet.addAll(value)
        sharedPreferences.edit().putStringSet(key, mutableSet).apply()
    }

    fun getStringSet(key: String, default: MutableSet<String>): MutableSet<String> {
        return sharedPreferences.getStringSet(key, default)
    }


    fun <T : Any> put(key: String, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("This type can be saved into Preferences")
            }
            apply()
        }
    }

    fun <T> get(key: String, default: T): T {

        with(sharedPreferences) {
            val res: Any = when (default) {
                is String -> getString(key, default)
                is Boolean -> getBoolean(key, default)
                is Long -> getLong(key, default)
                is Float -> getFloat(key, default)
                else -> throw IllegalArgumentException("This type can be saved into Preferences")
            }
            return res as T
        }
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

}