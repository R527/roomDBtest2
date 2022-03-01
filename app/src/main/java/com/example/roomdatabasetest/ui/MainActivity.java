package com.example.roomdatabasetest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.roomdatabasetest.R;
import com.example.roomdatabasetest.db.TaskDao;
import com.example.roomdatabasetest.db.TaskEntity;
import com.example.roomdatabasetest.db.TaskRoomDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.reactivex.rxjava3.core.Completable;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn = (FloatingActionButton)findViewById(R.id.add_task_button);

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