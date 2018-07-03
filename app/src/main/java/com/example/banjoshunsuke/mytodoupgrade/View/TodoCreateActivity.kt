package com.example.banjoshunsuke.mytodoupgrade.View

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.banjoshunsuke.mytodoupgrade.Contract.TodoEditCreateContract
import com.example.banjoshunsuke.mytodoupgrade.Presenter.TodoEditCreatePresenter
import com.example.banjoshunsuke.mytodoupgrade.R

import kotlinx.android.synthetic.main.activity_todoedit.*
import java.util.*

/**
 * Created by banjousyunsuke on 2018/01/27.
 */
class TodoCreateActivity : AppCompatActivity(){

    private lateinit var todoEditPresenter: TodoEditCreateContract.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todoedit)
        todoEditPresenter = TodoEditCreatePresenter()
        setupViews()
        dateSelect()
    }

    private fun setupViews() {
        tool_bar.title = "やること"
        setSupportActionBar(tool_bar)
//        val adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        type_spinner.adapter = adapter
//        SpinnerSelection().setSelection(type_spinner, type)
//        todoEditPresenter.selectType(type)
    }
    private fun dateSelect(){
        val mCalender = Calendar.getInstance()
        deadlineEdit.setOnKeyListener(null)
        deadlineEdit.setOnClickListener {
            val datePicker = DatePickerDialog(this@TodoCreateActivity,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        //setした日付を取得して表示
                        val calenderFormat = String.format("%d/%d/%d", year, month + 1, dayOfMonth)
                        deadlineEdit.setText(calenderFormat)
                    },
                    mCalender.get(Calendar.YEAR),
                    mCalender.get(Calendar.MONTH),
                    mCalender.get(Calendar.DATE))

            datePicker.show()
        }
    }





//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val spinnerType = type_spinner.getItemAtPosition(position) as String
//        type = TodoType.values().filter{ it.rawvalue == spinnerType}.first()
//        todoEditPresenter.selectType(type)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                todoEditPresenter.saveClick(todoEdit.text.toString(), deadlineEdit.text.toString())
                Toast.makeText(this, "追加しました", Toast.LENGTH_SHORT).show()

                var intent = Intent(application,TodoListActivity::class.java)
                startActivity(intent)

            }
            else -> return super.onOptionsItemSelected(item)
        }
        finish()
        return true
    }

    fun createIntent(context: Context): Intent {
        val intent = Intent(context, TodoCreateActivity::class.java)
        return intent
    }
}


