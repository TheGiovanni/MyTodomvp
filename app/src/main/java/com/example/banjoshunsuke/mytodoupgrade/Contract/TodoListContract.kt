package com.example.banjoshunsuke.mytodoupgrade.Contract

import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity

interface TodoListContract {
    interface View {
        fun setListData(todoListData:List<TodoEntity>)
    }

    interface UserActions{
        fun loadListEntity()
    }
}