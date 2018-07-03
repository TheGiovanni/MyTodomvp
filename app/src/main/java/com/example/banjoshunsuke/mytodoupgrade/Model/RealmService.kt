package com.example.banjoshunsuke.mytodoupgrade.Model

import android.util.Log
import io.realm.Realm
import java.util.*

/**
 * Created by banjousyunsuke on 2018/01/27.
 */
class RealmService {
    private var realm: Realm = Realm.getDefaultInstance()

    fun find(id: Int): TodoEntity {
        return realm.where(TodoEntity::class.java).equalTo("id", id).findFirst()!!
    }

    fun findAll(): List<TodoEntity> {
        return realm.where(TodoEntity::class.java).findAll()
    }



    fun save(todo: String, deadLine: Date) {
        realm.executeTransaction {
            //auto incrementの機能が無いため+1することで実装している
            val maxId:Number? = realm.where(TodoEntity::class.java).max("id")
            var nextTodoId: Int = 0
            var nextId:Int = 0
            if (maxId != null) nextId = maxId.toInt() + 1
            Log.d("id", nextTodoId.toString())
            val todoEntity = realm.createObject(TodoEntity::class.java, nextId)
            todoEntity.todo = todo
            todoEntity.deadLine = deadLine
        }
    }

    fun update(todo: String, deadLine: Date, todoEntity: TodoEntity) {
        realm.executeTransaction {
            todoEntity.todo = todo
            todoEntity.deadLine = deadLine
        }
    }

    fun delete(todoEntity: TodoEntity) {
        realm.executeTransaction {
            todoEntity.deleteFromRealm()
        }
    }
}

