package com.example.roomdatabasetest.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdatabasetest.db.TaskEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasksEntity")
    Flowable<List<TaskEntity>> getAll();

    @Insert
    Completable insert(TaskEntity taskEntity);

    @Delete
    Completable delete(TaskEntity taskEntity);
}
