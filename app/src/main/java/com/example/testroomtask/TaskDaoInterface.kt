package com.example.testroomtask

import androidx.room.*


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

