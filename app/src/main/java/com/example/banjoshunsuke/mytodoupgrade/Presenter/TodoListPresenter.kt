package com.example.banjoshunsuke.mytodoupgrade.Presenter

import com.example.banjoshunsuke.mytodoupgrade.Contract.TodoListContract
import com.example.banjoshunsuke.mytodoupgrade.Model.RealmService
import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity

class TodoListPresenter(private val mainContract: TodoListContract.View) : TodoListContract.UserActions{
    private lateinit var todoListEntity: List<TodoEntity>

    override fun loadListEntity(){
        todoListEntity = RealmService().findAll()
        mainContract.setListData(todoListEntity)
    }
}