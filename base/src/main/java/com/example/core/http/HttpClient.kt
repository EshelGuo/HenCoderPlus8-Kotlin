package com.example.core.http

import com.example.core.utils.nonnull
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 16:17
 */
object HttpClient : OkHttpClient(){

    var gson:Gson = Gson()

    private fun <T> convert(json:String?, type:Type):T?{
        //fromJson返回值可能为空
        try {
            return gson.fromJson(json, type)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }

    fun <T> get(path:String, type:Type, callback:EntityCallback<T>) {
        val request:Request = Request.Builder()
            .url("https://api.hencoder.com/$path")
            .build()

        val call = newCall(request)

        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                when(response.code()){
                    in 200..299 -> dispatchCallback(response, type, callback)
                    in 400..499 -> callback.onFailure("客户端错误")
                    in 500..599 -> callback.onFailure("服务器错误")
                    else -> callback.onFailure("未知错误")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure("网络异常")
            }

        })
    }

    private fun <T> dispatchCallback(
        response: Response,
        type: Type,
        entityCallback: EntityCallback<T>
    ) {
        val json: String? = response.body()?.string()
        val result: T? = convert(json, type) as T?

        nonnull(result) {
            entityCallback.onSuccess(it)
        } or {
            entityCallback.onFailure("类型错误")
        }
    }
}