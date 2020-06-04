package com.example.testroomtask.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testroomtask.Adapters.MyAdapterForList
import com.example.testroomtask.Interfaces.SingleITemcClick
import com.example.testroomtask.R
import com.example.testroomtask.RoomDatabase.DatabaseClient2
import com.example.testroomtask.RoomDatabase.MyRoomTask

class ActivityFirstPage : AppCompatActivity(){
    var rc_list: RecyclerView? = null
    var sharedPreferences: SharedPreferences = getSharedPreferences("PREF", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()

    }
    private fun init() {
        sharedPreferences = getSharedPreferences("PREF", 0)
        val firstTimeLunch = sharedPreferences.getString("firTime", "")
        rc_list = findViewById<View>(R.id.rc_list) as RecyclerView
        rc_list!!.layoutManager = LinearLayoutManager(this)
        Log.i("TAG", "the pre is  $firstTimeLunch")
        if (firstTimeLunch.isEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString("firTime", "no")
            editor.commit()
            for (i in 0..5) {
                if (i == 0) {
                    val taskItems = "item $i"
                    saveTask(taskItems, "yes")
                } else {
                    val taskItems = "item $i"
                    saveTask(taskItems, "no")
                }
            }
            Log.d("Tag", "Inserted to Room")
            getTasks()
        } else {
            Log.d("Tag", "getting from Room")
            getTasks()
        }
    }

    private fun saveTask(listItem: String, visibility: String) {
        class SaveTask :
            AsyncTask<Void?, Void?, Void?>() {
            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                val task = MyRoomTask()
                task.taskListItems = listItem
                task.tag = visibility
                //adding to database
                DatabaseClient2.getInstance(applicationContext).appDatabase
                    .taskDao()
                    ?.insert(task)
                return null
            }
        }

        val st = SaveTask()
        st.execute()
    }

    private fun getTasks() {
        class GetTasks :
            AsyncTask<Void?, Void?, List<MyRoomTask?>?>() {
            protected override fun doInBackground(vararg p0: Void?): List<MyRoomTask?>? {
                return DatabaseClient2
                    .getInstance(applicationContext)
                    .appDatabase
                    .taskDao()
                    ?.all
            }

            override fun onPostExecute(tasks: List<MyRoomTask?>?) {
                super.onPostExecute(tasks)
                val adapter =
                    MyAdapterForList(this@ActivityFirstPage, tasks as List<MyRoomTask>,
                        SingleITemcClick { v, position ->
                            val tv_list_item =
                                v.findViewById<View>(R.id.tv_list_item) as TextView
                            val tv_tag =
                                v.findViewById<View>(R.id.tv_list_item_tag) as TextView
                            val itemName = tv_list_item.text.toString()
                            val task = tasks!![position]
                            val i = Intent(
                                this@ActivityFirstPage,
                                ActivitySecondPage::class.java
                            )
                            i.putExtra("task", task)
                            i.putExtra("item", itemName)
                            i.putExtra("tagPos", tasks[position]!!.tag)
                            startActivityForResult(i, 123)
                        })
                rc_list!!.adapter = adapter
            }
        }

        val gt = GetTasks()
        gt.execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 123) {
            val message = data?.getStringExtra("done")
            if (message.equals("done", ignoreCase = true)) {
                finish()
                startActivity(Intent(this@ActivityFirstPage, ActivityFirstPage::class.java))
            }
        }
    }

}
