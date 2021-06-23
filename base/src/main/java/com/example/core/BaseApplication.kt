package com.example.core

import android.app.Application
import android.content.Context

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 16:04
 */
class BaseApplication : Application(){

    companion object{
        @JvmStatic
        lateinit var currentApplication:Context private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }
}