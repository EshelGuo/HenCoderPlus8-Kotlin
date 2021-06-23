package com.example.core.http

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:13
 */
class HttpListener<T> {

    internal var success:((T) -> Any)? = null
    internal var failed:((String) -> Any)? = null

    infix fun onSuccess(block: (T) -> Any):HttpListener<T>{
        success = block
        return this
    }

    infix fun onFailed(block: (String) -> Any):HttpListener<T>{
        failed = block
        return this
    }
}