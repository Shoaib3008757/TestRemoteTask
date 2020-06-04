package com.example.testroomtask.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testroomtask.Interfaces.SingleITemcClick
import com.example.testroomtask.R
import com.example.testroomtask.RoomDatabase.MyRoomTask


class MyAdapterForList(
    private val mCtx: Context,
    private val taskList: List<MyRoomTask>,
    var singleITemcClick: SingleITemcClick
) :
    RecyclerView.Adapter<MyAdapterForList.TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view =
            LayoutInflater.from(mCtx).inflate(R.layout.layout_adapter, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val t = taskList[position]
        holder.tv_list_item.text = t.taskListItems
        holder.tv_list_item_tag.text = t.tag
        Log.i("TAg", "the value of tage is here " + t.tag)
        if (t.tag.toString().equals("yes", ignoreCase = true)) {
            holder.tv_list_item.setTextColor(mCtx.resources.getColor(R.color.black))
        } else {
            holder.tv_list_item.setTextColor(mCtx.resources.getColor(R.color.dimBlack))
        }
        holder.rc_item.setOnClickListener { view ->
            singleITemcClick.onSingleItemClick(
                view,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TasksViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tv_list_item: TextView
        var tv_list_item_tag: TextView
        var rc_item: RelativeLayout

        init {
            tv_list_item = itemView.findViewById(R.id.tv_list_item)
            tv_list_item_tag = itemView.findViewById(R.id.tv_list_item_tag)
            rc_item = itemView.findViewById(R.id.rc_item)
        } /* @Override
        public void onClick(View view) {
            MyRoomTask task = taskList.get(getAdapterPosition());


        }*/
    }

}
