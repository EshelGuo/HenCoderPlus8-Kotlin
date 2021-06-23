package com.example.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.core.BaseApplication
import com.example.core.R

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 15:59
 */
object CacheUtils {

    private val SP: SharedPreferences = context().getSharedPreferences(context().getString(R.string.app_name), Context.MODE_PRIVATE)

    fun save(key:String, value:String) = SP.edit().putString(key, value).apply()

    fun get(key:String):String = SP.getString(key, "")?:""

    private fun context() = BaseApplication.currentApplication
}