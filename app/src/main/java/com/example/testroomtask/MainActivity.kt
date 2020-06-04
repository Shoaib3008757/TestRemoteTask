package com.example.testroomtask

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    var rc_list: RecyclerView? = null
    var sharedPreferences = getSharedPreferences("Pref", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rc_list = findViewById(R.id.rc_list)

        val firstTimeLunch: String = SPref.getStringPref(sharedPreferences, SPref.isfFirtTime)

        if(firstTimeLunch=="yes")
        {

        }
        else
        {
            val editor = sharedPreferences.edit()
            editor.putString(SPref.isfFirtTime, "no")
            editor.commit()

            for (i in 0..5) {
                val taksItem = "item $i"
                saveTask(taksItem)
            }
        }

    }

    private fun saveTask(item: String)
    {

        val firstTimeLunch: String = SPref.getStringPref(sharedPreferences, SPref.isfFirtTime)
        if(firstTimeLunch=="yes")
        {
            val editor = sharedPreferences.edit()
            editor.putString(SPref.isfFirtTime, "yes")
            editor.commit()

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



    }
}
