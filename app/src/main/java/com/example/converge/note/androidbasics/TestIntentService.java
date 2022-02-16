package com.example.converge.note.androidbasics;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * @Date: 2022/2/15
 * @Time: 6:02 下午
 * @Author: Jian
 */
public class TestIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
