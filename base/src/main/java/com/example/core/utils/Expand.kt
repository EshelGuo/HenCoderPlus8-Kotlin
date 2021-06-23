@file:JvmName("UtilsKt")
package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.core.BaseApplication
import com.example.core.BaseViewHolder

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 15:51
 */
private val displayMetrics = Resources.getSystem().displayMetrics

fun Float.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

@ColorInt
fun BaseViewHolder.getColor(@ColorRes id: Int): Int{
    return ContextCompat.getColor(itemView.context, id)
}

@JvmOverloads
fun toast(string:String?, duration:Int = Toast.LENGTH_SHORT) {
    string?.let {
        Toast.makeText(BaseApplication.currentApplication, it, duration).show()
    }
}

