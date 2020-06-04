package com.example.testroomtask.Interfaces

import androidx.room.*
import com.example.testroomtask.RoomDatabase.MyRoomTask


@Dao
interface TaskDaoInterface {
    @get:Query("SELECT * FROM MyRoomTask")
    val all: List<MyRoomTask?>?

    @Insert
    fun insert(task: MyRoomTask?)

    @Delete
    fun delete(task: MyRoomTask?)

    @Update
    fun update(task: MyRoomTask?)


}

