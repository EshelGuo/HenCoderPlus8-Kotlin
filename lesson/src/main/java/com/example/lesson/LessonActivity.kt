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

/**
 * <br>createBy Gw
 * <br>createTime: 2021/6/21 17:38
 */
class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter> {

    override val presenter by lazy { LessonPresenter(this) }
    private val adapter = LessonAdapter()
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        findViewById<Toolbar>(R.id.toolbar).run {
            inflateMenu(R.menu.menu_lesson)

            setOnMenuItemClickListener {
                presenter.showPlayback()
                return@setOnMenuItemClickListener true
            }
        }

        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = this@LessonActivity.adapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener { presenter.fetchData() }
            isRefreshing = true
        }

        presenter.fetchData()
    }

    fun showResult(lessons: List<Lesson>) {
        adapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

}