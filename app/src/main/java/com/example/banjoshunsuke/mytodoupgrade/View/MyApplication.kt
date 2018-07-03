package com.example.banjoshunsuke.mytodoupgrade.View

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}