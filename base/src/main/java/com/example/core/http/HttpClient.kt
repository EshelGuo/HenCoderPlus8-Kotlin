package com.example.core.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 16:17
 */
object HttpClient : OkHttpClient(){

    var gson:Gson = Gson()

    fun <T> get(path:String, clazz:Class<T>):HttpListener<T>{
        return get(path, type = clazz)
    }

    fun <T> get(path:String, typeToken:TypeToken<T>):HttpListener<T>{
        return get(path, typeToken.type)
    }

    private fun <T> get(path:String, type:Type):HttpListener<T> {
        val request:Request = Request.Builder()
            .url("https://api.hencoder.com/$path")
            .build()
        val call = newCall(request)

        val listener = HttpListener<T>()

        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                when(response.code()){
                    in 200..299 -> dispatchCallback(response, type, listener)
                    in 400..499 -> listener.failed?.invoke("客户端错误")
                    in 500..599 -> listener.failed?.invoke("服务器错误")
                    else -> listener.failed?.invoke("未知错误")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                listener.failed?.invoke("网络异常")
            }

        })

        return listener
    }

    private fun <T> dispatchCallback(
        response: Response,
        type: Type,
        listener: HttpListener<T>
    ) {
        val json: String? = response.body()?.string()
        val result: T? = gson.fromJson(json, type) as T?

        result?.also {
            listener.success?.invoke(it)
        } ?: run {
            listener.failed?.invoke("类型错误")
        }
    }
}