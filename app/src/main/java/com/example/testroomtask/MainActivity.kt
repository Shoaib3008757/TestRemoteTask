package com.example.testroomtask

import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    var rc_list: RecyclerView? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rc_list = findViewById(R.id.rc_list)
        rc_list?.setLayoutManager(LinearLayoutManager(this))
       var sharedPreferences  = getSharedPreferences("Pref", 0)

        val firstTimeLunch: String = sharedPreferences.getString("firTime", "")

        Log.i("TAG", "the pre is  $firstTimeLunch")
        if(firstTimeLunch.isEmpty())
        {
            val editor = sharedPreferences.edit()
            editor.putString("firTime", "no")
            editor.commit()

            for (i in 0..5) {
                val taksItem = "item $i"
                saveTask(taksItem)
            }

            Log.d("Tag", "Inserted to Room")
        }
        else
        {

            getTasks();
        }

    }

    private fun saveTask(item: String)
    {

            class SaveTask :
                AsyncTask<Void?, Void?, Void?>() {

                override fun onPostExecute(aVoid: Void?) {
                    super.onPostExecute(aVoid)

                    // startActivity(Intent(applicationContext, MainActivity::class.java))

                }

                override fun doInBackground(vararg p0: Void?): Void? {
                    val task = MyRoomTask()
                    task.taskListItems = item

                    //adding to database
                    DatabaseClient.getInstance(applicationContext)?.getAppDatabase()
                        ?.taskDao()
                        ?.insert(task)
                    return null
                }
            }

            val st = SaveTask()

            st.execute()

    }


    private fun getTasks() {
        class GetTasks :
            AsyncTask<Void?, Void?, List<MyRoomTask>>() {
            protected override fun doInBackground(vararg p0: Void?): List<MyRoomTask>? {
                return DatabaseClient
                    .getInstance(applicationContext)
                    ?.getAppDatabase()
                    ?.taskDao()
                    ?.all as List<MyRoomTask>?
            }

            override fun onPostExecute(tasks: List<MyRoomTask>) {
                super.onPostExecute(tasks)
                val adapter = MyAdapter(this@MainActivity, tasks)
                rc_list?.setAdapter(adapter)
            }

        }

        val gt = GetTasks()
        gt.execute()
    }
}
