package com.example.lesson.entity

import androidx.annotation.ColorRes
import com.example.lesson.R

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:32
 */
data class Lesson @JvmOverloads constructor(
    val date: String? = null,
    val content: String? = null,
    val state: State? = null
){
    enum class State(val stateName:String, @ColorRes val colorRes:Int){
        PLAYBACK("有回放", R.color.playback),
        LIVE("正在直播", R.color.live),
        WAIT("等待中", R.color.wait)
    }
}


