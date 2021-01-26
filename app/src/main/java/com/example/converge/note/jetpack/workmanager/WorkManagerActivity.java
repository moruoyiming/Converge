package com.example.converge.note.jetpack.workmanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.converge.R;

public class WorkManagerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanager);
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
                .Builder(WorkManager1.class)
                .build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }
}
