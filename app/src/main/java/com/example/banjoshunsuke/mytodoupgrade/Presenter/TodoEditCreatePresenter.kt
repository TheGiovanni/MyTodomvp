package com.example.banjoshunsuke.mytodoupgrade.Presenter

import android.util.Log
import com.example.banjoshunsuke.mytodoupgrade.Contract.TodoEditCreateContract
import com.example.banjoshunsuke.mytodoupgrade.Model.RealmService
import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by banjousyunsuke on 2018/01/27.
 */
class TodoEditCreatePresenter() : TodoEditCreateContract.UserActions {
    private lateinit var todoEditView: TodoEditCreateContract.View
    private lateinit var todoEntity: TodoEntity

    constructor(todoEditView: TodoEditCreateContract.View) : this() {
        this.todoEditView = todoEditView
    }

    override fun updateClick(todo: String, date: String) {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        var dateParse = sdf.parse(date)
        RealmService().update(todo, dateParse, todoEntity)
    }

    override fun saveClick(todo: String, date: String) {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        var dateParse = Date()
        try {
            dateParse = sdf.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        RealmService().save(todo, dateParse)
    }



    override fun loadTodoEntity(id:Int) {
        todoEntity = RealmService().find(id)
        todoEditView.setData(todoEntity)
//        Log.d("debug".rawvalue)
    }

    override fun deleteClick() {
        RealmService().delete(todoEntity)
    }

}