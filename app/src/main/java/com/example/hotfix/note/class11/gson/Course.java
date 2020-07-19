package com.example.hotfix.note.class11.gson;

public class Course {

    private String name;
    private float score;

    public Course() {
        System.out.println("_Course: empty");
    }

    public Course(String name, float score) {
        System.out.println("_Course: " + name + " " + score);
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
