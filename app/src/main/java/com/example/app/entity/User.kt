package com.example.app.entity

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 15:20
 */
data class User(
    var username:String?,
    var password:String?,
    var code:String?
){
    constructor() : this(null, null, null)
}
