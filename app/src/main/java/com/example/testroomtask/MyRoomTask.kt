package com.example.testroomtask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class MyRoomTask : Serializable{

    @PrimaryKey(autoGenerate = true)
    var id = 0
    @ColumnInfo(name = "taskListItems")
    var taskListItems: String? = null


}