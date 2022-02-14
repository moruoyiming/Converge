package com.example.converge.note.androidheight.jetpack.workmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * 数据 互相传递
 * 后台任务
 */
public class MainWorker2 extends Worker {

    public final static String TAG = MainWorker2.class.getSimpleName();

    private Context mContext;
    private WorkerParameters workerParams;

    public MainWorker2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.mContext = context;
        this.workerParams = workerParams;
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "MainWorker2 doWork: 后台任务执行了");

        // 接收 MainActivity传递过来的数据
        String dataString = workerParams.getInputData().getString("Derry");
        Log.d(TAG, "MainWorker2 doWork: 接收Activity传递过来的数据:" + dataString);

        // 反馈数据 给 MainActivity
        // 把任务中的数据回传到activity中
        Data outputData = new Data.Builder().putString("Derry", "三分归元气").build();
        Result.Success success = new Result.Success(outputData);

        // return new Result.Failure(); // 本地执行 doWork 任务时 失败
        // return new Result.Retry(); // 本地执行 doWork 任务时 重试一次
        // return new Result.Success(); // 本地执行 doWork 任务时 成功 执行任务完毕
        return success;
    }
}
