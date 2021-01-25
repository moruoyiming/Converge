package com.example.hotfix.note.jetpack.room;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey
    private int name;

    @Embedded//描述对象类型
    private Address address;

    public Person(int name, Address address) {
        this.name = name;
        this.address = address;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}







