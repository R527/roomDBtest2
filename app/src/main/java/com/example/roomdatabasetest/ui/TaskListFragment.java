package com.example.roomdatabasetest.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabasetest.R;
import com.example.roomdatabasetest.viewmodel.TaskModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaskListFragment extends Fragment implements DeleteTaskListener {
    private static final String TAG = "TaskListFragment";
    private RecyclerView mRecyclerView;
    protected TaskAdapter mAdapter;
    private TaskModel taskModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list,container,false);

        taskModel = new ViewModelProvider(this).get(TaskModel.class);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.task_list_view);
        mAdapter = new TaskAdapter();
        mAdapter.setDeleteTaskListener(this);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart() {
        super.onStart();
        //mDisposableでそれ以下の処理が繰り返されるのを止める
        mDisposable.add(taskModel.getTaskTextList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //AdapterにあるRecyclerViewにTextListを渡す
                .subscribe(textList -> {
                            mAdapter.setData(textList);
                        },
                        throwable -> Log.e(TAG, "Unable to get username", throwable)));
    }

    //mDisposableをすべて削除
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "mDisposable.clear();");
        mDisposable.clear();
    }

    //DeleteTask
    @Override
    public void onClickDeleteTask(int position) {
        mDisposable.add(taskModel.deleteTask(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mAdapter.notifyDataSetChanged(),
                        throwable -> Log.e(TAG, "Unable to update username", throwable)));
    }
}
