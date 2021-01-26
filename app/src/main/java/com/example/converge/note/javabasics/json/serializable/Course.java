package com.example.converge.note.javabasics.json.serializable;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 667279791530738499L;
    private String name;
    private float score;

    public Course() {
        System.out.println("Course: empty");
    }

    public Course(String name, float score) {
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

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    static class A {
        private String str;

        public A(){}

        public A(String str){
            this.str = str;
        }

        @Override
        public String toString() {
            return "A{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }

   static class B implements Serializable {
        private String name;
        private int age;

        private A a;

        public B(String name,int age, A a){
            this.name = name;
            this.age = age;
            this.a = a;
        }

        @Override
        public String toString() {
            return "B{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", a=" + a +
                    '}';
        }
    }

    public static void main(String... args) throws Exception {
        B b = new B("Zero",18,new A());

        byte[] bs = SerializeableUtils.serialize(b);

        //反序列化
        B b1 = SerializeableUtils.deserialize(bs);
        System.out.println(b1);
    }
}
