package com.example.converge.note.javabasics.generic;

import androidx.annotation.NonNull;

/**
 * @Date: 2022/4/1
 * @Time: 15:24
 * @Author: Jian
 */
public class GenericMethod3 {

    static class Fruit {

        @NonNull
        @Override
        public String toString() {
            return "fruit";
        }
    }

    static class Apple extends Fruit {
        @NonNull
        @Override
        public String toString() {
            return "apple";
        }
    }


    static class Person {
        @NonNull
        @Override
        public String toString() {
            return "Person";
        }
    }

    static class GenericTest<T> {

        public void show1(T t) {
            System.out.println(t.toString());
        }

        public <T> void show2(T t) {
            System.out.println(t.toString());
        }

        public <E> void show3(E e){
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();
        GenericTest test = new GenericTest();
        test.show1(apple);
        test.show1(person);
        test.show2(apple);
        test.show3(apple);

        GenericTest<Fruit> test2 = new GenericTest();
        test2.show1(apple);
//        test2.show1(person);
        test2.show2(person);
        test2.show3(person);
    }


}
