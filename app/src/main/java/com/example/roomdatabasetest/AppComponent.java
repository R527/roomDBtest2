package com.example.roomdatabasetest;

import android.app.Application;

public class AppComponent extends Application {
    public TaskRoomDB getDatabase() {
        return TaskRoomDB.getInstance(this);
    }
}
