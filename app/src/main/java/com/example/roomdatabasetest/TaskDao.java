package com.example.roomdatabasetest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasksEntity")
    List<TaskEntity> getAll();

    @Insert
    Completable insert(TaskEntity taskEntity);

    @Delete
    void delete(TaskEntity taskEntity);
}
