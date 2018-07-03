package com.example.banjoshunsuke.mytodoupgrade.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class TodoEntity: RealmObject() {
    @PrimaryKey var id:Int = 0
    open var todo:String = ""
    open var deadLine:Date = Date()
}
