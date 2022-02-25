package com.example.roomdatabasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Dao;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TaskModel taskModel;
    private TaskDao taskDao;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private Application application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);


        btn.setOnClickListener(v -> {
            //todo
            //タスク追加処理
            mDisposable.add(insertTask("test")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {},
                            throwable -> Log.e(TAG, "Unable to update username", throwable)));
        });
    }

    public Completable insertTask(String text){
        //Entityに登録
        TaskEntity task = new TaskEntity();
        task.setTask(text);
        //データベースに登録
        return taskDao.insert(task);
    }


}