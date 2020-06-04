package com.example.testroomtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SecondPage extends AppCompatActivity {

    CheckBox ch_box;
    Button bt_submit;
    String tagPos;
    String item;
    MyRoomTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        init();
        gettiingIntentValue();
        onSubmitClick();
    }

    private void init()
    {
        ch_box = (CheckBox) findViewById(R.id.ch_box);
        bt_submit = (Button) findViewById(R.id.bt_submit);
    }

    private void gettiingIntentValue()
    {
        task = (MyRoomTask) getIntent().getSerializableExtra("task");
        item = getIntent().getStringExtra("item");

    }
    private void onSubmitClick()
    {


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch_box.isChecked())
                {
                    updateTask(task, item);
                }
                else
                    {
                        Toast.makeText(SecondPage.this, "Please Check Term and condition", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }



    private void updateTask(final MyRoomTask task, final String iteName) {


        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                task.setTag("yes");
                task.setTaskListItems(iteName);
                DatabaseClient2.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent i = new Intent();
                i.putExtra("done", "done");
                setResult(123, i);
                finish();
            }
        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }


   /* private void deleteTask(final MyRoomTask task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient2.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                for(int i = 0;i<=5;i++)
                {
                    if (i==0 ||  i==1)
                    {
                        String taskItems= "item " +i;
                        saveTask(taskItems,"yes");
                    }

                    else {
                        String taskItems = "item " + i;
                        saveTask(taskItems, "no");
                    }
                }







            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }

    private void saveTask(final String listItem, final String visibility)
    {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                MyRoomTask task = new MyRoomTask();
                task.setTaskListItems(listItem);
                task.setTag(visibility);
                //adding to database


                DatabaseClient2.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }*/
}
