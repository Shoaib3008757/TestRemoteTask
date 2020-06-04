package com.example.testroomtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<MyRoomTask> taskList;

    public MyAdapter(Context mCtx, List<MyRoomTask> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_adapter, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        MyRoomTask t = taskList.get(position);
        holder.tv_list_item.setText(t.getTaskListItems());



    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_list_item;

        public TasksViewHolder(View itemView) {
            super(itemView);

            tv_list_item = itemView.findViewById(R.id.tv_list_item);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MyRoomTask task = taskList.get(getAdapterPosition());

           /* Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("task", task);

            mCtx.startActivity(intent);*/
        }
    }
}
