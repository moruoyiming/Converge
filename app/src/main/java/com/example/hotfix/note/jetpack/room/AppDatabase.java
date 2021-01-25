package com.example.hotfix.note.jetpack.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class, Address.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appdatabase;

    public static AppDatabase getInstance(Context context) {
        if (appdatabase == null) {
            appdatabase = Room.databaseBuilder(context
                    , AppDatabase.class
                    , "jettDB")
                    // 主线程创建数据库
                    .allowMainThreadQueries()
                    .build();
        }
        return appdatabase;
    }

    public abstract StudentDao userDao();
}
