package com.example.roomdatabasetest;

import android.app.Application;

import com.example.roomdatabasetest.db.TaskRoomDB;

public class AppComponent extends Application {
    public TaskRoomDB getDatabase() {
        return TaskRoomDB.getInstance(this);
    }

    Application application;
}
