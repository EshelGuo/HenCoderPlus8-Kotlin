@file:JvmName("UtilsKt")
package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 15:51
 */
private val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp:Float):Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
}

fun toast(string:String?) {
    string?.let {
        toast(string, Toast.LENGTH_SHORT)
    }
}

fun toast(string:String?, duration:Int) {
    Toast.makeText(BaseApplication.currentApplication(), string, duration).show();
}