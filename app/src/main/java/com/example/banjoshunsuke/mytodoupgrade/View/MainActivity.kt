package com.example.banjoshunsuke.mytodoupgrade.View

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import com.example.banjoshunsuke.mytodoupgrade.R
import com.example.banjoshunsuke.mytodoupgrade.R.id.drawer_layout
import com.example.banjoshunsuke.mytodoupgrade.Util.Navigationselect
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var titleText:TextView
    private lateinit var prevButton:Button
    private lateinit var nextButton:Button

    private lateinit var mCalendarAdapter:CalendarAdapter
    private lateinit var calendarGridView:GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caleder)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.drawer_open,R.string.drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)


            titleText = findViewById(R.id.titleText);
            prevButton = findViewById(R.id.prevButton);
            prevButton.setOnClickListener {

                mCalendarAdapter.prevMonth();
                titleText.text = mCalendarAdapter.title;
            }
            nextButton = findViewById(R.id.nextButton);
            nextButton.setOnClickListener{
                    mCalendarAdapter.nextMonth();
                titleText.text = mCalendarAdapter.title;
                }

            calendarGridView = findViewById(R.id.calendarGridView)
            mCalendarAdapter = CalendarAdapter(this);
            calendarGridView.setAdapter(mCalendarAdapter);
            titleText.text = mCalendarAdapter.title


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
}

