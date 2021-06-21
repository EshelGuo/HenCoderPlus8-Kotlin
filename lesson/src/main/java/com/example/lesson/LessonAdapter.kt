package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.core.utils.nonnull
import com.example.lesson.entity.Lesson
import java.util.ArrayList

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:43
 */
class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var list: List<Lesson> = ArrayList()

    fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class LessonViewHolder(view: View) : BaseViewHolder(view) {
        companion object {
            @JvmStatic
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false)
                )
            }
        }

        fun onBind(lesson: Lesson) {
            val date: String = nonnull(lesson.date, "日期待定")

            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.content)

            lesson.state?.let {
                setText(R.id.tv_state, it.stateName())
                val colorRes =
                    when (it) {
                        Lesson.State.PLAYBACK -> R.color.playback
                        Lesson.State.LIVE -> R.color.live
                        Lesson.State.WAIT -> R.color.wait
                    }

                val backgroundColor = itemView.context.getColor(colorRes)

                val target = getView(R.id.tv_state) as TextView?
                target?.setBackgroundColor(backgroundColor)
            }

        }

    }
}