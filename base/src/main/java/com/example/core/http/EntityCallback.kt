package com.example.core.http

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:13
 */
interface EntityCallback<T> {
    fun onSuccess(entity:T)
    fun onFailure(message:String?)
}