package com.example.testroomtask.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testroomtask.R
import com.example.testroomtask.RoomDatabase.DatabaseClient2
import com.example.testroomtask.RoomDatabase.MyRoomTask

class ActivitySecondPage : AppCompatActivity(){

    var ch_box: CheckBox? = null
    var bt_submit: Button? = null
    var tagPos: String? = null
    var item: String? = null
    var task: MyRoomTask? = null

    var rc_list: RecyclerView? = null
    var sharedPreferences: SharedPreferences = getSharedPreferences("PREF", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)


        init()
        gettiingIntentValue()
        onSubmitClick()
    }

    private fun init() {
        ch_box = findViewById<View>(R.id.ch_box) as CheckBox
        bt_submit = findViewById<View>(R.id.bt_submit) as Button
    }

    private fun gettiingIntentValue() {
        task = intent.getSerializableExtra("task") as MyRoomTask
        item = intent.getStringExtra("item")
    }

    private fun onSubmitClick() {
        bt_submit!!.setOnClickListener {
            if (ch_box!!.isChecked) {
                updateTask(task!!, item!!)
            } else {
                Toast.makeText(
                    this@ActivitySecondPage,
                    "Please Check Term and condition",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun updateTask(task: MyRoomTask, iteName: String) {
        class UpdateTask :
            AsyncTask<Void?, Void?, Void?>() {


            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                val i = Intent()
                i.putExtra("done", "done")
                setResult(123, i)
                finish()
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                task.tag = "yes"
                task.taskListItems = iteName
                DatabaseClient2.getInstance(applicationContext).appDatabase
                    .taskDao()
                    ?.update(task)
                return null
            }
        }

        val ut = UpdateTask()
        ut.execute()
    }

}

