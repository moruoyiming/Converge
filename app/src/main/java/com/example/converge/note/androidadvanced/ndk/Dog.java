package com.example.converge.note.androidadvanced.ndk;

import android.util.Log;

public class Dog {


    public Dog() {
        Log.d("Dog", "Dog init...");
    }

    public Dog(int number1) {
        Log.d("Dog", "Dog init...number1:" + number1);
    }

    public Dog(int number1, String name) {
        Log.d("Dog", "Dog init...number1:" + number1 + "  name = " + name);
    }
}
