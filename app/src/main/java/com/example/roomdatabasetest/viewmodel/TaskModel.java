package com.example.roomdatabasetest.viewmodel;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.roomdatabasetest.db.TaskEntity;
import com.example.roomdatabasetest.AppComponent;
import com.example.roomdatabasetest.db.TaskDao;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class TaskModel extends AndroidViewModel {
    private final String TAG = "TaskModel";
    private TaskDao taskDao;
    private List<TaskEntity> mTasks;
    private List<Boolean> importants;

    public TaskModel(@NonNull Application application) {
        super(application);
        taskDao = ((AppComponent)application).getDatabase().taskDao();
    }

    //非同期処理対応の返り値
    //メソッド内にバージョン不足だと利用できないメソッドあるから注意書きの@RequiresApi
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Flowable<List<String>> getTaskTextList() {
        //tasksを全取得して
        return taskDao.getAll()
                //DatabaseにあるTasks＜List＞を取得していじる
                .map(tasks -> {
                    mTasks = tasks;
                    return tasks.stream()
                            //Stringのみを抽出
                            //for文で回すのと同じ処理
                            .map(task -> task.getTask())
                            .collect(Collectors.toList());
                });
    }


    //task重要度を取得する
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Flowable<List<Boolean>> getTaskImportantList(){
        return taskDao.getAll()
                //DatabaseにあるTasks＜List＞を取得していじる
                .map(importants -> {
                    mTasks = importants;
                    return importants.stream()
                            //Stringのみを抽出
                            //for文で回すのと同じ処理
                            .map(important -> important.isTask_important())
                            .collect(Collectors.toList());
                });
    }


    //タスクを追加する処理
    public Completable insertTask(final String text, final boolean important) {

        Log.d(TAG,"insertTask");
        //Entityに登録
        TaskEntity task = new TaskEntity();
        task.setTask(text);
        task.setTask_important(important);
        //データベースに登録
        return taskDao.insert(task);
    }

    //タスク削除処理
    public Completable deleteTask(int position) {
        return taskDao.delete(mTasks.get(position));
    }

}
