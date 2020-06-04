package com.example.testroomtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class FirstPage extends AppCompatActivity {

    RecyclerView rc_list;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        sharedPreferences = getSharedPreferences("PREF", 0);
        String firstTimeLunch = sharedPreferences.getString("firTime", "");

        rc_list = (RecyclerView) findViewById(R.id.rc_list);
        rc_list.setLayoutManager(new LinearLayoutManager(this));


        Log.i("TAG", "the pre is  " +  firstTimeLunch);
        if(firstTimeLunch.isEmpty())
        {
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            editor.putString("firTime", "no");
            editor.commit();
            for(int i = 0;i<=5;i++)
            {
                if (i==0)
                {
                    String taskItems= "item " +i;
                    saveTask(taskItems,"yes");
                }
                else {
                    String taskItems = "item " + i;
                    saveTask(taskItems, "no");
                }
            }

            Log.d("Tag", "Inserted to Room");
            getTasks();

        }
        else
        {
            Log.d("Tag", "getting from Room");
            getTasks();
        }


    }

    private void saveTask(final String listItem, final String visibility) {

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
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<MyRoomTask>> {

            @Override
            protected List<MyRoomTask> doInBackground(Void... voids) {
                List<MyRoomTask> taskList = DatabaseClient2
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(final List<MyRoomTask> tasks) {
                super.onPostExecute(tasks);
                MyAdapter adapter = new MyAdapter(FirstPage.this, tasks, new SingleITemcClick() {
                    @Override
                    public void onSingleItemClick(View v, int position) {

                        TextView tv_list_item = (TextView) v.findViewById(R.id.tv_list_item);
                        TextView tv_tag = (TextView) v.findViewById(R.id.tv_list_item_tag);
                        String itemName = tv_list_item.getText().toString();
                        MyRoomTask task = tasks.get(position);
                        Intent i = new Intent(FirstPage.this, SecondPage.class);
                        i.putExtra("task", task);
                        i.putExtra("item", itemName);

                        i.putExtra("tagPos", tasks.get(position).getTag());
                        startActivityForResult(i, 123);

                    }
                });
                rc_list.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==123)
        {
            String message=data.getStringExtra("done");
            if (message.equalsIgnoreCase("done"))
            {
                finish();
                startActivity(new Intent(FirstPage.this, FirstPage.class));
            }
        }
    }

}
