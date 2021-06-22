package com.example.core.utils

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 16:53
 */

inline fun<T> Any.nonnull(any:T?, block: (T) -> Any): Strong.NonNull<T>{
    any?.let {
        block.invoke(it)
    }
    return Strong.NonNull(any)
}

inline fun<T> Any.nullable(any:T?, block: () -> Any): Strong.Nullable<T>{
    if(any == null) block.invoke()
    return Strong.Nullable(any)
}

fun<T> Any.nonnull(any:T?, def:T): T{
    if(any == null) {
        return def
    }

    return any
}

class Strong {

    class NonNull<T>(val value: T?) {
        inline infix fun or(block: () -> Any){
            if(value == null){
                block.invoke()
            }
        }
    }

    class Nullable<T>(val value: T?) {
        inline infix fun or(block: (T) -> Any){
            value?.let { block.invoke(it) }
        }
    }
}