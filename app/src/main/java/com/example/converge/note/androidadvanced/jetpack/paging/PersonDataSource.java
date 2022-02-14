package com.example.converge.note.androidadvanced.jetpack.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import java.util.ArrayList;
import java.util.List;

public class PersonDataSource extends PositionalDataSource {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        callback.onResult(getStudents(0, 20), 0, 500);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback callback) {
        callback.onResult(getStudents(params.startPosition, params.loadSize));
    }

    private List<Person> getStudents(int startPosition, int pageSize) {
        List<Person> list = new ArrayList<>();
        for (int i = startPosition; i < startPosition + pageSize; i++) {
            Person person = new Person();
            person.setId("ID号是：" + i);
            person.setName("我名称：" + i);
            person.setSex("性别" + i);
            list.add(person);
        }
        Log.i("what","xxxxx"+list.size());
        return list;
    }
}
