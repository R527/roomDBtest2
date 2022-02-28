package com.example.roomdatabasetest.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskEntity.class},version = 1,exportSchema = false)
public abstract class TaskRoomDB extends RoomDatabase {

    private static TaskRoomDB sInstance;
    public static final String DATABASE_NAME = "mydb";

    public abstract TaskDao taskDao();

    public static TaskRoomDB getInstance(final Context context) {
        //二つ以上あると問題だから制御する
        if (sInstance == null) {
            synchronized (TaskRoomDB.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context, TaskRoomDB.class, DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}
