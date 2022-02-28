package com.example.roomdatabasetest.ui;

import android.content.Context;

import androidx.room.Room;

import com.example.roomdatabasetest.db.TaskRoomDB;

public class TaskDBSingleton {
    private static TaskRoomDB instance = null;
    private TaskDBSingleton(){}

    public static TaskRoomDB getInstance(Context context){
        if(instance != null){
            return instance;
        }
        instance = Room.databaseBuilder(context,TaskRoomDB.class,"TaskDatabase").build();
        return instance;
    }
}
