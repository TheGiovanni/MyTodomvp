package com.example.banjoshunsuke.mytodoupgrade.Contract

import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity

interface TodoEditCreateContract {
    interface View {
        fun setData(todoEntity: TodoEntity)
    }

    interface UserActions {
        fun updateClick(todo: String, date: String)
        fun saveClick(todo: String, date: String)
        fun deleteClick()
        fun loadTodoEntity(id: Int)
    }
}