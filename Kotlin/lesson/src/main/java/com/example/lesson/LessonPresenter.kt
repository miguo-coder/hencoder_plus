package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken

class LessonPresenter(var activity: LessonActivity) {
    companion object {
        const val LESSON_PATH = "lessons"
    }

    var lessons: List<Lesson> = arrayListOf()

    var type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.INSTANCE.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message ?: "") }
            }

        })
    }

    fun showPlayback() {
        val playbackLessons: MutableList<Lesson> = mutableListOf()
        lessons.forEach {
            if (it.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(it)
            }
        }
        activity.showResult(playbackLessons)
    }
}