package com.example.hotfix.note.class19;

public class Star {
    private String name ;
    private String groudName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroudName() {
        return groudName;
    }

    public void setGroudName(String groudName) {
        this.groudName = groudName;
    }

    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", groudName='" + groudName + '\'' +
                '}';
    }
}
