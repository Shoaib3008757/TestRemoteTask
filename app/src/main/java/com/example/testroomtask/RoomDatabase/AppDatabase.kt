package com.example.testroomtask.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testroomtask.Interfaces.TaskDaoInterface


@Database(entities = [MyRoomTask::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDaoInterface?
}