package com.example.banjoshunsuke.mytodoupgrade.View

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter

import android.view.View
import android.widget.Toast
import com.example.banjoshunsuke.mytodoupgrade.Contract.TodoEditCreateContract
import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity
import com.example.banjoshunsuke.mytodoupgrade.Presenter.TodoEditCreatePresenter
import com.example.banjoshunsuke.mytodoupgrade.R
import kotlinx.android.synthetic.main.activity_todoedit.*
import java.text.SimpleDateFormat
import java.util.*
import android.support.design.widget.Snackbar



/**
 * Created by banjousyunsuke on 2018/01/27.
 */
class TodoEditActivity : AppCompatActivity(), TodoEditCreateContract.View {
    private lateinit var todoEditPresenter: TodoEditCreateContract.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todoedit)
        val todoId = intent.getIntExtra("todoId", -1)
        todoEditPresenter = TodoEditCreatePresenter(this)
        setupViews()
        Log.d("debug2", todoId.toString())
        todoEditPresenter.loadTodoEntity(todoId)
        dateSelect()
    }

    private fun setupViews() {
        tool_bar.title = "やること"
        setSupportActionBar(tool_bar)
    }
    private fun dateSelect(){
        val mCalender = Calendar.getInstance()
        deadlineEdit.setOnKeyListener(null)
        deadlineEdit.setOnClickListener {
            val datePicker = DatePickerDialog(this@TodoEditActivity,
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

    override fun setData(todoEntity: TodoEntity) {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        todoEdit.setText(todoEntity.todo)
        deadlineEdit.setText(sdf.format(todoEntity.deadLine))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                todoEditPresenter.updateClick(todoEdit.text.toString(), deadlineEdit.text.toString())
                Snackbar.make(findViewById(android.R.id.content),
                        "アップデートしました", Snackbar.LENGTH_LONG)
                        .setAction("戻る") {
                            var intent = Intent(application,TodoListActivity::class.java)
                            startActivity(intent)
                        }
                        .setActionTextColor(Color.YELLOW)
                        .show()
            }
            R.id.menu_delete -> {
                todoEditPresenter.deleteClick()
                var intent = Intent(application,TodoListActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "削除しました", Toast.LENGTH_LONG).show(); }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
    fun createIntent(context: Context, todoId:Int): Intent {
        val intent = Intent(context, TodoEditActivity::class.java)
        intent.putExtra("todoId",todoId)
        return intent
    }
}