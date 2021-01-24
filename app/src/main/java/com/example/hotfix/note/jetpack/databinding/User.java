package com.example.hotfix.note.jetpack.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.hotfix.BR;

public class User extends BaseObservable {
    private String name;
    private String pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }
}
