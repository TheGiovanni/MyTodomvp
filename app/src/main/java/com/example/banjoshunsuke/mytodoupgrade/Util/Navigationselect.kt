package com.example.banjoshunsuke.mytodoupgrade.Util

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.example.banjoshunsuke.mytodoupgrade.R
import com.example.banjoshunsuke.mytodoupgrade.View.MainActivity
import com.example.banjoshunsuke.mytodoupgrade.View.TodoListActivity
import kotlinx.android.synthetic.main.activity_main.*

class Navigationselect  {
    fun onNavigationItemSelected(item: MenuItem,context: Context): Intent? {
        when(item.itemId){
            R.id.menu_calender -> {
                var intent = Intent(context, MainActivity::class.java)
                return intent
            }
            R.id.menu_todo -> {
                var intent = Intent(context, TodoListActivity::class.java)
                return intent
            }

            R.id.menu_grew -> {

            }

            R.id.menu_technology -> {

            }
        }
        return null
    }

}