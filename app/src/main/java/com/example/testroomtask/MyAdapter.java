package com.example.testroomtask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<MyRoomTask> taskList;
    SingleITemcClick singleITemcClick;

    public MyAdapter(Context mCtx, List<MyRoomTask> taskList, SingleITemcClick onSingleItemClick ) {
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.singleITemcClick = onSingleItemClick;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_adapter, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, final int position) {
        MyRoomTask t = taskList.get(position);
        holder.tv_list_item.setText(t.getTaskListItems());
        holder.tv_list_item_tag.setText(t.getTag());

        Log.i("TAg", "the value of tage is here " + t.getTag());
        if (t.getTag().toString().equalsIgnoreCase("yes"))
        {
            holder.tv_list_item.setTextColor(mCtx.getResources().getColor(R.color.black));
        }else
            {
                holder.tv_list_item.setTextColor(mCtx.getResources().getColor(R.color.dimBlack));
            }
        holder.rc_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                singleITemcClick.onSingleItemClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView tv_list_item, tv_list_item_tag;
        RelativeLayout rc_item;

        public TasksViewHolder(View itemView) {
            super(itemView);

            tv_list_item = itemView.findViewById(R.id.tv_list_item);
            tv_list_item_tag = itemView.findViewById(R.id.tv_list_item_tag);
            rc_item = itemView.findViewById(R.id.rc_item);

        }

       /* @Override
        public void onClick(View view) {
            MyRoomTask task = taskList.get(getAdapterPosition());


        }*/
    }
}
