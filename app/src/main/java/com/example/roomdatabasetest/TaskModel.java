package com.example.roomdatabasetest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.rxjava3.core.Completable;

public class TaskModel extends AndroidViewModel {

    private TaskDao taskDao;
    public TaskModel(@NonNull Application application) {
        super(application);
        taskDao = ((AppComponent)application).getDatabase().taskDao();
    }




}
