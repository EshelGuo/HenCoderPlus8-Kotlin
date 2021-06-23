package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.core.utils.getColor
import com.example.lesson.entity.Lesson
import java.util.ArrayList

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:43
 */
internal class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var dataSource: List<Lesson> = ArrayList()

    fun updateAndNotify(data: List<Lesson>) {
        this.dataSource = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder {
        return LessonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(dataSource[position])
    }

    class LessonViewHolder(view: View) : BaseViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): LessonViewHolder {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_lesson, parent, false)
                return LessonViewHolder(view)
            }
        }

        fun onBind(lesson: Lesson) {
            val(date, content, state) = lesson

            setText(R.id.tv_date, date?: "日期待定")
            setText(R.id.tv_content, content)

            state?.apply {
                setText(R.id.tv_state, stateName)
                setBackgroundColor(R.id.tv_state, getColor(colorRes))
            }
        }

    }
}