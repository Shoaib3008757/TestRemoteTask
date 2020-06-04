package com.example.testroomtask

import android.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter (mCtx: Context, taskList: List<MyRoomTask>) :
    RecyclerView.Adapter<MyAdapter.TasksViewHolder>()
{
    private val mCtx: Context
    private val taskList: List<MyRoomTask>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksViewHolder {
        val view: View =
            LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TasksViewHolder,
        position: Int
    ) {
        val t: MyRoomTask = taskList[position]
        holder.tv_list_item.text = t.taskListItems

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TasksViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tv_list_item: TextView
        override fun onClick(view: View?) {
            val task: MyRoomTask = taskList[adapterPosition]
            //val intent = Intent(mCtx, UpdateTaskActivity::class.java)
            //intent.putExtra("task", task)
           // mCtx.startActivity(intent)
        }

        init {
            tv_list_item = itemView.findViewById(R.id.tv_list_item)
            itemView.setOnClickListener(this)
        }
    }

    init {
        this.mCtx = mCtx
        this.taskList = taskList
    }
}