package com.example.converge.note.androidheight.jetpack.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

// 最简单的 执行任务
public class MainWorker1 extends Worker {

    private final static String TAG = MainWorker1.class.getSimpleName();

    public MainWorker1(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    // 后台任务 并且 异步的
    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "MainWorker1 doWork: run started ... ");
        try {
            Thread.sleep(8000); // 睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.failure(); // 本次任务失败
        } finally {
            Log.d(TAG, "MainWorker1 doWork: run end ... ");
        }
        return Result.success(); // 本次任务成功
    }
}
