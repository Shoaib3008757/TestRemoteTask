package com.example.testroomtask

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MyRoomTask::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDaoInterface?
}