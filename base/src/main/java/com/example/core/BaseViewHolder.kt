package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:24
 */
abstract class BaseViewHolder(itemView: View) : ViewHolder(itemView) {

    private val cache: MutableMap<Int, View> = HashMap()

    @Suppress("UNCHECKED_CAST")
    protected fun <T : View?> getView(@IdRes id: Int): T? {
        var view: View? = cache[id]
        if (view == null) {
            view = itemView.findViewById(id)
            cache[id] = view
        }
        return view as? T
    }

    protected fun setText(@IdRes id: Int, text: String?) {
        (getView<TextView>(id))?.text = text
    }

    protected fun setBackgroundColor(@IdRes id: Int, @ColorInt color: Int) {
        (getView<View>(id))?.setBackgroundColor(color)
    }
}