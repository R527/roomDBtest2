package com.example.roomdatabasetest.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasksEntity")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tasks")
    private String task;

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
