package com.example.converge.note.javabasics.json.serializable;

import java.io.Serializable;

public class Person  implements Serializable {

    private static final long serialVersionUID = 1865725274925268509L;
    private String name;
    private String sax;

    public Person() {
    }

    public Person(String name, String sax) {
        this.name = name;
        this.sax = sax;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sax='" + sax + '\'' +
                '}';
    }
}
