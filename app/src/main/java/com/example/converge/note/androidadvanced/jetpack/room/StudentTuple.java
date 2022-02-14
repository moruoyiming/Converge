package com.example.converge.note.androidadvanced.jetpack.room;

import androidx.room.ColumnInfo;

public class StudentTuple {
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name="pwd")
    public String password;

    public StudentTuple(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentTuple{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
