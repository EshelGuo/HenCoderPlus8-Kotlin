package com.example.lesson.entity

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:32
 */
//Gson解析不支持枚举类型
//https://stackoverflow.com/questions/61675470/gson-expected-a-string-but-was-begin-object-at-line-1-column-3-path-0
/*enum*/ class Lesson(
    val date: String? = null,
    val content: String? = null,
    val state: State? = null){

    constructor() : this(null, null, null) {
    }
    enum class State{
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String
    }
}


