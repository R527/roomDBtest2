package com.example.roomdatabasetest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.roomdatabasetest.R;
import com.example.roomdatabasetest.db.TaskDao;
import com.example.roomdatabasetest.db.TaskEntity;
import com.example.roomdatabasetest.db.TaskRoomDB;
import com.example.roomdatabasetest.viewmodel.TaskModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);

        //DatabaseをInstace
        TaskRoomDB db = TaskRoomDB.getInstance(this.getApplicationContext());
        taskDao = db.taskDao();

        btn.setOnClickListener(v -> {
            //タスク追加処理
            AddTaskDialogFragment dialog = new AddTaskDialogFragment();
            dialog.show(getSupportFragmentManager(), "AddTaskDialogFragment");
//            mDisposable.add(insertTask("test")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(() -> {},
//                            throwable -> Log.e(TAG, "Unable to update username", throwable)));
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