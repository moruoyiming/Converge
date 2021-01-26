package com.example.hotfix.note.javabasics.json.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 重写writeObject/readObject
 */
public class Course2 implements Serializable {

    private static final long serialVersionUID = 667279791530738499L;
    private String name;
    private float score;

    public Course2() {
        System.out.println("Course: empty");
    }

    public Course2(String name, float score) {
        System.out.println("Course: " + name + " " + score);
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


    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        System.out.println("readObject");
        inputStream.defaultReadObject();
        name = (String)inputStream.readObject();
        score = inputStream.readFloat();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        System.out.println("writeObject");
        outputStream.defaultWriteObject();
        outputStream.writeObject(name);
        outputStream.writeFloat(score);
    }


    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String... args) throws Exception {
        //TODO:
        Course2 course = new Course2("英语", 12f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(course);
        byte[] bs = out.toByteArray();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs));
        Course2 course1 = (Course2) ois.readObject();
        System.out.println("course1: " + course1);

    }


}
