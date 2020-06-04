package com.example.testroomtask.RoomDatabase;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient2 {

    private Context mCtx;
    private static DatabaseClient2 mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient2(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient2 getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient2(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
