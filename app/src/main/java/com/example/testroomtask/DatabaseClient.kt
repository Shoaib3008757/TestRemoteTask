package com.example.testroomtask

import android.content.Context
import androidx.room.Room


class DatabaseClient private constructor(private val mCtx: Context) {
    //our app database object
    private val appDatabase: AppDatabase

    fun getAppDatabase(): AppDatabase {
        return appDatabase
    }

    companion object {
        private var mInstance: DatabaseClient? = null
        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance
        }
    }

    init {
        //creating the app database with Room database builder
//MyToDos is the name of the database
        appDatabase =
            Room.databaseBuilder<AppDatabase>(mCtx, AppDatabase::class.java, "MyToDos").build()
    }
}