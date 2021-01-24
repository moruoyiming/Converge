package com.example.hotfix.note.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Address.class,parentColumns = "addressId",childColumns = "addressId"))
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name="pwd")
    private String password;
    @ColumnInfo(name="addressId")
    private int addressId;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", addressId=" + addressId +
                '}';
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Student( String name, String password, int addressId) {
        this.name = name;
        this.password = password;
        this.addressId = addressId;
    }
}
