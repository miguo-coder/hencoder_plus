package com.example.lesson.entity

class Lesson(var date: String, var content: String, var state: State) {
    enum class State {
        PLAYBACK {
            override fun stateName(): String { return "有回放"}
        },
        LIVE {
            override fun stateName(): String { return "有回放"}
        },
        WAIT {
            override fun stateName(): String { return "有回放"}
        };

        abstract fun stateName(): String
    }




}