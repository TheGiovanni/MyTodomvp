package com.example.banjoshunsuke.mytodoupgrade.View

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.example.banjoshunsuke.mytodoupgrade.Contract.TodoListContract
import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity
import com.example.banjoshunsuke.mytodoupgrade.Presenter.TodoListPresenter
import com.example.banjoshunsuke.mytodoupgrade.R
import com.example.banjoshunsuke.mytodoupgrade.Util.Navigationselect
import kotlinx.android.synthetic.main.activity_todolist.*

class TodoListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,TodoRecyclerAdapter.OnItemClickListener,TodoListContract.View  {
    private val todoListPresenter:TodoListContract.UserActions = TodoListPresenter(this as TodoListContract.View)
    private lateinit var mAdapter: TodoRecyclerAdapter
    private lateinit var mRecyclerView:RecyclerView

    private lateinit var mLayoutManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist)
        toolbar.title = "やること"
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.drawer_open,R.string.drawer_close)
//        val toggle = ActionBarDrawerToggle(this,drawer_layout,R.string.drawer_open,R.string.drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)
        recyclerView.setHasFixedSize(true)
        fab.setOnClickListener {
            startActivity(TodoCreateActivity().createIntent(application))

        }
        mRecyclerView = findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(this.application)
        mRecyclerView.layoutManager = mLayoutManager
        todoListPresenter.loadListEntity()


    }
    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        startActivity(Navigationselect().onNavigationItemSelected(item,applicationContext))
        drawer_layout.closeDrawer(GravityCompat.START)


        return true
    }
    override fun onItemClick(todoId: Int) {
        startActivity(TodoEditActivity().createIntent(application,todoId))
    }

    override fun setListData(todoListData: List<TodoEntity>) {
        mAdapter = TodoRecyclerAdapter(this.application, this,todoListData)
        mRecyclerView.adapter = mAdapter
    }
}