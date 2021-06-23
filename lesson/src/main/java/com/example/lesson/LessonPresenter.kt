package com.example.lesson

import com.example.core.http.HttpClient
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken


/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 18:01
 */
class LessonPresenter(
    private val activity: LessonActivity
) {

    companion object{
        const val LESSON_PATH = "lessons"
    }

    private var lessons: List<Lesson> = ArrayList()
    private val typeToken: TypeToken<List<Lesson>> = object : TypeToken<List<Lesson>>() {}

    fun fetchData() {
        HttpClient.get(LESSON_PATH, typeToken) onSuccess {
            this@LessonPresenter.lessons = it
            activity.runOnUiThread {
                activity.showResult(it)
            }
        } onFailed {
            activity.run { toast(it) }
        }
    }

    fun showPlayback() {
        activity.showResult(lessons.filter { it.state === Lesson.State.PLAYBACK })
    }
}