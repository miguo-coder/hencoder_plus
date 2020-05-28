package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity: AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {

    val lessonPresenter: LessonPresenter = LessonPresenter(this)

    val lessonAdapter = LessonAdapter()

    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        findViewById<Toolbar>(R.id.toolbar).apply {
            inflateMenu(R.menu.menu_lesson)
            setOnMenuItemClickListener(this@LessonActivity)
        }

        findViewById<RecyclerView>(R.id.list).let {
            it.layoutManager = LinearLayoutManager(this@LessonActivity)
            it.adapter = lessonAdapter
            it.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        }

        refreshLayout = findViewById(R.id.swipe_refresh_layout)
        refreshLayout.setOnRefreshListener { getPresenter().fetchData() }
        refreshLayout.isRefreshing = true

        getPresenter().fetchData()
    }


    override fun getPresenter(): LessonPresenter {
        return lessonPresenter
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        getPresenter().showPlayback()
        return false
    }

    fun showResult(lessons : List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }
}